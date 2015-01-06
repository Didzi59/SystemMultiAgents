package wator;

public class Fish extends Agent {
	
	public static final int BREEDING_AGE = 2;
	
	public Fish(Position pos) {
		super(BREEDING_AGE, pos);
	}
	
	public void breed() {
		if (age != 0 && age % this.breedingAge == 0) {
			//TODO : pondre à côté ou non
		}
	}
	
	public void move() {
		//TODO : faire un random. Si la case est libre OK, sinon faire le tour dans le sens des aiguilles d'une montre
		// gérer les murs 
	}
	
	public void doIt() {
		this.breed();
		this.move();
		this.aging();
	}
	
	public boolean isEatable() {
		return true;
	}
}
