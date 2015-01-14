package wator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import view.WatorView;

/**
 * Cette classe représente le système multi-agent
 * @author Julia Leven et Jérémy Bossut
 */
public class SMA extends Observable {
	
	// L'environnement
	private Environment env;
	
	// Le numéro du tour
	private int chronon;
	
	// La latence
	private static final int LATENCY = 100;
	
	// Le nombre de requins au départ
	private static final int NB_SHARKS = 100;
	
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
		new WatorView(this);
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
		// On génère les statistiques
		this.env.generateStatByTurn();
		// On affiche l'environnement 
		this.display();
	}
	
	/**
	 * Cette méthode permet de jouer
	 */
	public void run() {
		System.out.println("Run Wator");
		this.chronon = 0;
		this.env.printFirstLineStats();
		while (!this.isTerminated()) {
			this.runOnce();
			this.chronon++;
		}
	}
	
	public boolean isTerminated() {
		boolean res = (this.env.getNbFish() == Environment.NB_ROWS * Environment.NB_COLS || this.env.getAgentsList().size() == 0);
		if (this.nbTurn != 0) {
			res = res || (this.chronon > this.nbTurn);
		}
		return res; 
	}
	
    /**
     * Cette méthode permet de rafraichir la vue, afin que celle-ci soit toujours en accord avec l'environnement
     */
	public void display() {
		this.setChanged();
		super.notifyObservers();
		try {
		    TimeUnit.MILLISECONDS.sleep(LATENCY);
		} catch (InterruptedException e) {
		    //Handle exception
		}
	}
	
	public Environment getEnv() {
		return this.env;
	}

	public int getChronon() {
		return chronon;
	}
	
}
