package modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
* La classe Graphe correspond à un graphe, composée à la fois d'un nombre de sommets,
* d'un nombre d'arêtes, d'une liste de sommets, d'une liste d'arêtes et d'une matrice
* d'adjacence.
*
* @author Florent LUCET et Marie-Florence REVENEAU
* @version 1.0
*/
public class Graphe {
	
	static final int NB_SOMMETS_MAX_MATRICE = 1000;
	private int nbSommets;
	private int nbAretes;
	private ArrayList<Sommet> sommets = new ArrayList<Sommet>();
	private ArrayList<Arete> aretes = new ArrayList<Arete>();
	private boolean[][] matriceAdjacence = new boolean[NB_SOMMETS_MAX_MATRICE][NB_SOMMETS_MAX_MATRICE];
	
	
	
	// *********************
	// *** CONSTRUCTEURS ***
	// *********************
	
	/**
	 * Constructeur avec les attributs nbSommets, nbAretes, sommets et aretes de la
	 * classe Graphe.
	 * 
	 * @param nbSommets Nombre de sommets du graphe.
	 * @param nbAretes Nombre d'arêtes du graphe.
	 * @param sommets Liste des sommets du graphe.
	 * @param aretes Liste des arêtes du graphe.
	 */
	public Graphe(int nbSommets, int nbAretes, ArrayList<Sommet> sommets, ArrayList<Arete> aretes) {
		this.nbSommets = nbSommets;
		this.nbAretes = nbAretes;
		this.sommets = sommets;
		this.aretes = aretes;
		this.matriceAdjacence = this.construireMatriceAdjacence();
	}
	
	/**
	 * Constructeur avec les attributs nbSommets et sommets de la classe Graphe.
	 * 
	 * @param nbSommets Nombre de sommets du graphe.
	 * @param sommets Liste des sommets du graphe.
	 */
	public Graphe(int nbSommets, ArrayList<Sommet> sommets) {
		this.nbSommets = nbSommets;
		this.sommets = sommets;
	}
	
	/**
	 * Constructeur sans attribut (par défaut) de la classe Graphe.
	 */
	public Graphe() {}
	
	
	
	// ***************************************************
	// *** MÉTHODES D'ADJACENCE DE SOMMETS ET D'ARÊTES ***
	// ***************************************************
	
	/**
	 * Méthode permettant de construire la matrice d'adjacence du graphe à partir de
	 * ses arêtes.
	 * 
	 * @return Matrice d'adjacence du graphe.
	 */
	public boolean[][] construireMatriceAdjacence() {
		boolean[][] matriceAdjacence = new boolean[NB_SOMMETS_MAX_MATRICE][NB_SOMMETS_MAX_MATRICE];
		
		for(int i=0 ; i<this.getNbSommets() ; i++) {
			for (int j=0 ; j<this.getNbSommets(); j++) {
				matriceAdjacence[j][i] = false;
			}
		}
		
		for (Arete a : this.getAretes()) {
			matriceAdjacence[a.getPremierSommet().getValeur()][a.getSecondSommet().getValeur()] = true;
			matriceAdjacence[a.getSecondSommet().getValeur()][a.getPremierSommet().getValeur()] = true;
		}
		
		return matriceAdjacence;
	}
	
	/**
	 * Méthode permettant d'obtenir une liste d'arêtes contenant les arêtes adjacentes
	 * au sommet mis en paramètre.
	 * 
	 * @param s Sommet dont on cherche à connaître les arêtes adjacentes.
	 * @return Liste d'arêtes contenant les arêtes adjacentes au sommet s.
	 */
	public ArrayList<Arete> aretesAdjacentes (Sommet s) {
		ArrayList<Arete> aretesAdjacentes = new ArrayList<Arete>();
		
		for (int i=0 ; i<this.getNbSommets() ; i++) {
			if (this.getMatriceAdjacence()[s.getValeur()][i] == true)
				aretesAdjacentes.add(this.getArete(s.getValeur(), i));
		}
		
		return aretesAdjacentes;
	}
	
	/**
	 * Méthode permettant d'obtenir une liste de sommets contenant les sommets adjacents
	 * au sommet mis en paramètre.
	 * 
	 * @param s Sommet dont on cherche à connaître les sommets adjacents.
	 * @return Liste de sommets contenant les sommets adjacents au sommet s.
	 */
	public ArrayList<Sommet> sommetsAdjacents (Sommet s) {
		ArrayList<Sommet> sommetsAdjacents = new ArrayList<Sommet>();
		
		for (int i=0 ; i<this.getNbSommets() ; i++) {
			if (this.getMatriceAdjacence()[s.getValeur()][i] == true)
				sommetsAdjacents.add(this.getSommets().get(i));
		}
		
		return sommetsAdjacents;
	}
	
