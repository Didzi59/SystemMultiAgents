package core;

import java.awt.Color;
import java.awt.Graphics;

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

	
	/**
	 * Le constructeur représentant un agent 
	 * @param idAgent l'identifiant de l'agent
	 * @param pos la position de l'agent dans l'environnement
	 * @param env l'environnement dans lequel évolue l'agent
	 */
	public Agent(Environment env, int idAgent, Position pos) {
		this.idAgent = idAgent;
		this.pos = pos;
		env.addAgent(this);
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
	 * Cette méthode retourne la position de l'agent dans l'environnement
	 * @return la position de l'agent dans l'environnement
	 */
	public Position getPos() {
		return pos;
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
	 * Cette méthode retourne le nom de l'agent
	 * @return le nom de l'agent
	 */
	public abstract String getName();
	
	/** 
	 * Cette méthode correpond aux actions réalisées par l'agent lorsque c'est son tour
	 */
	public abstract void doIt();
	
    /**
     * Cette méthode récupère le couleur qui représente un agent
     * @return la couleur qui représente un agent
     */
    public abstract Color getColor();
	
	/**
	 * Cette méthode permet de représenter l'agent dans l'environnement
	 * @param g le graphique 
	 * @param x l'abscisse de l'agent
	 * @param y l'ordonnée de l'agent
	 * @param squareWidth la largeur d'un bloc
	 * @param squareHeight la hauteur d'un bloc
	 */
	public void representationAgent(Graphics g, int x, int y, int squareWidth, int squareHeight) {
		g.setColor(this.getColor());
		g.fillRect(x * squareWidth, y * squareHeight, squareWidth, squareHeight);
	}
	
}
