#!/bin/bash

echo "Compilation du menu de la borne d'arcade"
javac -cp .:./MG2D.jar ./Arcade/*.java
javac -cp .:./Arcade:./MG2D.jar *.java

cd ./projet

while IFS="," read -r dir lang input git
do
  if [ "$lang" = "java"];
  then
    cd "$dir"
    echo "Compilation du jeu : $dir"
    javac -cp ".:../..:../../MG2D.jar" *.java
    cd ..
  fi
done < ../games.csv

cd ..