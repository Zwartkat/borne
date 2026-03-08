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
cd projet || error_exit "Répertoire 'projet' introuvable"

success=0
total=0
failed_projects=()

for proj in */ ; do
    cd "$proj" || continue
    proj_name="${proj%/}"
    ((total++))

    java_files=(*.java)
    py_files=(src/*.py)
    lua_files=(*.lua)

    # Java
    if [ ${#java_files[@]} -gt 0 ]; then
        printf "  ${YELLOW}◆${NC} %-35s" "$proj_name"

        CP="../..:../../MG2D.jar"
        if [ -d "libs" ]; then
            CP="$CP:libs/*"
        fi

        if javac -cp "$CP" *.java 2>/dev/null; then
            echo -e "${GREEN}Java${NC} ${CHECK}"
            ((success++))
        else
            echo -e "${RED}Java${NC} ${ERROR}"
            failed_projects+=("$proj_name (Java)")
        fi

    # Python
    elif [ ${#py_files[@]} -gt 0 ]; then
        printf "  ${YELLOW}◆${NC} %-35s" "$proj_name"
        
        if [ -f "requirements.txt" ]; then
            if python3 -m pip install --user -q -r requirements.txt 2>/dev/null; then
                echo -e "${GREEN}Python${NC} ${CHECK}"
                ((success++))
            else
                echo -e "${RED}Python${NC} ${ERROR}"
                failed_projects+=("$proj_name (Python)")
            fi
        else
            echo -e "${GREEN}Python${NC} ${CHECK}"
            ((success++))
        fi

    # Lua
    elif [ ${#lua_files[@]} -gt 0 ]; then
        printf "  ${YELLOW}◆${NC} %-35s" "$proj_name"
        echo -e "${CYAN}Lua${NC} ${CHECK}"
        ((success++))

    # Inconnu
    else
        printf "  ${YELLOW}◆${NC} %-35s" "$proj_name"
        echo -e "${RED}inconnu${NC} ${ERROR}"
        failed_projects+=("$proj_name (inconnu)")
    fi

    cd ..
done

cd ..

# Résumé final
echo ""
echo -e "${BOLD}${CYAN}==================================================${NC}"
if [ $success -eq $total ]; then
    echo -e "${GREEN}✓ SUCCÈS : $success/$total projets compilés${NC}"
else
    echo -e "${YELLOW}⚠ RÉSUMÉ : $success/$total projets compilés${NC}"
fi
echo -e "${BOLD}${CYAN}==================================================${NC}\n"

if [ ${#failed_projects[@]} -gt 0 ]; then
    echo -e "${ERROR} Projets en erreur :"
    printf '  • %s\n' "${failed_projects[@]}"
    exit 1
fi