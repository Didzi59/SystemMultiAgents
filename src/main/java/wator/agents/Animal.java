package wator.agents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import core.Agent;
import core.Environment;
import core.Position;
import wator.Sea;

public abstract class Animal extends Agent {
	
	// L'âge de l'agent
	protected int age;
		
	// Le délai de reproduction
	protected int breedingAge;
	
	/**
	 * Le constructeur représentant un animal
	 * @param env l'environnement dans lequel évolue l'agent
	 * @param idAgent l'identifiant de l'agent
	 * @param pos la position de l'agent dans l'environnement
	 * @param breedingAge le délai de reproduction de l'animal
	 */
	public Animal(Environment env, int idAgent, Position pos, int breedingAge) {
		super(env, idAgent, pos);
		this.age = 0;
		this.breedingAge = breedingAge;
	}
	
	/**
	 * Cette méthode retourne l'âge de l'agent
	 * @return l'âge de l'agent 
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Cette méthode permet d'incrémenter l'âge de l'agent
	 */
	public void aging() {
		this.age++;
	}
	
	/**
	 * Cette méthode permet à l'agent de se reproduire
	 */
	public void breed() {
		/* Bébé poisson : si il y a un espace libre autour, on le met aléatoirement autour sur un espace libre,
		 * sinon on choisit un espace libre aléatoire dans la grille
		 */
		/** L'agent peut se reproduire */
		if (this.age != 0 && this.age % this.breedingAge == 0) {
            Sea sea = (Sea) this.env;
            Position newPos = sea.getRandomFreeNeighborPosition(this.pos);
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
     * Cette méthode permet à l'agent de se déplacer
     */
    public void move() {
        Sea sea = (Sea) this.env;
        Position newPos = sea.getRandomFreeNeighborPosition(this.pos);
        this.moveTo(newPos);
    }
	
    public void representationAgent(Graphics g, int x, int y, int squareWidth, int squareHeight) {
		g.drawImage(this.getImage(),
        		x * squareWidth, y * squareHeight, 
        		x * squareWidth+squareWidth, y * squareHeight+squareHeight, 
                0, 0,
                128, 128,
                Color.BLUE,
                null);
	}
	
	/**
     * Cette méthode permet de savoir si le requin est mangeable
     * @return false, le requin n'est jamais mangeable
     */
	public abstract boolean isEatable();
	
	/**
	 * Cette méthode permet de récupérer l'image représentant l'animal
	 */
	public abstract Image getImage();

    /**
     * Cette méthode permet de créer un nouvel animal à une position indiquée
     * @param pos la position où l'on crée le nouvel agent
     */
    public abstract void createAgent(Position pos);

}
