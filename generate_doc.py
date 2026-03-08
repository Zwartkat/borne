
from __future__ import (
    annotations,
)  # Permet les annotations de types en avant-référence (Python < 3.13)

import base64  # Encodage base64 requis pour envoyer des images à /api/generate
import json  # Sérialisation/désérialisation JSON
import shutil  # Pour trouver l'exécutable "ollama" dans le PATH
import socket  # Pour tester rapidement si un port TCP est ouvert
import subprocess  # Pour lancer "ollama serve"
import time  # Pour boucler avec un timeout lors du démarrage serveur
from dataclasses import dataclass  # Modèles de données simples et typés
from pathlib import Path  # Manipulation robuste des chemins
from typing import Any, Dict, List, Literal, Mapping, Optional, Sequence, Union

import numpy as np  # Types




class OllamaError(RuntimeError):
    """Erreur générique pour les opérations Ollama."""


class OllamaConnectionError(OllamaError):
    """Erreur de connexion au serveur Ollama."""


class OllamaResponseError(OllamaError):
    """Erreur lorsque la réponse HTTP/JSON d'Ollama est invalide ou inattendue."""


class OllamaServerStartError(OllamaError):
    """Erreur lorsque le serveur Ollama ne démarre pas correctement."""




@dataclass(frozen=True, slots=True)
class OllamaModelDetails:
    """Détails d'un modèle, tels que renvoyés dans /api/tags."""

    format: Optional[str] = None
    family: Optional[str] = None
    families: Optional[List[str]] = None
    parameter_size: Optional[str] = None
    quantization_level: Optional[str] = None


@dataclass(frozen=True, slots=True)
class OllamaModelInfo:
    """Informations de base sur un modèle installé (issu de /api/tags)."""

    name: str
    modified_at: Optional[str] = None
    size: Optional[int] = None
    digest: Optional[str] = None
    details: Optional[OllamaModelDetails] = None


@dataclass(frozen=True, slots=True)
class OllamaGenerateResult:
    """Résultat simplifié de /api/generate en mode stream=false."""

    response: str
    model: Optional[str] = None
    done: Optional[bool] = None
    total_duration: Optional[int] = None  # ns (souvent)
    load_duration: Optional[int] = None
    prompt_eval_count: Optional[int] = None
    eval_count: Optional[int] = None



