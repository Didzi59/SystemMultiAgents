package core;

import core.view.View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

/**
 * Cette classe représente le système multi-agent
 * @author Julia Leven et Jérémy Bossut
 */
public class SMA extends Observable {
	
	// L'environnement
	private Environment env;
	
	// Le nombre de tour avant la fin du programme
	private int nbTurns;
	
	// La latence
	private int latency;
	
	// Le numéro du tour
	private int chronon;
		
	/**
	 * Le constructeur représentant le système multi-agent
	 * @param env l'environnement
	 * @param nbTurns le nombre de tour avant la fin du programme
     * @param latency le temps d'attente entre deux affichages
	 */
	public SMA(Environment env, int nbTurns, int latency) {
		this.env = env;
		new View(this);
		this.nbTurns = nbTurns;
        this.latency = latency;
        this.display();
	}
	
	/**
	 * Cette méthode permet de récupérer l'environnement
	 * @return l'environnement
	 */
	public Environment getEnv() {
		return this.env;
	}

	/**
	 * Cette méthode permet de récupérer le numéro du tour
	 * @return le numéro du tour
	 */
	public int getChronon() {
		return chronon;
	}
	
	/**
	 * Cette méthode permet de comparer deux identifiants pour récupérer le plus grand des deux
	 * @return l'identifiant maximal
	 */
	public int compareId(int id1, int id2) {
		if (id1 >= id2) 
			return id1;
		return id2;
	}
	
	/**
     * Cette méthode permet de rafraichir la vue, afin que celle-ci soit toujours en accord avec l'environnement
     */
	public void display() {
		this.setChanged();
		super.notifyObservers();
		try {
		    TimeUnit.MILLISECONDS.sleep(this.latency);
		} catch (InterruptedException e) {
		    //Handle exception
		}
	}
	
	/**
	 * Cette méthode permet de jouer un tour
	 */
	public void runOnce() {
		ArrayList<Agent> agents = this.env.getAgentsList();
		// Chaque agent réalise une action
		for (Agent agent : agents) {
			//Agent is not dead during this turn
			if (agent == this.env.getAgent(agent.getPos())) {
				this.env.setIdMaxAgent(this.compareId(this.env.getIdMaxAgent(), agent.getId()));
				agent.doIt();
			}
		}
		// On génère les statistiques
		this.env.generateStatByTurn();
		// On affiche l'environnement 
		this.display();
	}
	
	/**
	 * Cette méthode permet de savoir si le jeu doit se terminer
	 * @return true si le jeu doit se terminer, false sinon
	 */
	public boolean isTerminated() {
		boolean res = this.env.isTerminated();
		if (this.nbTurns != 0) {
			res = res || (this.chronon > this.nbTurns);
		}
		return (res); 
	}
	
	/**
	 * Cette méthode permet de jouer
	 */
	public void run() {
		this.chronon = 1;
		this.env.printFirstLineStats();
		while (!this.isTerminated()) {
			this.runOnce();
            this.chronon++;
		}
	}
	
}