	/**
	 * Méthode permettant d'obtenir le second sommet d'une arête dont on connaît le
	 * premier sommet.
	 * 
	 * @param s Sommet de l'arête connu.
	 * @param a Arête dont on cherche à connaître le second sommet.
	 * @return Second sommet de l'arête a.
	 */
	public Sommet sommetAdjacent (Sommet s, Arete a) {
		if (s.getValeur() == a.getPremierSommet().getValeur())
			return a.getSecondSommet();
		else if (s.getValeur() == a.getSecondSommet().getValeur())
			return a.getPremierSommet();
		else
			return null;
	}
	
	
	
	// *************************************
	// *** MÉTHODE DE PARCOURS DE GRAPHE ***
	// *************************************
	
	/**
	 * Méthode permettant d'effectuant une recherche en largeur en vue de trouver le
	 * nombre d'arêtes entre un sommet mis en paramètre et le sommet le plus éloigné
	 * de ce dernier, en passant par le plus court chemin.
	 * Est utilisé pour trouver le diamètre du graphe.
	 * 
	 * @param sommetDepart Sommet duquel part la recherche en largeur.
	 * @return Le nombre d'arêtes entre le sommet sommetDepart et le sommet le plus
	 * éloigné de ce dernier, en passant par le plus court chemin.
	 */
	public int rechercheEnLargeur (Sommet sommetDepart) {
		Queue<Sommet> sommetsParcourus = new LinkedList<Sommet>();
		Sommet sommet = new Sommet();
		int valeurMaxSommet = 0;
		
		for (Sommet s : this.getSommets()) {
			s.setMarque(false);
			s.setVague(0);
		}
		
		sommetDepart.setMarque(true);
		sommetDepart.setVague(0);
		sommetsParcourus.add(sommetDepart);
		
		while (!sommetsParcourus.isEmpty()) {
			sommet = sommetsParcourus.poll();
			for (Sommet s : this.sommetsAdjacents(sommet)) {
				if (s.isMarque() == false) {
					s.setMarque(true);
					s.setVague(sommet.getVague()+1);
					sommetsParcourus.add(s);
					if (valeurMaxSommet < s.getVague())
						valeurMaxSommet = s.getVague();
				}
			}
		}
		
		return valeurMaxSommet;
	}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	/**
	 * Accesseur permettant de récupérer le nombre de sommets du graphe.
	 * 
	 * @return Nombre de sommets du graphe.
	 */
	public int getNbSommets() {
		return nbSommets;
	}
	
	/**
	 * Accesseur permettant de récupérer le nombre d'arêtes du graphe.
	 * 
	 * @return Nombre d'arêtes du graphe.
	 */
	public int getNbAretes() {
		return nbAretes;
	}
	
	/**
	 * Accesseur permettant de récupérer la liste des sommets du graphe.
	 * 
	 * @return Liste des sommets du graphe.
	 */
	public ArrayList<Sommet> getSommets() {
		return sommets;
	}
	
	/**
	 * Accesseur permettant de récupérer la liste des arêtes du graphe.
	 * 
	 * @return Liste des arêtes du graphe.
	 */
	public ArrayList<Arete> getAretes() {
		return aretes;
	}
	
	/**
	 * Accesseur permettant de récupérer l'adresse de la matrice d'adjacence du
	 * graphe.
	 * 
	 * @return Matrice d'adjacence du graphe.
	 */
	public boolean[][] getMatriceAdjacence() {
		return matriceAdjacence;
	}
	
	/**
	 * Accesseur permettant de récupérer une arête du graphe à partir de la valeur
	 * de ses deux sommets.
	 * 
	 * @param valeurPremierSommet Valeur du premier sommet de l'arête.
	 * @param valeurSecondSommet Valeur du second sommet de l'arête.
	 * @return Arête dont les deux sommets ont pour valeurs respectives
	 * valeurPremierSommet et valeurSecondSommet.
	 */
	public Arete getArete(int valeurPremierSommet, int valeurSecondSommet) {
		for (Arete a : this.getAretes()) {
			if ((a.getPremierSommet().getValeur() == valeurPremierSommet) && (a.getSecondSommet().getValeur() == valeurSecondSommet)) {
				return a;
			} else if ((a.getPremierSommet().getValeur() == valeurSecondSommet) && (a.getSecondSommet().getValeur() == valeurPremierSommet)) {
				return a;
			}
		}
		
		return null;
	}
	
