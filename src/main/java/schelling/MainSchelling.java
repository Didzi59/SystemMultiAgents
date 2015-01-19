package schelling;

import core.Main;
import core.SMA;

/**
 * La classe permet de lancer le programme Schelling
 * @author Julia Leven et Jérémy Bossut
 */
public class MainSchelling extends Main {
	
	/**
	 * Le méthode principale permettant de lancer le programme Schelling
	 * @param args les arguments en ligne de commande
	 */
	public static void main(String[] args){
		//Initialisation
		MainSchelling main = new MainSchelling();
		
		// Parser la ligne de commande 
		main.doMain(args);
		
		// Récupération des paramètres passés en ligne de commande
		int nbTurns = Integer.parseInt(Main.getListArguments().get(0));
		int nbRows = Integer.parseInt(Main.getListArguments().get(1));
		int nbCols = Integer.parseInt(Main.getListArguments().get(2));
		int nbGreenHumans = Integer.parseInt(Main.getListArguments().get(3));
        int nbRedHumans = Integer.parseInt(Main.getListArguments().get(4));
		int tolerance = Integer.parseInt(Main.getListArguments().get(5));
		
		// Lancement du programme Schelling
		District district = new District(nbRows, nbCols);
		district.init(nbGreenHumans, nbRedHumans, tolerance);
		new SMA(district,nbTurns,100).run();
	}

}

