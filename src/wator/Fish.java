package wator;

public class Fish extends Agent {
	
	public static final int BREEDING_AGE = 2;
	
	public Fish(Environment env, Position pos) {
		super(env, pos, BREEDING_AGE);
	}	
	
	public void doIt() {
		this.breed();
		this.move();
		this.aging();
	}
	
	public boolean isEatable() {
		return true;
	}

	@Override
	public void createAgent(Position pos) {
		new Fish(this.env, pos);
	}

    public String toString() {
        return "f";
    }
}
