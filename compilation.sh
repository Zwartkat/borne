#!/bin/bash

echo "Compilation du menu de la borne d'arcade"
pwd
javac -cp .:./MG2D.jar ./Arcade/*.java
javac -cp .:./Arcade:./MG2D.jar *.java


cd projet || exit 1

for proj in */ ; do
    cd "$proj" || continue
    echo "Traitement du projet $proj..."

    java_files=(*.java)
    py_files=(*.py)
    lua_files=(*.lua)

    # Java
    if [ ${#java_files[@]} -gt 0 ]; then
        echo "→ Projet Java détecté"

        CP="../../MG2D.jar"
        if [ -d "libs" ]; then
            CP="$CP:libs/*"
        fi

        javac -cp "$CP" *.java

    # Python
    elif [ ${#py_files[@]} -gt 0 ]; then
        echo "→ Projet Python détecté"
        pipreqs . --force
        if [ -f "requirements.txt" ]; then
            echo "→ Installation des dépendances Python..."
            python3 -m pip install --user -r requirements.txt
        fi

    elif [ ${#lua_files[@]} -gt 0 ]; then
        echo "→ Projet Lua détecté"

    else
        echo "→ Langage non reconnu"
    fi

    cd ..
done
cd ..
wait