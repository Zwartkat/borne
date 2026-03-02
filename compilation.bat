@echo off
echo Compilation du menu de la borne d'arcade
echo Veuillez patienter

cd MG2D

javac . *.java
jar cf MG2D.jar ./*.class
cd ..

cd Arcade
javac -cp .;..\MG2D.jar *.java
cd ../

javac -cp .;.\MG2D.jar *.java

cd projet
for /D %%i in (*) do (
    cd %%i
    echo Compilation du jeu %%i
    echo Veuillez patienter

    if exist build.gradle.kts (
        gradlew build
    ) else (
        javac -cp .;..\..;..\..\MG2D.jar *.java
    )

    cd ..
)

cd ..
pause