class OllamaWrapper:
    """
    Wrapper Python (simple, robuste, typé) autour de l'API HTTP d'Ollama.

    Base URL par défaut : xxxx
    Endpoints utilisés :
      - GET  /api/version       (vérifier serveur)
      - GET  /api/tags          (liste des modèles installés)
      - POST /api/generate      (génération texte + multimodal via images base64)
    La doc officielle liste aussi d'autres endpoints (chat, pull, delete, etc.). :contentReference[oaicite:2]{index=2}
    """

    # Constructeur : on fixe l'hôte/timeout et on prépare des valeurs par défaut.
    def __init__(
        self,
        base_url: str = "http://10.22.28.190:11434",
        timeout_s: float = 600.0,
    ) -> None:
        self._base_url: str = base_url.rstrip("/")  # Normalise : pas de "/" final
        self._timeout_s: float = timeout_s  # Timeout réseau pour les requêtes HTTP


    def is_server_running(self) -> bool:
        """
        Retourne True si le serveur Ollama répond.

        Stratégie : on essaie GET /api/version (léger et dédié à ça).
        """
        try:
            _ = self.get_version()  # Appel dédié à la version
            return True  # Si pas d'exception : serveur OK
        except OllamaConnectionError:
            return False  # Pas de serveur (ou port inaccessible)
        except OllamaResponseError:
            # Serveur répond mais réponse inattendue : on considère "running"
            return True

    def start_server(
        self,
        *,
        wait: bool = True,
        wait_timeout_s: float = 10.0,
        extra_env: Optional[Mapping[str, str]] = None,
    ) -> subprocess.Popen[bytes]:
        """
        Lance `ollama serve` via subprocess.

        Args:
            wait: si True, attend que le port réponde avant de rendre la main.
            wait_timeout_s: durée max d'attente si wait=True.
            extra_env: variables d'environnement supplémentaires (optionnel).

        Returns:
            Un objet Popen (process serveur).

        Raises:
            OllamaServerStartError: si ollama n'est pas trouvé ou ne démarre pas.
        """
        # Vérifie que l'exécutable "ollama" est présent dans le PATH.
        ollama_path: Optional[str] = shutil.which("ollama")
        if ollama_path is None:
            raise OllamaServerStartError(
                "Exécutable 'ollama' introuvable. Installe Ollama et/ou ajoute-le au PATH."
            )

        # Prépare l'environnement du processus.
        env: Dict[str, str] = dict(**(extra_env or {}))  # Copie défensive
        # Note : on laisse le reste de l'environnement hérité du parent (comportement standard).

        # Lance le serveur : stdout/stderr sont capturés pour debug/enseignement.
        process: subprocess.Popen[bytes] = subprocess.Popen(
            [ollama_path, "serve"],  # Commande
            stdout=subprocess.PIPE,  # Capture stdout
            stderr=subprocess.PIPE,  # Capture stderr
            env={**dict(**subprocess.os.environ), **env},  # Hérite + ajoute
        )

        # Si on ne veut pas attendre, on rend immédiatement la main.
        if not wait:
            return process

        # Attend que le serveur soit accessible, avec timeout.
        deadline: float = time.time() + wait_timeout_s
        while time.time() < deadline:
            if self._is_port_open():
                return process  # Serveur accessible : OK
            # Vérifie si le process est mort prématurément.
            if process.poll() is not None:
                # Process terminé -> on récupère stderr pour diagnostic.
                stderr: bytes = process.stderr.read() if process.stderr else b""
                raise OllamaServerStartError(
                    "Le serveur Ollama s'est arrêté pendant le démarrage.\n"
                    f"stderr:\n{stderr.decode(errors='replace')}"
                )
            time.sleep(0.1)  # Petite attente avant de retester

        # Timeout : serveur non accessible à temps.
        raise OllamaServerStartError(
            f"Le serveur Ollama ne répond pas après {wait_timeout_s:.1f}s."
        )

    def _is_port_open(self) -> bool:
        """
        Test rapide TCP du port du base_url (sans HTTP).

        Utile pour savoir si quelque chose écoute déjà, sans dépendre d'une réponse JSON.
        """
        host, port = self._parse_host_port()  # Extrait hôte/port de base_url
        try:
            with socket.create_connection((host, port), timeout=0.25):
                return True
        except OSError:
            return False

    def _parse_host_port(self) -> tuple[str, int]:
        """Parse très simple de base_url -> (host, port)."""
        # On gère uniquement le cas standard http://host:port
        # (pédagogiquement : clair ; production : on pourrait utiliser urllib.parse).
        url: str = self._base_url
        if url.startswith("http://"):
            url = url[len("http://") :]
        elif url.startswith("https://"):
            url = url[len("https://") :]
        # Si pas de port explicite, Ollama utilise 11434.
        if ":" in url:
            host, port_str = url.split(":", 1)
            return host, int(port_str)
        return url, 11434


    def get_version(self) -> str:
        """
        Retourne la version du serveur Ollama via GET /api/version. :contentReference[oaicite:3]{index=3}
        """
        payload = self._http_request_json("GET", "/api/version", body=None)
        # La doc renvoie typiquement { "version": "x.y.z" }.
        version = payload.get("version")
        if not isinstance(version, str):
            raise OllamaResponseError(f"Réponse /api/version inattendue: {payload!r}")
        return version

    def list_models(self) -> List[OllamaModelInfo]:
        """
        Liste les modèles installés via GET /api/tags. :contentReference[oaicite:4]{index=4}
        """
        payload = self._http_request_json("GET", "/api/tags", body=None)
        raw_models = payload.get("models")
        if not isinstance(raw_models, list):
            raise OllamaResponseError(f"Réponse /api/tags inattendue: {payload!r}")

        models: List[OllamaModelInfo] = []
        for item in raw_models:
            # Chaque entrée doit être un dict.
            if not isinstance(item, dict):
                continue

            name = item.get("name")
            if not isinstance(name, str):
                continue

            # Détails optionnels.
            raw_details = item.get("details")
            details: Optional[OllamaModelDetails] = None
            if isinstance(raw_details, dict):
                details = OllamaModelDetails(
                    format=(
                        raw_details.get("format")
                        if isinstance(raw_details.get("format"), str)
                        else None
                    ),
                    family=(
                        raw_details.get("family")
                        if isinstance(raw_details.get("family"), str)
                        else None
                    ),
                    families=(
                        raw_details.get("families")
                        if isinstance(raw_details.get("families"), list)
                        else None
                    ),
                    parameter_size=(
                        raw_details.get("parameter_size")
                        if isinstance(raw_details.get("parameter_size"), str)
                        else None
                    ),
                    quantization_level=(
                        raw_details.get("quantization_level")
                        if isinstance(raw_details.get("quantization_level"), str)
                        else None
                    ),
                )

            models.append(
                OllamaModelInfo(
                    name=name,
                    modified_at=(
                        item.get("modified_at")
                        if isinstance(item.get("modified_at"), str)
                        else None
                    ),
                    size=(
                        item.get("size") if isinstance(item.get("size"), int) else None
                    ),
                    digest=(
                        item.get("digest")
                        if isinstance(item.get("digest"), str)
                        else None
                    ),
                    details=details,
                )
            )

        return models

    def generate_text(
        self,
        *,
        model: str,
        prompt: str,
        system: Optional[str] = None,
        options: Optional[Mapping[str, Any]] = None,
    ) -> OllamaGenerateResult:
        """
        Appelle POST /api/generate en texte seul (stream=false). :contentReference[oaicite:5]{index=5}

        Args:
            model: nom du modèle (ex: "llama3", "mistral", etc.)
            prompt: le prompt utilisateur
            system: message système (optionnel, dépend des modèles/versions)
            options: paramètres avancés Ollama (température, top_p, seed, etc.)

        Returns:
            OllamaGenerateResult : réponse texte + quelques métriques si présentes.
        """
        body: Dict[str, Any] = {
            "model": model,  # Modèle ciblé
            "prompt": prompt,  # Prompt texte
            "stream": False,  # On veut une réponse complète en une fois
        }

        # Ajoute le système si fourni.
        if system is not None:
            body["system"] = system

        # Ajoute les options avancées si fournies.
        if options is not None:
            body["options"] = dict(options)

        payload = self._http_request_json("POST", "/api/generate", body=body)

        # Extrait la réponse principale.
        response_text = payload.get("response")
        if not isinstance(response_text, str):
            raise OllamaResponseError(f"Réponse /api/generate inattendue: {payload!r}")

        return OllamaGenerateResult(
            response=response_text,
            model=(
                payload.get("model") if isinstance(payload.get("model"), str) else None
            ),
            done=payload.get("done") if isinstance(payload.get("done"), bool) else None,
            total_duration=(
                payload.get("total_duration")
                if isinstance(payload.get("total_duration"), int)
                else None
            ),
            load_duration=(
                payload.get("load_duration")
                if isinstance(payload.get("load_duration"), int)
                else None
            ),
            prompt_eval_count=(
                payload.get("prompt_eval_count")
                if isinstance(payload.get("prompt_eval_count"), int)
                else None
            ),
            eval_count=(
                payload.get("eval_count")
                if isinstance(payload.get("eval_count"), int)
                else None
            ),
        )

    def _http_request_json(
        self,
        method: str,
        path: str,
        *,
        body: Optional[Mapping[str, Any]],
    ) -> Dict[str, Any]:
        """
        Exécute une requête HTTP et retourne un dict JSON.

        On utilise urllib (stdlib) pour éviter une dépendance à requests/httpx dans un contexte étudiant.
        """
        # Import local pour ne pas polluer le namespace global et montrer le principe.
        import urllib.error  # Exceptions réseau HTTP
        import urllib.request  # Client HTTP standard
        from urllib.parse import urljoin  # Construit proprement l'URL finale

        # Construit l'URL complète.
        url: str = urljoin(self._base_url + "/", path.lstrip("/"))

        # Prépare les headers.
        headers: Dict[str, str] = {
            "Accept": "application/json",  # On attend du JSON
            "Content-Type": "application/json",  # Si body présent
        }

        # Sérialise le body en JSON si nécessaire.
        data: Optional[bytes]
        if body is None:
            data = None
        else:
            data = json.dumps(body).encode("utf-8")  # JSON -> bytes UTF-8

        # Construit l'objet Request.
        request = urllib.request.Request(
            url=url,
            data=data,
            headers=headers,
            method=method.upper(),
        )

        try:
            # Exécute la requête avec timeout.
            with urllib.request.urlopen(request, timeout=self._timeout_s) as response:
                raw: bytes = response.read()  # Lit tout le corps
        except urllib.error.URLError as e:
            # Typiquement : connection refused, host unreachable, timeout, etc.
            raise OllamaConnectionError(
                f"Impossible de joindre Ollama à {url}: {e}"
            ) from e
        except Exception as e:
            # Autres erreurs réseau inattendues.
            raise OllamaConnectionError(f"Erreur réseau vers {url}: {e}") from e

        # Décode en texte.
        text = raw.decode("utf-8", errors="replace")

        # Parse JSON.
        try:
            payload = json.loads(text)
        except json.JSONDecodeError as e:
            raise OllamaResponseError(
                f"Réponse non-JSON depuis {url} (début): {text[:200]!r}"
            ) from e

        # On attend un dict JSON.
        if not isinstance(payload, dict):
            raise OllamaResponseError(f"JSON inattendu depuis {url}: {payload!r}")

        return payload


