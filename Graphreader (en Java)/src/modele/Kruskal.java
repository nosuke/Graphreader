package modele;

import java.util.ArrayList;
import java.util.Collections;


/**
* La classe Kruskal correspond � un appel de l'algorithme de Kruskal pour obtenir l'arbre
* couvrant minimal d'un graphe.
*
* Temps d'ex�cution de l'algorithme de Kruskal avec utilisation de la structure Union-Find.
* Instance de 100 : 0,371 seconde.
* Instance de 200 : 0,924 seconde.
* Instance de 300 : 1,316 seconde.
* Instance de 400 : 2,139 secondes.
* Instance de 500 : 4,296 secondes.
* Instance de 600 : 6,393 secondes.
* Instance de 700 : 8,489 secondes.
* Instance de 800 : 9,787 secondes.
* Instance de 900 : 10,791 secondes.
* Instance de 1000 : 11,529 secondes.
* 
* @author Florent LUCET et Marie-Florence REVENEAU
* @version 1.0
*/
public class Kruskal {
	
	private Graphe g;
	private int[] parent;
	private int[] niveau;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * Constructeur avec l'attribut g de la classe Kruskal.
	 * 
	 * @param g Graphe sur lequel on utilise l'algorithme de Kruskal.
	 */
	public Kruskal(Graphe g) {
		this.g = g;
		this.parent = new int[this.getG().getNbSommets()];
		this.niveau = new int[this.getG().getNbSommets()];
	}
	
	
	
	// ******************
	// *** ALGORITHME ***
	// ******************
	
	/**
	 * D�clenche l'algorithme de Kruskal.
	 * 
	 * @return Arbre couvrant minimal du graphe g.
	 */
	public Graphe declencherAlgorithme () {
		Graphe ACM = new Graphe(this.getG().getNbSommets(), this.getG().getSommets());
		ArrayList<Arete> aretesTriees = new ArrayList<Arete>();
		ArrayList<Arete> aretesACM = new ArrayList<Arete>();
		
		for (int i=0 ; i<this.getG().getNbSommets() ; i++)
			initialisationArbre(i);
		
		aretesTriees = this.getG().getAretes();
		Collections.sort(aretesTriees);
		
		for (Arete a : aretesTriees) {
			if (trouver(a.getPremierSommet().getValeur()) != trouver(a.getSecondSommet().getValeur())) {
				aretesACM.add(a);
				union(a.getPremierSommet().getValeur(), a.getSecondSommet().getValeur());
			}
		}
		
		ACM.setNbAretes(aretesACM.size());
		ACM.setAretes(aretesACM);
		ACM.setMatriceAdjacence(ACM.construireMatriceAdjacence());
		
		return ACM;
	}
	
	
	// *******************************************
	// *** M�THODES DE LA STRUCTURE UNION-FIND ***
	// *******************************************
	
	/**
	 * M�thode permettant d'initialiser chaque arbre � partir de chaque sommet du graphe g.
	 * Correspond � MakeSet dans la structure de donn�es Union-Find.
	 * 
	 * @param i Sommet racine de l'arbre cr��.
	 */
	public void initialisationArbre(int i) {
		parent[i] = i;
		niveau[i] = 0;
	}
	
	/**
	 * M�thode r�cursive permettant de r�cup�rer la racine de l'arbre o� est pr�sent un sommet.
	 * Est d'abord retourn� le parent de ce sommet, puis le parent de son parent, et ainsi de
	 * suite, jusqu'� sa racine.
	 * Correspond � Find dans la structure de donn�es Union-Find.
	 * Optimisation r�alis�e : compression du chemin, en ramenant le sommet mis en
	 * param�tre en tant que fils direct de la racine de l'arbre.
	 * 
	 * @param i Sommet dont on cherche le parent de mani�re r�cursive, et donc, indirectement,
	 * la racine.
	 * @return Parent du sommet mis en param�tre.
	 */
	public int trouver (int i) {
		if (parent[i] != i)
			parent[i] = trouver(parent[i]);
		return parent[i];
	}
	
	/**
	 * M�thode permettant d'unir les arbres de deux sommets en prenant pour racine la racine
	 * d'un des deux arbres.
	 * Correspond � Union dans la structure de donn�es Union-Find.
	 * Optimisation r�alis�e : union pond�r�e, en faisant correspondre chacun des deux arbres
	 * � un poids selon leur nombre de sommets, et en mettant pour racine de l'union des deux
	 * arbres la racine de l'arbre dont le poids est le plus haut.
	 * 
	 * @param i Premier sommet.
	 * @param j Second sommet.
	 */
	public void union (int i, int j) {
		int racineArbreI = trouver(i);
		int racineArbreJ = trouver(j);
		
		if (niveau[racineArbreI] < niveau[racineArbreJ])
			parent[racineArbreI] = racineArbreJ;
		else if (niveau[racineArbreI] < niveau[racineArbreJ])
			parent[racineArbreJ] = racineArbreI;
		else {
			parent[racineArbreJ] = racineArbreI;
			niveau[racineArbreI] = niveau[racineArbreI]+1;
		}
	}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	/**
	 * Accesseur permettant de r�cup�rer le graphe de Kruskal.
	 * 
	 * @return Graphe de Kruskal.
	 */
	public Graphe getG() {
		return g;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer le tableau des parents des sommets du graphe
	 * de Kruskal.
	 * 
	 * @return Tableau des parents des sommets du graphe de Kruskal.
	 */
	public int[] getParent() {
		return parent;
	}
	
	/**
	 * Accesseur permettant de r�cup�rer le tableau des niveaux des sommets du graphe
	 * de Kruskal.
	 * 
	 * @return Tableau des niveaux des sommets du graphe de Kruskal.
	 */
	public int[] getNiveau() {
		return niveau;
	}
	
	
	// *****************
	// *** MUTATEURS ***
	// *****************
	
	/**
	 * Mutateur permettant de changer le graphe de Kruskal.
	 * 
	 * @param g Nouveau graphe de Kruskal.
	 */
	public void setG(Graphe g) {
		this.g = g;
	}
	
	/**
	 * Mutateur permettant de changer le tableau des parents des sommets du graphe
	 * de Kruskal.
	 * 
	 * @param parent Nouveau tableau des parents des sommets du graphe de Kruskal.
	 */
	public void setParent(int[] parent) {
		this.parent = parent;
	}
	
	/**
	 * Mutateur permettant de changer le tableau des niveaux des sommets du graphe
	 * de Kruskal.
	 * 
	 * @param niveau Nouveau tableau des parents des niveaux du graphe de Kruskal.
	 */
	public void setNiveau(int[] niveau) {
		this.niveau = niveau;
	}
	
}