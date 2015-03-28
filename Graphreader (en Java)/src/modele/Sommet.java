package modele;


/**
* La classe Sommet correspond � un sommet de graphe, compos� � la fois d'un nom, d'une
* valeur, d'un marquage (pour l'agorithme de Prim et le parcours en largeur) et d'une
* vague (pour le parcours en largeur).
*
* @author Florent LUCET et Marie-Florence REVENEAU
* @version 1.0
*/
public class Sommet {
	
	private String nom;
	private int valeur;
	private boolean marque;
	private int vague;
	
	
	
	// *********************
	// *** CONSTRUCTEURS ***
	// *********************
	
	/**
	 * Constructeur avec les attributs nom, valeur et marque de la classe Sommet.
	 * 
	 * @param nom Nom du sommet.
	 * @param valeur Valeur du sommet.
	 * @param marque Marquage du sommet.
	 */
	public Sommet(String nom, int valeur, boolean marque) {
		this.nom = nom;
		this.valeur = valeur;
		this.marque = marque;
	}
	
	/**
	 * Constructeur avec l'attribut valeur de la classe Sommet.
	 * 
	 * @param valeur Valeur du sommet.
	 */
	public Sommet(int valeur) {
		this.nom = "" + valeur;
		this.valeur = valeur;
	}
	
	/**
	 * Constructeur sans attribut (par d�faut) de la classe Sommet.
	 */
	public Sommet() {}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	/**
	 * Accesseur permettant de r�cup�rer le nom du sommet.
	 * 
	 * @return Nom du sommet.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer la valeur du sommet.
	 * 
	 * @return Valeur du sommet.
	 */
	public int getValeur() {
		return valeur;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer le marquage du sommet.
	 * 
	 * @return Marquage du sommet.
	 */
	public boolean isMarque() {
		return marque;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer la vague du sommet.
	 * 
	 * @return Vague du sommet.
	 */
	public int getVague() {
		return vague;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	/**
	 * Mutateur permettant de changer le nom du sommet.
	 * 
	 * @param nom Nouveau nom du sommet.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Mutateur permettant de changer la valeur du sommet.
	 * 
	 * @param valeur Nouvelle valeur du sommet.
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * Mutateur permettant de changer le marquage du sommet.
	 * 
	 * @param marque Nouveau marquage du sommet.
	 */
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	/**
	 * Mutateur permettant de changer la vague du sommet.
	 * 
	 * @param vague Nouvelle vague du sommet.
	 */
	public void setVague(int vague) {
		this.vague = vague;
	}
	
}