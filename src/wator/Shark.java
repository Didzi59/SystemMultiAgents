package wator;

public class Shark extends Agent {
	
	public static final int BREEDING_AGE = 10;
	public static final int STARVATION_AGE = 3;
	
	// Compteur sans manger
	private int starvationTime;
	
	public Shark(Environment env, Position pos) {
		super(env, pos, BREEDING_AGE);
		this.starvationTime = 0;
	}
	
	public void feed() {
		//TODO
	}

	@Override
	public void doIt() {
		// TODO Auto-generated method stub
		// S'il se nourrit, il ne se déplace pas (car déplacé pour manger)
	}
	
	public boolean isEatable() {
		return false;
	}
	
	@Override
	public void createAgent(Position pos) {
		new Shark(this.env, pos);
	}

}
