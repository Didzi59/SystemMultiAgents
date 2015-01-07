package wator;

public class Shark extends Agent {
	
	public static final int BREEDING_AGE = 10;
	
	public Shark(Position pos) {
		super(BREEDING_AGE, pos);
	}
	
	public void feed() {
		//TODO
	}

	@Override
	public void doIt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void breed() {
		// TODO Auto-generated method stub
		
	}

	
	public boolean isEatable() {
		return false;
	}

}
