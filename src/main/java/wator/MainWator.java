package wator;

import core.Main;
import core.SMA;

/**
 * La classe permet de lancer le programme Wator
 * @author Julia Leven et Jérémy Bossut
 */
public class MainWator extends Main {
	
	/**
	 * Le méthode principale permettant de lancer le programme Wator
	 * @param args les arguments en ligne de commande
	 */
	public static void main(String[] args){
		// Initialisation
		MainWator mainWator = new MainWator();
		
		// Parser la ligne de commande
		mainWator.doMain(args);
		
		// Récupération des paramètres passés en ligne de commande 
		int nbTurns = Integer.parseInt(Main.getListArguments().get(0));
		int nbRows = Integer.parseInt(Main.getListArguments().get(1));
		int nbCols= Integer.parseInt(Main.getListArguments().get(2));
		int nbSharks = Integer.parseInt(Main.getListArguments().get(3));
		int nbFish = Integer.parseInt(Main.getListArguments().get(4));
		
		// Lancement du programme Wator
		Sea sea = new Sea(nbRows,nbCols);
		sea.init(nbSharks,nbFish);
		new SMA(sea,nbTurns,100).run();
    }

}