	/**
	 * Accesseur permettant de récupérer le diamètre du graphe à partir d'une
	 * recherche en largeur adaptée.
	 * 
	 * @return Diamètre du graphe.
	 */
	public int getDiametre () {
		int valeurMax = 0;
		int valeurRecherche = 0;
		
		for (Sommet s : this.getSommets()) {
			valeurRecherche = rechercheEnLargeur(s);
			if (valeurMax < valeurRecherche)
				valeurMax = valeurRecherche;
		}
		
		return valeurMax;
	}
	
	/**
	 * Accesseur permettant de récupérer le poids total des arêtes du graphe.
	 * 
	 * @return Poids total des arêtes du graphe.
	 */
	public int getPoidsTotal() {
		int poidsTotal = 0;
		
		for (Arete a : this.getAretes())
			poidsTotal += a.getPoids();
		
		return poidsTotal;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	/**
	 * Mutateur permettant de changer le nombre d'arêtes du graphe.
	 * 
	 * @param nbAretes Nouveau nombre d'arêtes du graphe.
	 */
	public void setNbAretes(int nbAretes) {
		this.nbAretes = nbAretes;
	}
	
	/**
	 * Mutateur permettant de changer le nombre de sommets du graphe.
	 * 
	 * @param nbSommets Nouveau nombre de sommets du graphe.
	 */
	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}
	
	/**
	 * Mutateur permettant de changer la liste des sommets du graphe.
	 * 
	 * @param sommets Nouvelle liste des sommets du graphe.
	 */
	public void setSommets(ArrayList<Sommet> sommets) {
		this.sommets = sommets;
	}
	
	/**
	 * Mutateur permettant de changer la liste des arêtes du graphe.
	 * 
	 * @param aretes Nouvelle liste des arêtes du graphe.
	 */
	public void setAretes(ArrayList<Arete> aretes) {
		this.aretes = aretes;
	}
	
	/**
	 * Mutateur permettant de changer la matrice d'adjacence du graphe.
	 * 
	 * @param matriceAdjacence Nouvelle matrice d'adjacence du graphe.
	 */
	public void setMatriceAdjacence(boolean[][] matriceAdjacence) {
		this.matriceAdjacence = matriceAdjacence;
	}
	
	
	
	// ***************************
	// *** MÉTHODE D'AFFICHAGE ***
	// ***************************
	
	/**
	 * Méthode permettant d'afficher les différentes caractéristiques du graphe.
	 * 
	 * @param typeGraphe Type du graphe (origine, ACM de Prim, ACM de Kruskal).
	 */
	public void afficherInformationsGraphe (int typeGraphe) {
		// Début de l'affichage des informations du graphe.
		if (typeGraphe == 0) {
			System.out.println("************************");
			System.out.println("*** GRAPHE D'ORIGINE ***");
			System.out.println("************************");
			
			System.out.println("");
			System.out.println("Le graphe d'origine possède pour...");
		} else {
			System.out.println("");
			System.out.println("");
			
			if (typeGraphe == 1) {
				System.out.println("**************************");
				System.out.println("*** ALGORITHME DE PRIM ***");
				System.out.println("**************************");
			} else {
				System.out.println("*****************************");
				System.out.println("*** ALGORITHME DE KRUSKAL ***");
				System.out.println("*****************************");
			}
			
			System.out.println("");
			System.out.println("L'art couvrant minimal possède pour...");
		}
		
		// Affichage du nombre de sommets du graphe.
		System.out.println("- Nombre de sommets : " + this.getNbSommets() + ".");
		
		// Affichage du nombre d'arêtes du graphe.
		System.out.println("- Nombre d'arêtes : " + this.getNbAretes() + ".");
		
		// Affichage des arêtes du graphe.
		for (Arete a : this.getAretes())
			System.out.println("- Premier sommet : " + a.getPremierSommet().getValeur() + " ; Second sommet : " + a.getSecondSommet().getValeur() + " ; Poids : " + a.getPoids() + ".");
		
		// Affichage du diamètre du graphe.
		System.out.println("- Diamètre : " + this.getDiametre() + ".");
		
		// Affichage du coût du graphe.
		System.out.println("- Coût : " + this.getPoidsTotal() + ".");
	}
	
}