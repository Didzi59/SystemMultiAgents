package wator;

import java.util.ArrayList;
import java.util.Collections;

import core.Environment;
import core.Position;
import wator.agents.Animal;
import wator.agents.Fish;
import wator.agents.Shark;

public class Sea extends Environment {

	// L'objet permettant de réaliser des statistiques sur l'environnement
	private WatorStat watorStat;

	/**
	 * Le constructeur représentant l'environnement Mer
	 * @param nbRows le nombre de lignes constituant l'environnement
	 * @param nbCols le nombre de colonnes constituant l'environnement
	 */
	public Sea(int nbRows, int nbCols) {
		super(nbRows, nbCols);
		// Initialisation de la classe permettant de réaliser des statistiques et de l'environnement
		this.watorStat = new WatorStat(this);
	}
	
	/**
	 * Cette méthode permet d'initialiser l'environnement
	 */
	public void init(int nbSharks, int nbFish) {
		// Initialisation des requins
		for (int i = 0; i < nbSharks ; i++) {
			Position pos = this.getRandomFreePosition();
			new Shark(this, pos);
		}
        this.nbAgentsByName.put(Shark.NAME, nbSharks);
		// Initialisation des poissons
		for (int i = 0; i < nbFish ; i++) {
			Position pos = this.getRandomFreePosition();
			new Fish(this, pos);
		}
        this.nbAgentsByName.put(Fish.NAME, nbFish);
	}
	
	/**
	 * Cette méthode permet de savoir si le jeu doit se terminer
	 * @return true si le jeu doit se terminer, false sinon
	 */
	public boolean isTerminated() {
		return (this.getNbAgent("Fish") == this.getNbRows() * this.getNbCols() || this.getAgentsList().size() == 0);
	}

    /**
     * Cette méthode permet de récupérer aléatoirement, une position voisine à la position passée en paramètre, qui est libre
     * @param pos la position
     * @return une position voisine et libre
     */
    public Position getRandomFreeNeighborPosition(Position pos) {
        ArrayList<Position> freeNeighbors = this.getFreeNeighborsList(pos);
        if (!freeNeighbors.isEmpty()) {
            Collections.shuffle(freeNeighbors);
            return freeNeighbors.get(0);
        }
        return null;
    }

	/**
	 * Cette méthode permet d'ajouter une position sur laquelle il y a agent mangeable par l'agent situé à la position passée en paramètre
	 * @param voisins la liste des positions voisines de la position passée en paramètre
	 * @param pos la position
	 */
    public void addEatableValidPosition(ArrayList<Position> voisins, Position pos) {
        Animal a = (Animal) this.getAgent(pos);
        if (this.isValidPosition(pos) && a != null && a.isEatable()) {
            voisins.add(pos);
        }
    }
    
    /**
     * Cette méthode permet de récupérer les positions voisines et libres de la position passée en paramètre, sur laquelle il y a un agent mangeable
     * @param pos la position
     * @return une liste contenant les positions voisines et libres de la position passée en paramètre, sur laquelle il y a un agent mangeable
     */
    public ArrayList<Position> getEatableNeighborsList(Position pos) {
        ArrayList<Position> eatableNeighbors = new ArrayList<Position>();

        this.addEatableValidPosition(eatableNeighbors, pos.left());
        this.addEatableValidPosition(eatableNeighbors, pos.right());
        this.addEatableValidPosition(eatableNeighbors, pos.up());
        this.addEatableValidPosition(eatableNeighbors, pos.down());
        this.addEatableValidPosition(eatableNeighbors, pos.leftup());
        this.addEatableValidPosition(eatableNeighbors, pos.rightup());
        this.addEatableValidPosition(eatableNeighbors, pos.leftdown());
        this.addEatableValidPosition(eatableNeighbors, pos.rightdown());

        return eatableNeighbors;
    }
    
    /**
     * Cette méthode permet de récupérer aléatoirement, une position voisine à la position passée en paramètre, sur laquelle il y a un agent mangeable
     * @param pos la position
     * @return une position voisine sur laquelle il y a un agent mangeable
     */
    public Position getRandomEatableNeighborPosition(Position pos) {
        ArrayList<Position> eatableNeighbors = this.getEatableNeighborsList(pos);
        if (!eatableNeighbors.isEmpty()) {
            Collections.shuffle(eatableNeighbors);
            return eatableNeighbors.get(0);
        }
        return null;
    }
    
	/**
	 * Cette méthode permet d'ajouter des entêtes aux fichiers de statistiques créés
	 */
	public void printFirstLineStats() {
		this.watorStat.printFirstLineStats();
	}
	
	/**
	 * Cette méthode permet de générer les statistiques
	 */
	public void generateStatByTurn() {
		this.watorStat.generateStatByTurn();
	}	

}
