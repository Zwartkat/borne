@echo off
echo Compilation du menu de la borne d'arcade
echo Veuillez patienter

cd Arcade 

javac -cp .;..\MG2D.jar *.java

cd ../

javac -cp .;.\MG2D.jar *.java

cd projet

for /D %%i in (*) do (
    cd %%i
    echo Compilation du jeu %%i
    echo Veuillez patienter
    javac -cp .;..\..;..\..\MG2D.jar *.java
    cd ..
)

cd ..
pause
