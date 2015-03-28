package modele;


/**
* La classe Arete correspond � une ar�te de graphe, compos�e � la fois de deux sommets
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
	 * @param premierSommet Premier sommet de l'ar�te.
	 * @param secondSommet Second sommet de l'ar�te.
	 * @param poids Poids de l'ar�te.
	 */
	public Arete(Sommet premierSommet, Sommet secondSommet, int poids) {
		this.premierSommet = premierSommet;
		this.secondSommet = secondSommet;
		this.poids = poids;
	}
	
	
	
	// ******************************
	// *** M�THODE DE COMPARAISON ***
	// ******************************
	
	/**
	 * M�thode servant � d�finir le tri "Sort" sur la base du poids des ar�tes
	 * de la liste d'ar�tes � trier.
	 * 
	 * @param arete L'une des deux ar�tes compar�es, en vue de trier une liste d'ar�tes.
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
	 * Accesseur permettant de r�cup�rer le premier sommet de l'ar�te.
	 * 
	 * @return Premier sommet de l'ar�te.
	 */
	public Sommet getPremierSommet() {
		return premierSommet;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer le second sommet de l'ar�te.
	 * 
	 * @return Second sommet de l'ar�te.
	 */
	public Sommet getSecondSommet() {
		return secondSommet;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer le poids de l'ar�te.
	 * 
	 * @return Poids de l'ar�te.
	 */
	public int getPoids() {
		return poids;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	/**
	 * Mutateur permettant de changer le premier sommet de l'ar�te.
	 * 
	 * @param premierSommet Nouveau premier sommet de l'ar�te.
	 */
	public void setPremierSommet(Sommet premierSommet) {
		this.premierSommet = premierSommet;
	}
	
	/**
	 * Mutateur permettant de changer le second sommet de l'ar�te.
	 * 
	 * @param secondSommet Nouveau second sommet de l'ar�te.
	 */
	public void setSecondSommet(Sommet secondSommet) {
		this.secondSommet = secondSommet;
	}
	
	/**
	 * Mutateur permettant de changer le poids de l'ar�te.
	 * 
	 * @param poids Nouveau poids de l'ar�te.
	 */
	public void setPoids(int poids) {
		this.poids = poids;
	}
	
}