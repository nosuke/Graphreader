package modele;

import java.util.ArrayList;


/**
* La classe Prim correspond à un appel de l'algorithme de Prim pour obtenir l'arbre
* couvrant minimal d'un graphe.
*
* Temps d'exécution de l'algorithme de Prim avec utilisation d'une recherche en largeur
* adaptée et d'une matrice d'adjacence.
* Instance de 100 : 0,371 seconde.
* Instance de 200 : 0,924 seconde.
* Instance de 300 : 1,316 seconde.
* Instance de 400 : 2,139 secondes.
* 
* @author Florent LUCET et Marie-Florence REVENEAU
* @version 1.0
*/
public class Prim {
	
	private Graphe g;
	
	
	
	// ********************
	// *** CONSTRUCTEUR ***
	// ********************
	
	/**
	 * Constructeur avec l'attribut g de la classe Prim.
	 * 
	 * @param g Graphe sur lequel on utilise l'algorithme de Prim.
	 */
	public Prim(Graphe g) {
		this.g = g;
	}
	
	
	
	// ******************
	// *** ALGORITHME ***
	// ******************
	
	/**
	 * Déclenche l'algorithme de Prim.
	 * 
	 * @return Arbre couvrant minimal du graphe g.
	 */
	public Graphe declencherAlgorithme () {
		Graphe ACM = new Graphe(this.getG().getNbSommets(), this.getG().getSommets());
		ArrayList<Arete> aretesACM = new ArrayList<Arete>();
		
		ArrayList<Sommet> sommetsParcourus = new ArrayList<Sommet>();
		ArrayList<Sommet> sommetsParcourusVagueActuelle = new ArrayList<Sommet>();
		Sommet sommetAdjacent = new Sommet();
		int poidsMinimal;
		
		for (Sommet s : this.getG().getSommets())
			s.setMarque(false);
		
		sommetsParcourus.add(this.getG().getSommets().get(0));
		this.getG().getSommets().get(0).setMarque(true);
		
		while (aretesACM.size() < (this.getG().getNbSommets()-1)) {
			poidsMinimal = getPoidsMinimal(sommetsParcourus);
			
			for (Sommet s : sommetsParcourus) {
				for (Arete a : this.getG().aretesAdjacentes(s)) {
					sommetAdjacent = this.getG().sommetAdjacent(s, a);
					if ((sommetAdjacent.isMarque() == false) && (a.getPoids() == poidsMinimal)) {
						aretesACM.add(a);
						sommetAdjacent.setMarque(true);
						sommetsParcourusVagueActuelle.add(sommetAdjacent);
					}
				}
			}
			
			for (Sommet s : sommetsParcourusVagueActuelle)
				sommetsParcourus.add(s);
			sommetsParcourusVagueActuelle.clear();
		}
		
		ACM.setNbAretes(aretesACM.size());
		ACM.setAretes(aretesACM);
		ACM.setMatriceAdjacence(ACM.construireMatriceAdjacence());
		
		return ACM;
	}
	
	
	
	// ******************
	// *** ACCESSEURS ***
	// ******************
	
	/**
	 * Accesseur permettant de récupérer le graphe de Prim.
	 * 
	 * @return Graphe de Prim.
	 */
	public Graphe getG() {
		return g;
	}
	
	
	/**
	 * Méthode permettant de récupérer le poids minimal trouvable pour les arêtes adjacentes
	 * aux sommets déjà parcourus.
	 * 
	 * @param sommetsParcourus Liste de sommets contenant les sommets déjà parcourus dans le
	 * graphe.
	 * @return Poids minimal trouvé pour les arêtes adjacentes aux sommets de la liste de
	 * sommets sommetsParcourus.
	 */
	public int getPoidsMinimal (ArrayList<Sommet> sommetsParcourus) {
		int poidsMinimal = 99999;
		
		for (Sommet s : sommetsParcourus) {
			for (Arete a : this.getG().aretesAdjacentes(s)) {
				if ((a.getPoids() <= poidsMinimal) && (this.getG().sommetAdjacent(s, a).isMarque() == false))
					poidsMinimal = a.getPoids();
			}
		}
		
		return poidsMinimal;
	}
	
	
	// ****************
	// *** MUTATEUR ***
	// ****************
	
	/**
	 * Mutateur permettant de changer le graphe de Prim.
	 * 
	 * @param g Nouveau graphe de Prim.
	 */
	public void setG(Graphe g) {
		this.g = g;
	}
	
}