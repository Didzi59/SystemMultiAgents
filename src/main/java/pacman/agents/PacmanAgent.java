package pacman.agents;

import java.awt.Graphics;

import core.Agent;
import core.Environment;
import core.Position;

/**
 * Cette classe représente un agent dans le jeu Pacman
 * @author Julia Leven et Jérémy Bossut
 */
public abstract class PacmanAgent extends Agent {

	/**
	 * Le constructeur représentant un agent évoluant dans le jeu pacman
	 * @param env l'environnement dans lequel évolue l'agent
	 * @param idAgent l'identifiant général pour chaque agent
	 * @param pos la position de l'agent dans l'environnement
	 */
	public PacmanAgent(Environment env, int idAgent, Position pos) {
		super(env, idAgent, pos);
	}
	
    /**
     * Cette méthode permet de savoir si l'agent est un mur
     * @return false, l'agent n'est pas un mur
     */
    public boolean isWall() {
        return false;
    }
    
    /**
     * Cette méthode permet de savoir si l'agent est mangeable
     * @return false, l'agent n'est pas mangeable
     */
	public boolean isEatable() {
        return false;
    }
	
	/**
	 * Cette méthode représente l'agent dans l'environnement
	 */
	public void representationAgent(Graphics g, int x, int y, int squareWidth, int squareHeight) {
		g.setColor(this.getColor());
		g.fillOval(x * squareWidth, y * squareHeight, squareWidth, squareHeight);
	}
	
	/** 
	 * Cette méthode correpond aux actions réalisées par l'agent lorsque c'est son tour
	 */
	public abstract void doIt();

}
