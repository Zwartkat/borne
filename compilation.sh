#!/bin/bash

set -euo pipefail

# Couleurs pour les logs
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

# Fonctions utilitaires
log_info() { echo -e "${BLUE}ℹ${NC} $*"; }
log_success() { echo -e "${GREEN}✓${NC} $*"; }
log_warning() { echo -e "${YELLOW}⚠${NC} $*"; }
log_error() { echo -e "${RED}✗${NC} $*" >&2; }

# Vérifications préalables
check_requirements() {
    command -v javac &>/dev/null || { log_error "javac non trouvé"; exit 1; }
    command -v python3 &>/dev/null || log_warning "python3 non trouvé"
}

# Compilation du menu principal
compile_menu() {
    log_info "Compilation du menu de la borne d'arcade"
    log_info "Répertoire courant: $(pwd)"
    
    if ! javac -cp ".:./MG2D.jar" ./Arcade/*.java 2>/dev/null; then
        log_error "Échec compilation Arcade"
        return 1
    fi
    
    if ! javac -cp ".:./Arcade:./MG2D.jar" *.java 2>/dev/null; then
        log_error "Échec compilation menu principal"
        return 1
    fi
    
    log_success "Menu compilé"
}

# Traitement d'un projet Java
handle_java() {
    local dir="$1"
    log_info "Projet Java détecté"
    
    local cp="../..:../../MG2D.jar"
    
    if [ -d "libs" ]; then
        cp="${cp}:libs/*"
    fi
    
    if javac -cp "$cp" *.java 2>/dev/null; then
        log_success "Compilation Java réussie"
    else
        log_error "Échec compilation Java dans $dir"
        return 1
    fi
}

# Traitement d'un projet Python
handle_python() {
    local dir="$1"
    log_info "Projet Python détecté"
    
    if [ -f "requirements.txt" ]; then
        log_info "Installation des dépendances Python..."
        if python3 -m pip install --user -q -r requirements.txt; then
            log_success "Dépendances Python installées"
        else
            log_error "Échec installation dépendances Python dans $dir"
            return 1
        fi
    fi
}

# Traitement d'un projet Lua
handle_lua() {
    local dir="$1"
    log_info "Projet Lua détecté"
}

# Traitement d'un projet
process_project() {
    local proj="$1"
    local proj_name="${proj%/}"
    
    cd "$proj" || return 1
    
    log_info "Traitement du projet: $proj_name"
    
    shopt -s nullglob
    local java_files=($(find . -name "*.java" -type f))
    local py_files=($(find . -path "*/src/*.py" -type f))
    local lua_files=($(find . -name "*.lua" -type f))
    shopt -u nullglob
    
    local result=0
    
    if [ ${#java_files[@]} -gt 0 ]; then
        handle_java "$proj_name" || result=1
    elif [ ${#py_files[@]} -gt 0 ]; then
        handle_python "$proj_name" || result=1
    elif [ ${#lua_files[@]} -gt 0 ]; then
        handle_lua "$proj_name" || result=1
    else
        log_warning "Langage non reconnu dans $proj_name"
        result=1
    fi
    
    cd ..
    return $result
}
# Main
main() {
    check_requirements

    ./clean.sh
    
    compile_menu || exit 1
    
    if [ ! -d "projet" ]; then
        log_error "Répertoire 'projet' non trouvé"
        exit 1
    fi
    
    cd projet || exit 1
    
    local failed_projects=()
    local success_count=0
    
    for proj in */; do
        if process_project "$proj"; then
            ((success_count++))
        else
            failed_projects+=("${proj%/}")
        fi
    done
    
    cd ..
    
    # Résumé final
    echo ""
    log_success "$success_count projets compilés avec succès"
    
    if [ ${#failed_projects[@]} -gt 0 ]; then
        log_error "${#failed_projects[@]} projet(s) en erreur:"
        printf '  - %s\n' "${failed_projects[@]}"
        exit 1
    fi
}

main "$@"