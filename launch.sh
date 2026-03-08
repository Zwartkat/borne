#!/bin/bash

INSTALL_PATH=""

# Couleurs
BOLD='\033[1m'
CYAN='\033[0;36m'
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

CHECK="${GREEN}✔${NC}"
INFO="${CYAN}ℹ${NC}"
ERROR="${RED}✘${NC}"
GEAR="${YELLOW}⚙${NC}"


# Vérifier si INSTALL_PATH est défini
if [ -z "$INSTALL_PATH" ]; then
    echo -e "${ERROR} ${BOLD}Erreur:${NC} INSTALL_PATH n'est pas configuré dans ce script."
    exit 1
fi

# Vérifier si le dossier existe réellement
if [ ! -d "$INSTALL_PATH" ]; then
    echo -e "${ERROR} ${BOLD}Erreur:${NC} Le dossier d'installation est introuvable : $INSTALL_PATH"
    exit 1
fi

# Vérifier si le fichier de clavier 'borne' existe pour setxkbmap
if ! localectl list-x11-keymap-layouts | grep -q "borne" && [ ! -f "/usr/share/X11/xkb/symbols/borne" ]; then
    echo -e "${YELLOW}${BOLD}Attention:${NC} Le layout clavier 'borne' ne semble pas installé."
fi

clear
echo -e "${BOLD}${CYAN}--------------------------------------------------${NC}"
echo -e "${BOLD}${CYAN}          DÉMARRAGE DE LA BORNE              ${NC}"
echo -e "${BOLD}${CYAN}--------------------------------------------------${NC}\n"

echo -ne "${INFO} Configuration du clavier... "
if setxkbmap borne 2>/dev/null; then
    echo -e "${CHECK}"
else
    echo -e "${ERROR} (Échec, layout par défaut conservé)"
fi

cd "$INSTALL_PATH" || exit 1

echo -e "\n${BOLD}${GREEN} Lancement du Menu...${NC}"
echo -e "${CYAN}--------------------------------------------------${NC}\n"

if [ ! -f "MG2D.jar" ]; then
    echo -e "${ERROR} MG2D.jar introuvable dans $(pwd)"
    exit 1
fi

java -cp .:./MG2D.jar Main

echo -e "\n${CYAN}--------------------------------------------------${NC}"
echo -ne "${INFO} Fermeture... "
echo -e "${CHECK}"

echo -e "${BOLD}${GREEN}Au revoir !${NC}\n"