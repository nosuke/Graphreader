package modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
* La classe Graphe correspond � un graphe, compos�e � la fois d'un nombre de sommets,
* d'un nombre d'ar�tes, d'une liste de sommets, d'une liste d'ar�tes et d'une matrice
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
	 * @param nbAretes Nombre d'ar�tes du graphe.
	 * @param sommets Liste des sommets du graphe.
	 * @param aretes Liste des ar�tes du graphe.
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
	 * Constructeur sans attribut (par d�faut) de la classe Graphe.
	 */
	public Graphe() {}
	
	
	
	// ***************************************************
	// *** M�THODES D'ADJACENCE DE SOMMETS ET D'AR�TES ***
	// ***************************************************
	
	/**
	 * M�thode permettant de construire la matrice d'adjacence du graphe � partir de
	 * ses ar�tes.
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
	 * M�thode permettant d'obtenir une liste d'ar�tes contenant les ar�tes adjacentes
	 * au sommet mis en param�tre.
	 * 
	 * @param s Sommet dont on cherche � conna�tre les ar�tes adjacentes.
	 * @return Liste d'ar�tes contenant les ar�tes adjacentes au sommet s.
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
	 * M�thode permettant d'obtenir une liste de sommets contenant les sommets adjacents
	 * au sommet mis en param�tre.
	 * 
	 * @param s Sommet dont on cherche � conna�tre les sommets adjacents.
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
	 * M�thode permettant d'obtenir le second sommet d'une ar�te dont on conna�t le
	 * premier sommet.
	 * 
	 * @param s Sommet de l'ar�te connu.
	 * @param a Ar�te dont on cherche � conna�tre le second sommet.
	 * @return Second sommet de l'ar�te a.
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
	// *** M�THODE DE PARCOURS DE GRAPHE ***
	// *************************************
	
	/**
	 * M�thode permettant d'effectuant une recherche en largeur en vue de trouver le
	 * nombre d'ar�tes entre un sommet mis en param�tre et le sommet le plus �loign�
	 * de ce dernier, en passant par le plus court chemin.
	 * Est utilis� pour trouver le diam�tre du graphe.
	 * 
	 * @param sommetDepart Sommet duquel part la recherche en largeur.
	 * @return Le nombre d'ar�tes entre le sommet sommetDepart et le sommet le plus
	 * �loign� de ce dernier, en passant par le plus court chemin.
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
	 * Accesseur permettant de r�cup�rer le nombre de sommets du graphe.
	 * 
	 * @return Nombre de sommets du graphe.
	 */
	public int getNbSommets() {
		return nbSommets;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer le nombre d'ar�tes du graphe.
	 * 
	 * @return Nombre d'ar�tes du graphe.
	 */
	public int getNbAretes() {
		return nbAretes;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer la liste des sommets du graphe.
	 * 
	 * @return Liste des sommets du graphe.
	 */
	public ArrayList<Sommet> getSommets() {
		return sommets;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer la liste des ar�tes du graphe.
	 * 
	 * @return Liste des ar�tes du graphe.
	 */
	public ArrayList<Arete> getAretes() {
		return aretes;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer l'adresse de la matrice d'adjacence du
	 * graphe.
	 * 
	 * @return Matrice d'adjacence du graphe.
	 */
	public boolean[][] getMatriceAdjacence() {
		return matriceAdjacence;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer une ar�te du graphe � partir de la valeur
	 * de ses deux sommets.
	 * 
	 * @param valeurPremierSommet Valeur du premier sommet de l'ar�te.
	 * @param valeurSecondSommet Valeur du second sommet de l'ar�te.
	 * @return Ar�te dont les deux sommets ont pour valeurs respectives
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
	 * Accesseur permettant de r�cup�rer le diam�tre du graphe � partir d'une
	 * recherche en largeur adapt�e.
	 * 
	 * @return Diam�tre du graphe.
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
	 * Accesseur permettant de r�cup�rer le poids total des ar�tes du graphe.
	 * 
	 * @return Poids total des ar�tes du graphe.
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
	 * Mutateur permettant de changer le nombre d'ar�tes du graphe.
	 * 
	 * @param nbAretes Nouveau nombre d'ar�tes du graphe.
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
	 * Mutateur permettant de changer la liste des ar�tes du graphe.
	 * 
	 * @param aretes Nouvelle liste des ar�tes du graphe.
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
	// *** M�THODE D'AFFICHAGE ***
	// ***************************
	
	/**
	 * M�thode permettant d'afficher les diff�rentes caract�ristiques du graphe.
	 * 
	 * @param typeGraphe Type du graphe (origine, ACM de Prim, ACM de Kruskal).
	 */
	public void afficherInformationsGraphe (int typeGraphe) {
		// D�but de l'affichage des informations du graphe.
		if (typeGraphe == 0) {
			System.out.println("************************");
			System.out.println("*** GRAPHE D'ORIGINE ***");
			System.out.println("************************");
			
			System.out.println("");
			System.out.println("Le graphe d'origine poss�de pour...");
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
			System.out.println("L'art couvrant minimal poss�de pour...");
		}
		
		// Affichage du nombre de sommets du graphe.
		System.out.println("- Nombre de sommets : " + this.getNbSommets() + ".");
		
		// Affichage du nombre d'ar�tes du graphe.
		System.out.println("- Nombre d'ar�tes : " + this.getNbAretes() + ".");
		
		// Affichage des ar�tes du graphe.
		for (Arete a : this.getAretes())
			System.out.println("- Premier sommet : " + a.getPremierSommet().getValeur() + " ; Second sommet : " + a.getSecondSommet().getValeur() + " ; Poids : " + a.getPoids() + ".");
		
		// Affichage du diam�tre du graphe.
		System.out.println("- Diam�tre : " + this.getDiametre() + ".");
		
		// Affichage du co�t du graphe.
		System.out.println("- Co�t : " + this.getPoidsTotal() + ".");
	}
	
}