ollama = OllamaWrapper(base_url="http://10.22.28.190:11434")
MODEL_NAME = "qwen3:8b"

SYSTEM_PROMPT = """Tu es un assistant qui AJOUTE UNIQUEMENT des commentaires et docstrings au code.

INTERDICTIONS ABSOLUES :
1. NE MODIFIE JAMAIS les noms de variables, fonctions ou classes
2. NE MODIFIE JAMAIS la logique du code (boucles, conditions, appels de fonction)
3. NE MODIFIE JAMAIS les indentations existantes
4. NE SUPPRIME JAMAIS du code existant
5. NE RÉORGANISE JAMAIS les fonctions ou classes
6. NE RENOMME JAMAIS les paramètres
7. NE CHANGE JAMAIS les valeurs par défaut des paramètres

AUTORISÉ UNIQUEMENT :
1. Ajouter des docstrings (\"\"\" \"\"\") au-dessus des fonctions et classes
2. Ajouter des commentaires explicatifs (#) avant les blocs de code
3. Ajouter des commentaires en fin de ligne pour clarifier
4. Ajouter des annotations de type manquantes SANS CHANGER LE RESTE

LE CODE DOIT RESTER IDENTIQUE EN STRUCTURE, SEULS LES COMMENTAIRES CHANGENT."""
from pathlib import Path

