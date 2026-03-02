#!/bin/bash
INSTALL_PATH=""

setxkbmap borne

cd /home/zwartkat/Desktop/borne/borne
echo "Nettoyage des répertoires"
./clean.sh
./compilation.sh

echo "Lancement du  Menu"

java -cp .:./MG2D.jar Main1

./clean.sh

