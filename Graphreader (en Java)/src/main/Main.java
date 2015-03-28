package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import modele.Arete;
import modele.Graphe;
import modele.Kruskal;
import modele.Prim;
import modele.Sommet;


/**
* La classe Main correspond à la classe principale du projet Graphreader.
* L'exécution du projet Graphreader commence donc dans cette classe.
*
* @author Florent LUCET et Marie-Florence REVENEAU
* @version 1.0
*/
public class Main {
	
	public final static String FICHIER_GRAPHE = "doc\\graphe_k5.dat";
	//public final static String FICHIER_GRAPHE = "doc\\Instances\\inst_v100.dat";

	
	/**
	 * La méthode main est la méthode principale du projet Graphreader.
	 * L'exécution du projet Graphreader commence dans cette méthode.
	 * 
	 * @param args Les arguments en lignes de commande.
	 */
	public static void main(String[] args) {
		
		/* Pour calculer le temps d'exécution de l'algorithme :
		long start = System.currentTimeMillis(); */
		
		
		// Lecture du fichier et stockage du graphe dans g.
		Graphe g = new Graphe();
		try {
			g = lireFichier(FICHIER_GRAPHE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Affichage des informations du graphe g.
		g.afficherInformationsGraphe (0);
		
		
		// Déclenchement de l'algorithme de Prim et stockage de l'arbre couvrant minimal dans ACMP.
		Graphe ACMP = new Graphe();
		Prim p = new Prim(g);
		ACMP = p.declencherAlgorithme();
		
		// Affichage des informations du graphe ACMP.
		ACMP.afficherInformationsGraphe (1);
		
		
		// Déclenchement de l'algorithme de Kruskal et stockage de l'arbre couvrant minimal dans ACMK.
		Graphe ACMK = new Graphe();
		Kruskal k = new Kruskal(g);
		ACMK = k.declencherAlgorithme();
		
		// Affichage des informations du graphe ACMK.
		ACMK.afficherInformationsGraphe (2);
		
		
		/* Pour calculer le temps d'exécution de l'algorithme :
		System.out.println("Temps d'exécution : " + (System.currentTimeMillis() - start) + "."); */
	}
	
	
	/**
	 * La méthode lireFichier permet de lire un fichier texte contenant les informations d'un
	 * graphe, et d'en extraire ces informations pour les manipuler et les afficher plus
	 * clairement.
	 * 
	 * @param cheminFichier Le chemin du fichier que l'on souhaite lire.
	 */
	@SuppressWarnings("resource")
	public static Graphe lireFichier(String cheminFichier) throws FileNotFoundException {
		Graphe g = new Graphe();
		ArrayList<Integer> entiersFichier = new ArrayList<Integer>();
		ArrayList<Sommet> sommetsGraphe = new ArrayList<Sommet>();
		ArrayList<Arete> aretesGraphe = new ArrayList<Arete>();
		
		Scanner scanner = new Scanner(new FileReader(cheminFichier));
		Pattern pattern = Pattern.compile("[^0-9]");
		scanner.useDelimiter(pattern);
		
		while (scanner.hasNext()) {
			try {
		    	entiersFichier.add(scanner.nextInt());
		    } catch (InputMismatchException e) {
		    	continue;
		    }
		}
		
		if (entiersFichier.size() != 0) {
			g.setNbSommets(entiersFichier.get(0));
			g.setNbAretes(entiersFichier.get(1));
			
			for (int i=0 ; i<entiersFichier.get(0) ; i++) {
				sommetsGraphe.add(new Sommet(i));
			}
			g.setSommets(sommetsGraphe);
			
			for (int i=2 ; i<entiersFichier.size()-2 ; i=i+3) {
				aretesGraphe.add(new Arete(sommetsGraphe.get(entiersFichier.get(i)), sommetsGraphe.get(entiersFichier.get(i+1)), entiersFichier.get(i+2)));
			}
			g.setAretes(aretesGraphe);
			
			g.setMatriceAdjacence(g.construireMatriceAdjacence());
		} else {
			System.out.println("Le fichier ne contient pas d'informations liées aux graphes.");
		}
		
		return g;
	}
	
}