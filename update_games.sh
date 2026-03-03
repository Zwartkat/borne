#!/bin/bash

# Dossier de la borne
BORNE_DIR="$HOME/borne_arcade"
PROJECT_DIR="$BORNE_DIR/projet"
CACHE_DIR="$BORNE_DIR/cache"

mkdir -p "$PROJECT_DIR"
mkdir -p "$CACHE_DIR"

# Points de montage classiques des clés USB
USB_MOUNTS=("/media/$USER/" "/run/media/$USER/")  

for MOUNT in "${USB_MOUNTS[@]}"; do
    if [ -d "$MOUNT" ]; then
        for USB in "$MOUNT"*/; do
            BORNE_USB="$USB/borne_games"
            if [ -d "$BORNE_USB" ]; then
                echo "Clé détectée avec borne_games : $USB"

                # Copier tout le contenu dans plugins
                cp -r "$BORNE_USB/"* "$PROJECT_DIR/"

                # Décompresser tous les ZIP Python dans cache
                for ZIP in "$PROJECT_DIR"/*.zip; do
                    [ -f "$ZIP" ] || continue
                    echo "Décompression de $ZIP"
                    unzip -o "$ZIP" -d "$CACHE_DIR/$(basename "$ZIP" .zip)"
                done
            fi
        done
    fi
done

echo "Installation des jeux terminée."