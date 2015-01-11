package wator;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Cette classe représente le système multi-agent
 * @author Julia Leven et Jérémy Bossut
 */
public class SMA {
	
	// L'environnement
	private Environment env;
	
	// Le numéro du tour
	private int chronon;
	
	// La latence
	private static final int LATENCY = 200;
	
	// Le nombre de requins au départ
	private static final int NB_SHARKS = 50;
	
	// Le nombre de poissons au début
	private static final int NB_FISH = 250;
	
	// Le nombre de tour avant la fin du programme
	private int nbTurn;
	
	/**
	 * Le constructeur représentant le système multi-agent
	 * @param nbTurn le nombre de tour avant la fin du programme
	 */
	public SMA(int nbTurn) {
		this.init();
		this.display();
		this.nbTurn = nbTurn;
	}
	
	/**
	 * Cette méthode permet d'initialiser l'environnement
	 */
	public void init() {
		this.env = new Environment();
		// Initialisation des requins
		for (int i = 0; i < NB_SHARKS ; i++) {
			Position pos = this.env.getRandomFreePosition();
			new Shark(this.env, pos);
		}
		// Initialisation des poissons
		for (int i = 0; i < NB_FISH ; i++) {
			Position pos = this.env.getRandomFreePosition();
			new Fish(this.env, pos);
		}
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
		// On affiche l'environnement 
		this.display();
		// On génère les statistiques
		this.env.generateStatByTurn();
	}
	
	/**
	 * Cette méthode permet de jouer
	 */
	public void run() {
		System.out.println("Run Wator");
		this.chronon = 0;
		this.env.printFirstLineStats();
		while (this.chronon < this.nbTurn) {
			this.runOnce();
			this.chronon++;
		}
	}
	
	public void display() {
		this.env.display();
		try {
		    TimeUnit.MILLISECONDS.sleep(LATENCY);
		} catch (InterruptedException e) {
		    //Handle exception
		}
	}
}
