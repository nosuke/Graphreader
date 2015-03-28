package modele;


/**
* La classe Arete correspond à une arête de graphe, composée à la fois de deux sommets
* et d'un poids.
*
* @author Florent LUCET et Marie-Florence REVENEAU
* @version 1.0
*/
public class Arete implements Comparable<Object> {
	
	private Sommet premierSommet;
	private Sommet secondSommet;
	private int poids;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * Constructeur avec tous les attributs de la classe Arete.
	 * 
	 * @param premierSommet Premier sommet de l'arête.
	 * @param secondSommet Second sommet de l'arête.
	 * @param poids Poids de l'arête.
	 */
	public Arete(Sommet premierSommet, Sommet secondSommet, int poids) {
		this.premierSommet = premierSommet;
		this.secondSommet = secondSommet;
		this.poids = poids;
	}
	
	
	
	// ******************************
	// *** MÉTHODE DE COMPARAISON ***
	// ******************************
	
	/**
	 * Méthode servant à définir le tri "Sort" sur la base du poids des arêtes
	 * de la liste d'arêtes à trier.
	 * 
	 * @param arete L'une des deux arêtes comparées, en vue de trier une liste d'arêtes.
	 */
	@Override
	public int compareTo(Object arete) {
		int nombre1 = ((Arete) arete).getPoids();
		int nombre2 = this.getPoids();
		
		if (nombre1 > nombre2)  return -1;
		else if (nombre1 == nombre2) return 0;
		else return 1;
	}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	/**
	 * Accesseur permettant de récupérer le premier sommet de l'arête.
	 * 
	 * @return Premier sommet de l'arête.
	 */
	public Sommet getPremierSommet() {
		return premierSommet;
	}
	
	/**
	 * Accesseur permettant de récupérer le second sommet de l'arête.
	 * 
	 * @return Second sommet de l'arête.
	 */
	public Sommet getSecondSommet() {
		return secondSommet;
	}
	
	/**
	 * Accesseur permettant de récupérer le poids de l'arête.
	 * 
	 * @return Poids de l'arête.
	 */
	public int getPoids() {
		return poids;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	/**
	 * Mutateur permettant de changer le premier sommet de l'arête.
	 * 
	 * @param premierSommet Nouveau premier sommet de l'arête.
	 */
	public void setPremierSommet(Sommet premierSommet) {
		this.premierSommet = premierSommet;
	}
	
	/**
	 * Mutateur permettant de changer le second sommet de l'arête.
	 * 
	 * @param secondSommet Nouveau second sommet de l'arête.
	 */
	public void setSecondSommet(Sommet secondSommet) {
		this.secondSommet = secondSommet;
	}
	
	/**
	 * Mutateur permettant de changer le poids de l'arête.
	 * 
	 * @param poids Nouveau poids de l'arête.
	 */
	public void setPoids(int poids) {
		this.poids = poids;
	}
	
}