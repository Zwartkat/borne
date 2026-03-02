import os

def rename_ogg_files_with_number(directory):
    # Vérifier que le répertoire existe
    if not os.path.isdir(directory):
        print(f"Le répertoire {directory} n'existe pas.")
        return
    
    # Parcourir tous les fichiers dans le répertoire
    for filename in os.listdir(directory):
        # Récupérer le chemin complet du fichier
        filepath = os.path.join(directory, filename)
        
        # Vérifier si le chemin pointe vers un fichier .ogg
        if os.path.isfile(filepath) and filename.endswith(".mp3"):
            # Séparer le nom de fichier en utilisant l'espace comme séparateur
            parts = filename.split(" ")
            # Vérifier si la première partie est un nombre
            if parts[0].isdigit():
                # Récupérer le nombre trouvé
                number = parts[0]
                # Construire le nouveau nom de fichier avec seulement le nombre
                new_filename = number + ".mp3"
                # Renommer le fichier
                os.rename(filepath, os.path.join(directory, new_filename))
                print(f"Le fichier {filename} a été renommé en {new_filename}.")
            else:
                print(f"Aucun nombre trouvé au début du fichier {filename}. Le fichier n'a pas été renommé.")

# Spécifiez le répertoire où se trouvent les fichiers .ogg
repertoire = "C:/Users/adrie/Desktop/IUT/BUT1/R2.01 Dev objet/R2_01_tdtp08/audio/cris"

# Appel de la fonction pour renommer les fichiers .ogg avec seulement le nombre au début
rename_ogg_files_with_number(repertoire)
