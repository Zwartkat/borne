package Arcade;

class LigneHighScore {
	private String nom;
	private int score;

	public LigneHighScore() {
		nom = "AAA";
		score = 0;
	}
	/**
	 * Constructeur par défaut initialisant le nom à "AAA" et le score à 0.
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
	 * Constructeur de LigneHighScore à partir d'un autre LigneHighScore.
	 * @param l Le LigneHighScore à copier
	 */
	public LigneHighScore(LigneHighScore l) {
		nom = new String(l.nom);
		score = l.score;
	}
	/**
	 * Constructeur de LigneHighScore à partir d'une chaîne de caractères au format "Nom-Score".
	 * @param str La chaîne de caractères représentant le LigneHighScore
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
		 * Retourne le score du joueur.
		 * @return Le score du joueur
		 */
		return score;
	}

	public String getNom() {
		/**
		 * Retourne le nom du joueur.
		 * @return Le nom du joueur
		 */
		return nom;
	}

	public String toString() {
		/**
		 * Retourne la représentation sous forme de chaîne du LigneHighScore au format "Nom-Score".
		 * @return La chaîne représentant le LigneHighScore
		 */
		return nom + "-" + score;
	}
}