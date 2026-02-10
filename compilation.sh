#!/bin/bash

echo "Compilation du menu de la borne d'arcade"
cd Arcade
javac -cp .:../MG2D.jar *.java
cd ../
javac -cp .:./Arcade:./MG2D.jar *.java

cd projet

for i in *
do
    cd "$i"
    echo "Compilation du jeu $i"
    javac -cp ".:../..:../../MG2D.jar" *.java
    cd ..
done

cd ..

