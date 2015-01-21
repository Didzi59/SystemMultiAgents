package pacman;

import core.Main;
import core.SMA;

public class MainPacman extends Main {
	
	/**
	 * Le méthode principale permettant de lancer le programme Pacman
	 * @param args les arguments en ligne de commande
	 */
	public static void main(String[] args){
		//Initialisation
		MainPacman main = new MainPacman();
		
		// Parser la ligne de commande 
		main.doMain(args);
		
		// Récupération des paramètres passés en ligne de commande
		int nbTurns = Integer.parseInt(Main.getListArguments().get(0));
		int nbRows = Integer.parseInt(Main.getListArguments().get(1));
		int nbCols = Integer.parseInt(Main.getListArguments().get(2));
		int nbWalls = Integer.parseInt(Main.getListArguments().get(3));
        int nbPreys = Integer.parseInt(Main.getListArguments().get(4));
		int nbPredators = Integer.parseInt(Main.getListArguments().get(5));
		
		// Lancement du programme Schelling
		Board board = new Board(nbRows, nbCols);
		board.init(nbPreys, nbPredators, nbWalls);
		new SMA(board,nbTurns,100).run();
	}

}
