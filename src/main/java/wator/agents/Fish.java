package wator.agents;

import java.awt.Image;

import core.Environment;
import core.Position;
import wator.ImageGetter;

/**
 * Cette classe représente un poisson
 * @author Jérémy Bossut et Julia Leven
 */
public class Fish extends Animal {
	
	//Le délai de reproduction d'un poisson
	public static final int BREEDING_AGE = 2;

	//Le nom de l'agent
	public static final String NAME = "Fish";
	
	/**
	 * Le constructeur représentant un poisson
	 * @param env l'environnement dans lequel évolue un poisson
	 * @param pos la position du poisson dans l'environnement
	 */
	public Fish(Environment env, Position pos) {
		super(env, 0, pos, BREEDING_AGE);
	}

	/**
	 * Cette méthode retourne le nom de l'agent
	 * @return le nom de l'agent
	 */
    public String getName() {
        return NAME;
    }

    /**
	 * Cette méthode permet de savoir si un poisson est mangeable
	 * @return true le poisson est toujours mangeable
	 */
	public boolean isEatable() {
		return true;
	}
    
	/**
	 * Cette méthode permet de récupérer l'image représentant un poisson
	 */
	public Image getImage() {
		return ImageGetter.IMAGE_FISH;
	}

	/**
	 * Cette méthode permet de créer un poisson
	 * @param pos la position du poisson dans l'environnement
	 */
	public void createAgent(Position pos) {
		new Fish(this.env, pos);
	}
	
	/**
	 * Cette méthode correspond aux actions réalisées par le poisson lorsque c'est son tour
	 * Il peut se reproduire et manger
	 */
	public void doIt() {
        this.aging();
		this.breed();
		this.move();
	}
}
