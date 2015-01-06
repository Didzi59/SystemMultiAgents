package wator;

public abstract class Agent {
	
	private Position pos;
	private Environment env;
	protected int age;
	protected int breedingAge;
	
	public Agent(int breedingAge, Position pos) {
		this.age = 0;
		this.pos = pos;
		this.breedingAge = breedingAge;
	}
	
	public abstract void doIt();
	
	public abstract void breed();
	
	public abstract void move();
	
	public void aging() {
		this.age++;
	}
	
	public abstract boolean isEatable();
}