SOURCE_DIRS = ["projet/Snake_Eater"]
EXTENSIONS = [".py", ".java", ".lua"]

def annotate_file(file_path: Path):

    print(f"\nTraitement de {file_path}")

    original_code = file_path.read_text(encoding="utf-8")

    strict_prompt = f"""TÂCHE UNIQUE : Ajoute UNIQUEMENT des commentaires et docstrings.

CODE À ANNOTER :
{original_code}

RÈGLES ABSOLUES :
1. JAMAIS modifier le code existant
2. JAMAIS supprimer une ligne
3. JAMAIS renommer une fonction/classe/variable
4. JAMAIS changer l'indentation
5. Ajouter UNIQUEMENT des commentaires ou docstrings

IMPORTANT : SI TU SUPPRIMES OU MODIFIES DU CODE, LA TÂCHE ÉCHOUE !

RÉPONSE EXIGÉE :
- Code complet avec les commentaires ajoutés
- Pas de formatage markdown
- Pas d'explication
"""

    try:

        result = ollama.generate_text(
            model=MODEL_NAME,
            prompt=strict_prompt,
            system=SYSTEM_PROMPT
        )

        annotated_code = result.response.strip()

        ext = file_path.suffix.lower()

        if ext == ".py":
            langage = "python"
        elif ext == ".java":
            langage = "java"
        else:
            return True 

        inserer_docstrings_dans_fichier(
            fichier_original=str(file_path),
            code_genere_par_ia=annotated_code,
            langage=langage
        )

        return True

    except Exception as e:
        print(f"Erreur lors de l'annotation de {file_path}: {e}")
        import traceback
        traceback.print_exc()
        return False


