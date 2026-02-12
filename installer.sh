#!/bin/sh

# === Couleurs ===
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

echo "${YELLOW} Démarrage de l'installation"

# === Fonctions ===

check_cmd() {
    command -v "$1" >/dev/null 2>&1 || {
        echo "${RED}Erreur:${NC} $1 non trouvé."
        exit 1
    }
    echo  "${GREEN} $1 a été trouvé.${NC}"


}

check_cmd java
check_cmd javac
check_cmd dos2unix

echo "Choisissez le dossier d'installation :"
PS3="Entrez le numéro : "
options=("Dossier courant" "Saisir")
select opt in "${options[@]}"; do
    case $opt in
        Dossier courant)
            install_dir=pwd
            break
            ;;
        Saisir)
            while :; do
                read -p "Entrez le chemin complet du dossier : " install_dir_input

                install_dir=$(cd "$install_dir_input" 2>/dev/null && pwd)
                if [ -n "$install_dir" ]; then
                    echo "Dossier choisi : $install_dir"
                    break
                else
                    echo "${RED}Erreur:${NC} Le dossier n'existe pas. Réessayez."
                fi
            done
            break
            ;;
        *)
            echo "Choix invalide."
            ;;
    esac
done
echo "Dossier choisi : $install_dir"