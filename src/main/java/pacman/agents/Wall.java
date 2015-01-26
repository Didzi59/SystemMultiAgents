package pacman.agents;

import java.awt.Color;
import java.awt.Graphics;

import core.Environment;
import core.Position;

/**
 * Cette classe représente un Mur
 * @author Julia Leven et Jéremy Bossut
 */
public class Wall extends PacmanAgent {
	
	/**
	 * Le constructeur représentant un mur
	 * @param env l'environnement dans lequel se situe un mur
	 * @param pos la position du mur dans l'environnement
	 */
	public Wall(Environment env, Position pos) {
		super(env, 4, pos);
	}
	
	/**
	 * Cette méthode permet de savoir si l'agent est un mur
	 * @return true, l'agent est un mur
	 */
    public boolean isWall() {
        return true;
    }

	/**
	 * Cette méthode récupère la couleur représentant un mur
	 * @return la couleur représentant un mur
	 */
    public Color getColor() {
        return Color.BLACK;
    }
	
    /**
	 * Cette méthode retourne le nom de l'agent
	 * @return le nom de l'agent
	 */
	public String getName() {
		return "Wall";
	}
    
	/**
	 * Cette méthode représente l'agent dans l'environnement
	 */
	public void representationAgent(Graphics g, int x, int y, int squareWidth, int squareHeight) {
		g.setColor(this.getColor());
		g.fillRect(x * squareWidth, y * squareHeight, squareWidth, squareHeight);
	}
	
	/** 
	 * Cette méthode correpond aux actions réalisées par l'agent lorsque c'est son tour
	 */
	public void doIt() {
		return;
	}

}
