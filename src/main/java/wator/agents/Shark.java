package wator.agents;

import java.awt.Image;

import core.Position;
import wator.ImageGetter;
import wator.Sea;

/**
 * Cette classe représente un requin
 * @author Jérémy Bossut et Julia Leven
 */
public class Shark extends Animal {
	
	// Le délai de reproduction du requin
	private static final int BREEDING_AGE = 10;
	
	// Le délai sans manger avant que le requin meurt
	private static final int STARVATION_AGE = 3;

	//Le nom de l'agent
	public static final String NAME = "Shark";
	
	// La période durant laquelle le requin n'a pas mangé
	private int starvationTime;
	
	/**
	 * Constructeur représentant un requin
	 * @param env l'environnement dans lequel évolue le requin
	 * @param pos sa position dans l'environnement
	 */
	public Shark(Sea env, Position pos) {
		super(env,1, pos, BREEDING_AGE);
		this.starvationTime = 0;
	}

	/**
	 * Cette méthode retourne le nom de l'agent
	 * @return le nom de l'agent
	 */
    public String getName() {
        return NAME;
    }

    /**
     * Cette méthode permet de savoir si le requin est mangeable
     * @return false, le requin n'est jamais mangeable
     */
	public boolean isEatable() {
		return false;
	}
	
	/**
	 * Cette méthode permet de récupérer l'image représentant un requin
	 */
	public Image getImage() {
		return ImageGetter.IMAGE_SHARK;
	}
	
	/**
	 * Cette méthode crée un nouveau requin et l'ajoute à l'environnement
	 */
	public void createAgent(Position pos) {
		new Shark((Sea) this.env,pos);
	}
	
	/**
	 * Cette méthode permet de savoir si le requin va mourrir
	 * @return true si le requin meurt, false sinon
	 */
    private boolean mustDie() {
        return this.starvationTime >= STARVATION_AGE;
    }

	/** 
	 * Cette méthode permet au requin de se nourrir d'un poisson
	 * @return true si le requin s'est nourri, c'est-à-dire si un poisson était proche de lui, false sinon
	 */
	public boolean feed() {
		Sea envA = (Sea) this.getEnv();
		// Calcul aléatoire : on cherche une position sur laquelle se situe un agent mangeable
        Position newPos = envA.getRandomEatableNeighborPosition(this.pos);
        // Un agent mangeable est proche du requin, il le mange
        if (newPos != null) {
            this.env.removeAgent(newPos);
            this.starvationTime = 0;
            this.moveTo(newPos);
            return true;
        // Aucun agent mangeable ne se situe aux alentours de la position du requin, 
        } else {
            this.starvationTime++;
            return false;
        }
	}

	/**
	 * Cette méthode définit l'action que le requin effectue pendant son tour
	 */
	public void doIt() {
        if (this.mustDie()) {
            this.env.removeAgent(this.pos);
            // Le requin est encore vivant, il a donc un an de plus
        } else {
            this.aging();
        }
        // Le requin cherche à manger
        boolean hasEaten = this.feed();
        // Le requin n'a pas trouvé à manger, il se déplace d'une case
        if (!hasEaten) {
            this.move();
        }
		// Le requin essaie de se reproduire
        this.breed();		
        // Le requin meurt puisqu'il n'a pas mangé durant une certaine période
	}
}
