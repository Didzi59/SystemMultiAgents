package wator;

/**
 * Cette classe représente un poisson
 * @author Jérémy Bossut et Julia Leven
 */
public class Fish extends Agent {
	
	//Le délai de reproduction d'un poisson
	public static final int BREEDING_AGE = 2;
	
	/**
	 * Le constructeur représentant un poisson
	 * @param env l'environnement dans lequel évolue un poisson
	 * @param pos la position du poisson dans l'environnement
	 */
	public Fish(Environment env, Position pos) {
		super(env, "Poisson",0, pos, BREEDING_AGE);
	}	
	
	/**
	 * Cette méthode correspond aux actions réalisées par le poisson lorsque c'est son tour
	 * Il peut se reproduire et manger
	 */
	public void doIt() {
		this.breed();
		this.move();
		this.aging();
	}
	
	/**
	 * Cette méthode permet de savoir si un poisson est mangeable
	 * @return true le poisson est toujours mangeable
	 */
	public boolean isEatable() {
		return true;
	}

	/**
	 * Cette méthode permet de créer un poisson
	 * @param la position du poisson dans l'environnement
	 */
	public void createAgent(Position pos) {
		new Fish(this.env, pos);
	}

	/**
	 * Cette méthode donne la représentation d'un poisson dans l'environnement
	 * @return la représentation d'un poisson dans l'environnement
	 */
    public String toString() {
        return "f";
    }
}
