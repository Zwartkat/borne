package Arcade;

class LigneHighScore {
	private String nom;
	private int score;

	public LigneHighScore() {
		nom = "AAA";
		score = 0;
	}
	/**
	 * Constructeur par défaut initialisant le nom à "AAA" (valeur par défaut pour nom inconnu)
	 * et le score à 0. Cette valeur est utilisée lorsque le nom est vide ou inconnu.
	 */
	public LigneHighScore(String nnom, int sscore) {
		if (nnom.length() > 3)
			nnom = "AAA";
		else
			nom = new String(nnom);
		if (sscore < 0)
			score = 0;
		else
			score = sscore;
	}
	/**
	 * Constructeur de copie permettant de créer une nouvelle instance à partir d'une autre
	 * LigneHighScore. Effectue une copie profonde des données.
	 * @param l Le LigneHighScore à copier
	 */
	public LigneHighScore(LigneHighScore l) {
		nom = new String(l.nom);
		score = l.score;
	}
	/**
	 * Constructeur à partir d'une chaîne au format "Nom-Score" (ex: "John-123").
	 * Effectue une validation de format et retourne des valeurs par défaut en cas d'erreur.
	 * @param str La chaîne à parser
	 */
	public LigneHighScore(String str) {
		String[] tab = str.split("-");
		if (tab.length != 2) {
			nom = "AAA";
			score = 0;
		} else {
			nom = new String(tab[0]);
			score = Integer.parseInt(tab[1]);
		}

	}

	public int getScore() {
		/**
		 * Retourne le score du joueur. Cette méthode permet d'obtenir la valeur du score
		 * sans modifier l'objet.
		 * @return Le score du joueur
		 */
		return score;
	}

	public String getNom() {
		/**
		 * Retourne le nom du joueur. Cette méthode permet d'obtenir la valeur du nom
		 * sans modifier l'objet.
		 * @return Le nom du joueur
		 */
		return nom;
	}

	public String toString() {
		/**
		 * Retourne la représentation sous forme de chaîne du LigneHighScore au format "Nom-Score".
		 * Cette méthode est utilisable pour l'affichage ou le stockage dans des formats textuels.
		 * @return La chaîne représentant le LigneHighScore
		 */
		return nom + "-" + score;
	}
}