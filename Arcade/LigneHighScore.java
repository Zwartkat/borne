package Arcade;

class LigneHighScore {
	private String nom;
	private int score;

	public LigneHighScore() {
		nom = "AAA";
		score = 0;
	}
	/**
	 * Constructeur de LigneHighScore avec nom et score. Si le nom est trop long, il est remplacé par "AAA". Si le score est négatif, il est remplacé par 0.
	 * @param nnom Le nom du joueur
	 * @param sscore Le score du joueur
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
		return score;
	}

	public String getNom() {
		return nom;
	}

	public String toString() {
		return nom + "-" + score;
	}
} 



