#!/bin/bash

echo "Compilation du menu de la borne d'arcade"
javac -cp .:./MG2D.jar ./Arcade/*.java
javac -cp .:./Arcade:./MG2D.jar *.java

cd projet
for proj in */ ; do
    cd "$proj"
    echo "Compilation du jeu $proj..."
    
    if [ -f "build.gradle" ] || [ -f "build.gradle.kts" ]; then
        echo "Gradle detecte. Compilation avec Gradle..."
        if [ -f "./gradlew" ]; then
            ./gradlew build
        else
            gradle build
        fi
    else
        echo "Pas de build system. Compilation avec javac récursive..."
        mkdir -p bin
        find . -name "*.java" > sources.txt
        javac -d bin -cp ../../MG2D.jar @sources.txt
        rm sources.txt
    fi

    cd ..
done
cd ..