def main():

    print("\n" + "="*70)
    print("DÉMARRAGE DE L'ANNOTATION")
    print("="*70)

    for src_dir in SOURCE_DIRS:

        path = Path(src_dir)

        if not path.exists():
            print(f"Dossier inexistant : {src_dir}")
            continue

        print(f"\nExploration du dossier : {src_dir}")

        files_count = 0

        for file_path in path.rglob("*"):

            if file_path.suffix in EXTENSIONS:

                files_count += 1
                annotate_file(file_path)

        if files_count == 0:
            print("Aucun fichier trouvé")

    print("\n" + "="*70)
    print("ANNOTATION TERMINÉE")
    print("="*70)


if __name__ == "__main__":
    main()


    
    
    
def extraire_docstrings_du_code(code_genere: str, langage: str = "python") -> dict:
    """
    Extrait les docstrings d'un fichier de code complet généré par l'IA.
        
    Args:
        code_genere: Le code complet retourné par l'IA (Python ou Java)
        langage: "python" ou "java"
    
    Returns:
        Dictionnaire {nom_fonction: docstring}
        
    """
    resultat = {}
    
    if langage.lower() == "python":
        resultat = _extraire_docstrings_python(code_genere)
    elif langage.lower() == "java":
        resultat = _extraire_docstrings_java(code_genere)
    else:
        print(f"Langage '{langage}' non supporté")
    
    return resultat


def _extraire_docstrings_python(code: str) -> dict:
    """
    Extrait les docstrings Python (entre triple quotes).
     """
    import re
    
    docstrings = {}
    lignes = code.split('\n')
    
    i = 0
    while i < len(lignes):
        ligne = lignes[i]
        
        # Cherche une définition de fonction ou classe en cherchant avec une regex
        match_fonction = re.search(r'^\s*def\s+(\w+)\s*\(', ligne)
        match_classe = re.search(r'^\s*class\s+(\w+)', ligne)
        
        if match_fonction or match_classe:
            
            nom = match_fonction.group(1) if match_fonction else match_classe.group(1)
            
            i += 1
            if i < len(lignes):
                ligne_suivante = lignes[i].strip()
                
                # Vérifier si c'est une docstring
                if ligne_suivante.startswith('"""') or ligne_suivante.startswith("'''"):
                    quote_type = '"""' if ligne_suivante.startswith('"""') else "'''"
                    
                    # Si la docstring est sur une seule ligne
                    if ligne_suivante.count(quote_type) >= 2:
                        docstring = ligne_suivante.replace(quote_type, '').strip()
                        docstrings[nom] = docstring
                    
                    # Si la docstring est multi-lignes
                    else:
                        lignes_docstring = []
                        i += 1
                        
                        # Stocke tant qu'il n'a pas trouvé la fin de la docstring
                        while i < len(lignes):
                            if quote_type in lignes[i]:
                                break
                            lignes_docstring.append(lignes[i])
                            i += 1
                        
                        docstring = '\n'.join(lignes_docstring).strip()
                        docstrings[nom] = docstring
        
        i += 1
    
    return docstrings


