import os

def supprimer_fichiers_ogg(directory):
    # Vérifier que le répertoire existe
    if not os.path.isdir(directory):
        print(f"Le répertoire {directory} n'existe pas.")
        return
    
    # Parcourir tous les fichiers dans le répertoire
    for filename in os.listdir(directory):
        # Vérifier si le fichier est un fichier .ogg
        if filename.endswith(".ogg"):
            # Construire le chemin complet du fichier
            filepath = os.path.join(directory, filename)
            # Supprimer le fichier
            os.remove(filepath)
            print(f"Le fichier {filename} a été supprimé.")

# Spécifiez le répertoire où se trouvent les fichiers .ogg
repertoire = "/home/max-linder-user/Documents/génération 1"

# Appel de la fonction pour supprimer les fichiers .ogg dans le répertoire spécifié
supprimer_fichiers_ogg(repertoire)
