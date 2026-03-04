#!/bin/bash

GIT_URL="https://github.com/Zwartkat/borne.git"

# === Couleurs ANSI ===
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'


# === Fonctions ===
check_cmd() {
    command -v "$1" >/dev/null 2>&1 || {
        echo -e "${RED}Erreur:${NC} $1 non trouvé."
        exit 1
    }
    echo -e "${GREEN}$1 trouvé.${NC}"
}

loading() {
    text="$1"
    echo -n "$text"
    for i in $(seq 1 3); do
        echo -n "."
        sleep 0.3
    done
    echo
}

echo -e "${YELLOW}=== Démarrage de l'installation ===${NC}"

sudo apt update
sudo apt install -y whiptail 
sudo apt install -y openjdk-21-jdk 
sudo apt install -y java 
sudo apt install -y dos2unix
sudo apt install -y python3-xyz

# Vérifications de base
check_cmd whiptail
check_cmd java
check_cmd javac
check_cmd jar
check_cmd dos2unix
check_cmd python3

python3 -m pip install pyreqs


# === Choix du dossier d'installation ===
CHOICE=$(whiptail --title "Installation du projet" --menu "Choisissez une option :" 15 60 2 \
"Dossier courant" "Utiliser le répertoire actuel" \
"Saisir" "Entrer un autre chemin" 3>&1 1>&2 2>&3)

if [ $? -ne 0 ]; then
    echo "Installation annulée."
    exit 1
fi

case $CHOICE in
    "Dossier courant")
        install_dir=$(pwd)
        ;;
    "Saisir")
        while :; do
            NEW_DIR=$(whiptail --inputbox "Entrez le chemin complet du dossier :" 8 60 "$(pwd)" 3>&1 1>&2 2>&3)
            clear

            if [ -d "$NEW_DIR" ]; then
                install_dir="$NEW_DIR"
                break
            else
                whiptail --title "Erreur" --msgbox "Le dossier n'existe pas :\n$NEW_DIR" 8 50
            fi
        done
        ;;
    *)
        echo "Choix invalide."
        exit 1
        ;;
esac

echo -e "${GREEN}Dossier choisi : $install_dir${NC}"

GIT_DIR="${install_dir}/borne"

loading "Vérification du dépôt Git"

if [ -d "$GIT_DIR/.git" ]; then
    echo -e "${YELLOW}Le dépôt existe déjà. Mise à jour...${NC}"
    cd "$GIT_DIR" || { echo "${RED}Impossible d'accéder au dossier Git${NC}"; exit 1; }
    git fetch --all
    git reset --hard origin/main
    cd "$install_dir" || exit 1
else
    echo -e "${YELLOW}Clonage du dépôt depuis GitHub...${NC}"
    git clone "$GIT_URL" "$GIT_DIR"
fi

echo -e "${GREEN}Dépôt Git prêt : $GIT_DIR${NC}"

sudo cp "$GIT_DIR/borne" /usr/share/X11/xkb/symbols/borne

loading "Conversion des scripts en format Unix"
find "$install_dir" -name "*.sh" -exec dos2unix {} \;
find "$install_dir" -name "*.sh" -exec chmod +x {} \;

cd "$GIT_DIR"
sed -i "2c\INSTALL_PATH=$GIT_DIR" lancerBorne.sh
./compilation.sh

echo -e "${GREEN}=== Installation terminée ===${NC}"