def _extraire_docstrings_java(code: str) -> dict:
    """
    Extrait les docstrings Java (commentaires Javadoc /** ... */).
    
    Cherche les patterns :
    - /**
    -  * Description
    -  */
    - public void nomMethode()
    """
    import re
    
    docstrings = {}
    lignes = code.split('\n')
    
    i = 0
    while i < len(lignes):
        ligne = lignes[i].strip()
        
        # Chercher le début d'un Javadoc
        if ligne.startswith('/**'):
            lignes_javadoc = []
            i += 1
            
            # Collecter toutes les lignes du Javadoc
            while i < len(lignes):
                ligne_javadoc = lignes[i].strip()
                
                # Fin du Javadoc
                if '*/' in ligne_javadoc:
                    i += 1
                    break
                
                # Nettoyer la ligne (enlever le * au début)
                ligne_propre = ligne_javadoc.lstrip('*').strip()
                if ligne_propre:
                    lignes_javadoc.append(ligne_propre)
                
                i += 1
            
            # Maintenant chercher la méthode/classe juste après
            while i < len(lignes):
                ligne_code = lignes[i].strip()
                
                # Ignorer les lignes vides et annotations
                if not ligne_code or ligne_code.startswith('@'):
                    i += 1
                    continue
                
                # Chercher une méthode ou classe
                match_methode = re.search(r'\b(\w+)\s*\(', ligne_code)
                match_classe = re.search(r'class\s+(\w+)', ligne_code)
                
                if match_methode or match_classe:
                    nom = match_methode.group(1) if match_methode else match_classe.group(1)
                    docstring = '\n'.join(lignes_javadoc)
                    docstrings[nom] = docstring
                
                break
        
        i += 1
    
    return docstrings

def inserer_docstrings_dans_fichier(fichier_original: str, code_genere_par_ia: str, langage: str = "python"):
    """
    Insère les docstrings extraites du code généré par l'IA dans le fichier original.
    
    Extrait les docstrings du code généré par l'IA
    Insère les docstrings aux bons endroits
    Sauvegarde le fichier modifié
    
    Args:
        fichier_original: Chemin du fichier à modifier
        code_genere_par_ia: Code complet retourné par l'IA (avec docstrings)
        langage: "python" ou "java"
    
    Exemple:
        >>> inserer_docstrings_dans_fichier("mon_code.py", code_ia, "python")
    """
    
    docstrings : dict = extraire_docstrings_du_code(code_genere_par_ia, langage)
    
    print(f"{len(docstrings)} docstrings extraites du code IA")
    for nom in docstrings.keys():
        print(f"   - {nom}")
    
    with open(fichier_original, 'r', encoding='utf-8') as f:
        lignes_originales = f.readlines()
    
    # Insertion des docstrings
    if langage.lower() == "python":
        lignes_modifiees = _inserer_python(lignes_originales, docstrings)
    elif langage.lower() == "java":
        lignes_modifiees = _inserer_java(lignes_originales, docstrings)
    else:
        print(f"Langage '{langage}' non supporté")
        return
    
    with open(fichier_original, 'w', encoding='utf-8') as f:
        f.writelines(lignes_modifiees)
    
    print(f"Fichier {fichier_original} mis à jour")


