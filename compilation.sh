#!/bin/bash

echo "Compilation du menu de la borne d'arcade"
javac -cp .:./MG2D.jar ./Arcade/*.java
javac -cp .:./Arcade:./MG2D.jar *.java

cd ./projet

for i in *
do
    cd $i
    echo "Compilation du projet $i"
    javac -cp .:../..:../../MG2D.jar *.java
    cd ..
done

cd ..