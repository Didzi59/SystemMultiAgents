package wator;

import java.awt.Image;

/**
 * Cette classe abstraite représente un agent qui va évoluer dans l'environnement
 * @author Jérémy Bossut et Julia Leven
 */
public abstract class Agent {
	
	// L'identifiant de l'agent
	protected int idAgent;
	
	// La position de l'agent dans l'environnement 
	protected Position pos;
	
	// L'environnement dans lequel évolue l'agent
	protected Environment env;
	
	// L'âge de l'agent
	protected int age;
	
	// Le délai de reproduction
	protected int breedingAge;
	
	
	/**
	 * Le constructeur représentant un agent 
	 * @param idAgent l'identifiant de l'agent
	 * @param env l'environnement dans lequel évolue l'agent
	 * @param pos la position de l'agent dans l'environnement
	 * @param breedingAge le délai de reproduction de l'agent
	 */
	public Agent(Environment env, int idAgent, Position pos, int breedingAge) {
		this.idAgent = idAgent;
		this.age = 0;
		this.pos = pos;
		this.breedingAge = breedingAge;
		env.addAgent(this);
		this.env = env;
	}

	/**
	 * Cette méthode permet de modifier la position de l'agent dans l'environnement
	 * @param pos la nouvelle position de l'agent 
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}

	/**
	 * Cete méthode retourne l'environnement dans lequel évolue l'agent
	 * @return l'environnement dans lequel évolue l'agent
	 */
	public Environment getEnv() {
		return env;
	}

	/**
	 * Cette méthode permet de modifier l'environnement dans lequel évolue l'agent
	 * @param env le nouvel environnement dans lequel évolue l'agent
	 */
	public void setEnv(Environment env) {
		this.env = env;
	}
	
	/**
	 * Cette méthode retourne l'identifiant de l'agent
	 * @return l'identifiant de l'agent
	 */
	public int getId() {
		return this.idAgent;
	}

	/**
	 * Cette méthode retourne l'âge de l'agent
	 * @return l'âge de l'agent 
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Cette méthode retourne le nom de l'agent
	 * @return le nom de l'agent
	 */
	public abstract String getName();
	
	/**
	 * Cette méthode retourne la position de l'agent dans l'environnement
	 * @return la position de l'agent dans l'environnement
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Cette méthode retourne le délai de reproduction de l'agent
	 * @return le délai de reproduction de l'agent
	 */
	public int getBreedingAge() {
		return breedingAge;
	}

	/** 
	 * Cette méthode correpond aux actions réalisées par l'agent lorsque c'est son tour
	 */
	public abstract void doIt();
	
	/**
	 * Cette méthode permet à l'agent de se reproduire
	 */
	public void breed() {
		/* Bébé poisson : si il y a un espace libre autour, on le met aléatoirement autour sur un espace libre,
		 * sinon on choisit un espace libre aléatoire dans la grille
		 */
		/** L'agent peut se reproduire */
		if (this.age != 0 && this.age % this.breedingAge == 0) {
			Position newPos = this.env.getRandomFreeNeighborPosition(this.pos);
			// On n'a pas trouvé de place libre dans les environs, on crée l'agent n'importe où sur l'environnement
			if (newPos == null) {
				newPos = this.env.getRandomFreePosition();
			}
			// On a trouvé une place libre dans les alentours, on crée l'agent à cet endroit 
			if (newPos != null) {
				this.createAgent(newPos);
			}
		}		
	}
	
	/** 
	 * Cette méthode permet de créer un nouveau agent à une position indiquée
	 * @param pos la position où l'on crée le nouvel agent
	 */
	public abstract void createAgent(Position pos);

	/**
	 * Cette méthode permet à l'agent de se déplacer
	 */
	public void move() {
		Position newPos = this.env.getRandomFreeNeighborPosition(this.pos);
		this.moveTo(newPos);
	}
	
	/**
	 * Cette méthode permet à l'agent de se déplacer à la position indiquée
	 * @param newPos la position où l'agent va se déplacer
	 */
	public void moveTo(Position newPos) {
		if (newPos != null) {
			//update de la position de l'agent et préciser à l'environnement qu'il n'est plus là et qu'il s'est déplacé 
			this.env.moveAgent(this.pos, newPos);
			this.setPos(newPos);			
		}
	}
	
	/**
	 * Cette méthode permet d'incrémenter l'âge de l'agent
	 */
	public void aging() {
		this.age++;
	}
	
	/**
	 * Cette méthode permet de savoir si un agent est mangeable
	 * @return true si l'agent est mangeable, false sinon
	 */
	public abstract boolean isEatable();

	public abstract Image getImage();
}