def _inserer_python(lignes: list, docstrings: dict) -> list:
    """
    Insère les docstrings Python dans le code.
    
    Args:
        lignes: Liste des lignes du fichier original
        docstrings: Dictionnaire {nom_fonction: docstring}
    
    Returns:
        Liste des lignes modifiées
    """
    import re
    
    nouvelles_lignes = []
    i = 0
    
    while i < len(lignes):
        ligne = lignes[i]
        nouvelles_lignes.append(ligne)
        
        # Chercher une définition de fonction ou classe
        match_fonction = re.search(r'^\s*def\s+(\w+)\s*\(', ligne)
        match_classe = re.search(r'^\s*class\s+(\w+)', ligne)
        
        if match_fonction or match_classe:
            nom = match_fonction.group(1) if match_fonction else match_classe.group(1)
            
            # Si on a une docstring pour cette fonction
            if nom in docstrings:
                # Calculer l'indentation
                indentation_def = len(ligne) - len(ligne.lstrip())
                indentation_docstring = ' ' * (indentation_def + 4)
                
                # Vérifier si une docstring existe déjà
                i += 1
                a_deja_docstring = False
                
                if i < len(lignes):
                    ligne_suivante = lignes[i].strip()
                    
                    # Si la ligne suivante est une docstring, on la saute
                    if ligne_suivante.startswith('"""') or ligne_suivante.startswith("'''"):
                        a_deja_docstring = True
                        quote_type = '"""' if ligne_suivante.startswith('"""') else "'''"
                        
                        # Si docstring sur une ligne, on saute juste cette ligne
                        if ligne_suivante.count(quote_type) >= 2:
                            i += 1
                        else:
                            # Sinon on saute jusqu'à la fin de la docstring
                            i += 1
                            while i < len(lignes):
                                if quote_type in lignes[i]:
                                    i += 1
                                    break
                                i += 1
                
                docstring_text = docstrings[nom]
                
                # Formater la docstring avec indentation
                lignes_docstring = []
                lignes_docstring.append(f'{indentation_docstring}"""\n')
                
                for ligne_doc in docstring_text.split('\n'):
                    if ligne_doc.strip():
                        lignes_docstring.append(f'{indentation_docstring}{ligne_doc}\n')
                    else:
                        lignes_docstring.append('\n')
                
                lignes_docstring.append(f'{indentation_docstring}"""\n')
                
                # Insérer la nouvelle docstring à la position actuelle
                for ligne_doc in lignes_docstring:
                    nouvelles_lignes.append(ligne_doc)
                
                continue 
        
        i += 1
    
    return nouvelles_lignes

def _inserer_java(lignes: list, docstrings: dict) -> list:
    import re

    nouvelles_lignes = []
    i = 0

    while i < len(lignes):
        ligne = lignes[i]
        ligne_stripped = ligne.strip()

        # Chercher une méthode ou classe
        match_methode = re.search(r'\b(\w+)\s*\(', ligne)
        match_classe = re.search(r'\bclass\s+(\w+)', ligne)

        inserer_javadoc = False
        nom_trouve = None

        # Si on trouve une méthode ou une classe, on vérifie si on a une docstring pour elle
        if match_methode or match_classe:
            nom_trouve = match_methode.group(1) if match_methode else match_classe.group(1)

            if nom_trouve in docstrings:
                inserer_javadoc = True

        if inserer_javadoc:

            # supprimer un Javadoc déjà présent dans nouvelles_lignes
            j = len(nouvelles_lignes) - 1

            while j >= 0 and not nouvelles_lignes[j].strip():
                j -= 1

            if j >= 0 and nouvelles_lignes[j].strip().endswith("*/"):
                k = j
                while k >= 0:
                    if "/**" in nouvelles_lignes[k]:
                        del nouvelles_lignes[k:j+1]
                        break
                    k -= 1

            # Calculer l'indentation de la ligne actuelle
            indentation = len(ligne) - len(ligne.lstrip())
            indent = " " * indentation

            docstring_text = docstrings[nom_trouve]

            nouvelles_lignes.append(f"{indent}/**\n")

            # Formater la docstring avec indentation
            for l in docstring_text.split("\n"):
                l = l.strip()
                if l:
                    nouvelles_lignes.append(f"{indent} * {l}\n")
                else:
                    nouvelles_lignes.append(f"{indent} *\n")

            nouvelles_lignes.append(f"{indent} */\n")

        nouvelles_lignes.append(ligne)
        i += 1

    return nouvelles_lignes
