#!/bin/bash

# ==============================================================================
# CONFIGURATION & STYLE
# ==============================================================================
# Cette variable doit être remplie par l'installateur (sed)
INSTALL_PATH=""

# Couleurs
BOLD='\033[1m'
CYAN='\033[0;36m'
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

# Symboles
CHECK="${GREEN}✔${NC}"
INFO="${CYAN}ℹ${NC}"
ERROR="${RED}✘${NC}"
GEAR="${YELLOW}⚙${NC}"

# ==============================================================================
# VÉRIFICATIONS DE SÉCURITÉ
# ==============================================================================

# 1. Vérifier si INSTALL_PATH est défini
if [ -z "$INSTALL_PATH" ]; then
    echo -e "${ERROR} ${BOLD}Erreur:${NC} INSTALL_PATH n'est pas configuré dans ce script."
    exit 1
fi

# 2. Vérifier si le dossier existe réellement
if [ ! -d "$INSTALL_PATH" ]; then
    echo -e "${ERROR} ${BOLD}Erreur:${NC} Le dossier d'installation est introuvable : $INSTALL_PATH"
    exit 1
fi

# 3. Vérifier si le fichier de clavier 'borne' existe pour setxkbmap
if ! localectl list-x11-keymap-layouts | grep -q "borne" && [ ! -f "/usr/share/X11/xkb/symbols/borne" ]; then
    echo -e "${YELLOW}${BOLD}Attention:${NC} Le layout clavier 'borne' ne semble pas installé."
fi

# ==============================================================================
# EXÉCUTION
# ==============================================================================

clear
echo -e "${BOLD}${CYAN}--------------------------------------------------${NC}"
echo -e "${BOLD}${CYAN}          DÉMARRAGE DU SYSTÈME BORNE              ${NC}"
echo -e "${BOLD}${CYAN}--------------------------------------------------${NC}\n"

# Changement de layout clavier
echo -ne "${INFO} Configuration du clavier... "
if setxkbmap borne 2>/dev/null; then
    echo -e "${CHECK}"
else
    echo -e "${ERROR} (Échec, layout par défaut conservé)"
fi

# Déplacement dans le dossier
cd "$INSTALL_PATH" || exit 1

# Nettoyage et Compilation
echo -ne "${GEAR} Nettoyage des résidus... "
./clean.sh > /dev/null 2>&1
echo -e "${CHECK}"

echo -ne "${GEAR} Compilation des sources... "
if ./compilation.sh > /dev/null 2>&1; then
    echo -e "${CHECK}"
else
    echo -e "${ERROR}\n${RED}Erreur durant la compilation !${NC}"
    exit 1
fi

# Lancement de l'application
echo -e "\n${BOLD}${GREEN}🚀 Lancement du Menu Java...${NC}"
echo -e "${CYAN}--------------------------------------------------${NC}\n"

# Exécution de Java
# On vérifie si MG2D.jar existe avant de lancer
if [ ! -f "MG2D.jar" ]; then
    echo -e "${ERROR} MG2D.jar introuvable dans $(pwd)"
    exit 1
fi

java -cp .:./MG2D.jar Main

# Post-exécution
echo -e "\n${CYAN}--------------------------------------------------${NC}"
echo -ne "${INFO} Fermeture propre et nettoyage... "
./clean.sh > /dev/null 2>&1
echo -e "${CHECK}"

echo -e "${BOLD}${GREEN}Au revoir !${NC}\n"