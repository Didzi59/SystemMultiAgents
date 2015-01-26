package pacman;

import java.awt.Color;
import java.util.*;

import pacman.agents.*;
import core.Environment;
import core.Position;

/**
 * Cette classe représente un tableau
 * @author Leven Julia et Bossut Jérémy
 */
public class Board extends Environment {

	// La map contenant toutes les distances
    public Map<Position,int[][]> dijkstraMemory;
	
	/**
	 * Le constructeur représentant un environnement
	 * @param nbRows le nombre de lignes constituant l'environnement
	 * @param nbCols le nombre de colonnes constituant l'environnement
	 */
	public Board(int nbRows, int nbCols) {
		super(nbRows, nbCols);
        this.dijkstraMemory = new HashMap<Position, int[][]>();
	}
	
	/**
	 * Cette méthode permet d'initialiser l'environnement
	 * @param nbPreys le nombre de proies dans l'environnement
     * @param nbPredators le nombre de prédateurs dans l'environnement
	 * @param nbWalls le nombre de murs dans l'environnement
	 */
	public void init(int nbPreys, int nbPredators, int nbWalls) {
		// Initialisation des murs
		for (int i=0; i < nbWalls; i++) {
			Position pos = this.getRandomFreePosition();
			new Wall(this, pos);
		}
		// Initialisation du nombre de proies
        for (int i=0; i < nbPreys; i++) {
            Position pos = this.getRandomFreePosition();
            Prey p = new Prey(this, pos);
            // Déplace la proie si elle se trouve dans une position bloquante
            while (p.isBlocked()) {
            	pos = this.getRandomFreePosition();
            	p.moveTo(pos);
            	System.out.println("Déblocage : "+pos);
            }
        }
        // Initialisation du nombre de prédateurs
        for (int i=0; i < nbPredators; i++) {
            Position pos = this.getRandomFreePosition();
            new Predator(this, pos);
        }
        
	}

    /**
	 * Cette méthode donne la couleur par défaut des cases sans agent
	 */
    public Color getDefaultColor() {
    	return Color.LIGHT_GRAY;
    }

    /**
	 * Cette méthode permet d'ajouter des entêtes aux fichiers de statistiques créés
	 */
	public void printFirstLineStats() {
		return;
	}
	
	/**
	 * Cette méthode permet de générer les statistiques
	 */
	public void generateStatByTurn() {
		return;
	}	
 
	/**
	 * Cette méthode permet de savoir si le jeu doit se terminer
	 * @return true si le jeu doit se terminer, false sinon
	 */
	public boolean isTerminated() {
		return (this.getNbAgent("Prey") == 0);
	}

}
