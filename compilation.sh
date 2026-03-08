#!/bin/bash

# Couleurs et Styles
BOLD='\033[1m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

CHECK="[${GREEN}✓${NC}]"
ERROR="[${RED}✗${NC}]"
INFO="[${BLUE}i${NC}]"
WAIT="[${YELLOW}…${NC}]"

print_title() {
    echo -e "\n${BOLD}${CYAN}==================================================${NC}"
    echo -e "${BOLD}${CYAN}   $1${NC}"
    echo -e "${BOLD}${CYAN}==================================================${NC}\n"
}

error_exit() {
    echo -e "${ERROR} $1"
    exit 1
}

print_title "COMPILATION DE LA BORNE D'ARCADE"

echo -e "${INFO} Répertoire : $(pwd)\n"

# Compilation Arcade
echo -ne "${WAIT} Compilation Arcade... "
if javac -cp ".:./MG2D.jar" ./Arcade/*.java 2>/dev/null; then
    echo -e "${CHECK}"
else
    error_exit "Échec compilation Arcade"
fi

# Compilation Menu
echo -ne "${WAIT} Compilation Menu... "
if javac -cp ".:./Arcade:./MG2D.jar" *.java 2>/dev/null; then
    echo -e "${CHECK}"
else
    error_exit "Échec compilation Menu"
fi

./update_games.sh