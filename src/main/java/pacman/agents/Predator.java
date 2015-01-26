package pacman.agents;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.Agent;
import core.Environment;
import core.Position;

/**
 * Cette classe représente un prédateur
 * @author Julia Leven et Jérémy Bossut
 */
public class Predator extends PacmanAgent {

	/**
	 * Le constructeur représentant un prédateur
	 * @param env l'environnement dans lequel évolue un prédateur
	 * @param pos la position d'un prédateur dans l'environnement
	 */
	public Predator(Environment env, Position pos) {
		super(env, 5, pos);
	}
	
	/**
	 * Cette méthode récupère la couleur représentant une proie
	 * @return la couleur représentant une proie
	 */
	public Color getColor() {
		return Color.YELLOW;
	}

	/**
	 * Cette méthode retourne le nom de l'agent
	 * @return le nom de l'agent
	 */
	public String getName() {
		return "Predator";
	}

	/** 
	 * Cette méthode correpond aux actions réalisées par l'agent lorsque c'est son tour
	 */
	public void doIt() {
		// On regarde si l'agent peut se nourrir
        this.feed();
        // La position pour que le prédateur s'approche de la proie
        Position nextPosition = this.moveToPrey();
        // Le prédateur s'approche de la proie
        if (nextPosition != null) {
            this.moveTo(nextPosition);
        }
	}

    /**
     * Cette méthode permet au prédateur de manger une proie située sur une case voisine
     */
    public void feed() {
        List<Position> listNeighbors = this.env.getNeighborsList(this.pos);
        // On regarde l'ensemble des positions voisines
        for(int i =0; i < listNeighbors.size(); i++) {
            PacmanAgent agent = (PacmanAgent) this.env.getAgent(listNeighbors.get(i));
            // Si l'agent est mangeable, on calcule la prochaine position du prédateur afin d'avoir le plus court chemin
            if ((agent != null) && (agent.isEatable())) {
                this.env.removeAgent(listNeighbors.get(i));
            }
        }
    }

    /**
     * Cette méthode renvoie la liste des proies évoluant dans l'environnement
     * @return la liste des proies évoluant dans l'environnement
     */
    private ArrayList<Prey> getPreysList() {
        ArrayList<Agent> agents = this.env.getAgentsList();
        ArrayList<Prey> preys = new ArrayList<Prey>();
        for (Agent agent : agents) {
            PacmanAgent a = (PacmanAgent) agent;
            // L'agent est mangeable donc c'est une proie
            if (a.isEatable()) {
                preys.add((Prey)a);
            }
        }
        return preys;
    }

    /**
     * Cette méthode renvoie la proie la plus proche
     * @return la proie la plus proche
     */
    private Prey getClosestPrey() {
        List<Prey> preys = this.getPreysList();
        int value = Integer.MAX_VALUE;
        Prey bestPrey = null;
        for (Prey prey : preys) {
            if (prey.distanceTo(this.pos) < value) {
                value = prey.distanceTo(this.pos);
                bestPrey = prey;
            }
        }
        return bestPrey;
    }

    /**
     * Cette méthode renvoie la position sur laquelle doit aller un prédateur pour s'approcher au maximum d'une proie
     */
    public Position moveToPrey() {
        Prey prey = this.getClosestPrey();
        if (prey != null) {
            int[][] dijkstraValues = prey.computeDijkstra();
            List<Position> neighbors = this.env.getFreeNeighborsList(this.pos);
            Collections.shuffle(neighbors);
            for (Position p : neighbors) {
                if (dijkstraValues[p.getRow()][p.getCol()] == dijkstraValues[pos.getRow()][pos.getCol()] - 1) {
                    return p;
                }
            }
        }
        return null;
    }

}
