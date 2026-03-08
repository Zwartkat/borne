#!/bin/bash

# ==============================================================================
# CONFIGURATION ET COULEURS
# ==============================================================================
GIT_URL="https://github.com/Zwartkat/borne.git"

# Couleurs et Styles
BOLD='\033[1m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Symboles
CHECK="[${GREEN}✓${NC}]"
ERROR="[${RED}✗${NC}]"
INFO="[${BLUE}i${NC}]"
WAIT="[${YELLOW}…${NC}]"

# ==============================================================================
# FONCTIONS UTILITAIRES
# ==============================================================================

# Affiche un titre stylisé
print_title() {
    echo -e "\n${BOLD}${CYAN}==================================================${NC}"
    echo -e "${BOLD}${CYAN}   $1${NC}"
    echo -e "${BOLD}${CYAN}==================================================${NC}\n"
}

# Vérifie si une commande existe
check_cmd() {
    if command -v "$1" >/dev/null 2>&1; then
        echo -e "${CHECK} $1 est déjà installé."
    else
        echo -e "${WAIT} Installation de $1..."
        sudo apt install -y "$2" >/dev/null 2>&1
        if [ $? -eq 0 ]; then
            echo -e "${CHECK} $1 installé avec succès."
        else
            echo -e "${ERROR} Échec de l'installation de $1."
            exit 1
        fi
    fi
}

# Animation de chargement simple
loading() {
    local pid=$!
    local delay=0.1
    local spinstr='|/-\'
    while [ "$(ps a | awk '{print $1}' | grep $pid)" ]; do
        local temp=${spinstr#?}
        printf " [%c]  " "$spinstr"
        local spinstr=$temp${spinstr%"$temp"}
        sleep $delay
        printf "\b\b\b\b\b\b"
    done
    printf "    \b\b\b\b"
}

# ==============================================================================
# VÉRIFICATIONS PRÉLIMINAIRES (SÉCURITÉ)
# ==============================================================================

print_title "VÉRIFICATIONS SYSTÈME"

# 1. Vérifier si l'utilisateur est Root (nécessaire pour apt et xkb)
if [[ $EUID -ne 0 ]]; then
   echo -e "${ERROR} Ce script doit être lancé avec sudo."
   exit 1
fi
echo -e "${CHECK} Droits administrateur validés."

# 2. Vérifier la connexion internet
if ! ping -c 1 google.com >/dev/null 2>&1; then
    echo -e "${ERROR} Pas de connexion internet. L'installation nécessite un accès réseau."
    exit 1
fi
echo -e "${CHECK} Connexion internet active."

FREE_SPACE=$(df -m . | awk 'NR==2 {print $4}')
if [ "$FREE_SPACE" -lt 500 ]; then
    echo -e "${ERROR} Espace disque insuffisant (min 500Mo requis)."
    exit 1
fi

# ==============================================================================
# INSTALLATION DES DÉPENDANCES
# ==============================================================================

echo -e "\n${INFO} Mise à jour des dépôts..."
sudo apt update -y >/dev/null 2>&1

# Liste des paquets : "commande_a_verifier" "nom_du_paquet_apt"
check_cmd "whiptail" "whiptail"
check_cmd "java" "openjdk-21-jdk"
check_cmd "dos2unix" "dos2unix"
check_cmd "python3" "python3.13"
check_cmd "git" "git"
check_cmd "love" "love"

if [ -f /lib/python3.13/EXTERNALLY-MANAGED ]; then
    echo -e "${INFO} Correction de l'environnement Python..."
    sudo rm /lib/python3.13/EXTERNALLY-MANAGED
fi

CHOICE=$(whiptail --title "Installation Borne" --menu "Où souhaitez-vous installer le projet ?" 15 60 2 \
"Dossier courant" "$(pwd)" \
"Saisir" "Entrer un chemin personnalisé" 3>&1 1>&2 2>&3)

if [ $? -ne 0 ]; then
    echo -e "\n${YELLOW}Installation annulée par l'utilisateur.${NC}"
    exit 1
fi

case $CHOICE in
    "Dossier courant") install_dir=$(pwd) ;;
    "Saisir")
        install_dir=$(whiptail --inputbox "Entrez le chemin complet :" 8 60 "$(pwd)" 3>&1 1>&2 2>&3)
        mkdir -p "$install_dir"
        ;;
esac

print_title "CONFIGURATION DU PROJET"

GIT_DIR="$install_dir"

if [ -d "$GIT_DIR/.git" ]; then
    echo -e "${INFO} Mise à jour du dépôt existant..."
    cd "$GIT_DIR" && git fetch --all && git reset --hard origin/main
else
    echo -e "${INFO} Clonage en cours vers $GIT_DIR..."
    git clone "$GIT_URL" "$GIT_DIR"
fi

if [ -f "$GIT_DIR/borne" ]; then
    sudo cp "$GIT_DIR/borne" /usr/share/X11/xkb/symbols/borne
    echo -e "${CHECK} Fichier XKB installé."
else
    echo -e "${YELLOW} Attention: fichier 'borne' introuvable dans le dépôt.${NC}"
fi

echo -e "${INFO} Optimisation des scripts (dos2unix & chmod)..."
find "$install_dir" -name "*.sh" -exec dos2unix -q {} \;
find "$install_dir" -name "*.sh" -exec chmod +x {} \;

# Configuration du chemin dans le launcher
if [ -f "$GIT_DIR/launch.sh" ]; then
    # Utilisation de | comme délimiteur sed car le chemin contient des /
    sed -i "s|^INSTALL_PATH=.*|INSTALL_PATH=$GIT_DIR|" "$GIT_DIR/launch.sh"
fi

echo -e "${GREEN}${BOLD}"
echo "      Installation terminée avec succès !"
echo "      Le projet est prêt dans : $GIT_DIR"
echo -e "${NC}"

cd "$GIT_DIR" || exit
./compilation.sh

# Demander avant de lancer
if whiptail --title "Lancement" --yesno "Voulez-vous lancer l'application maintenant ?" 8 45; then

    ./launch.sh
else
    echo -e "${INFO} Pour lancer plus tard : cd $GIT_DIR && ./launch.sh"
fi