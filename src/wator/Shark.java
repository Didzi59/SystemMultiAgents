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
	
	public boolean feed() {
        Position newPos = this.env.getRandomEatableNeighborPosition(this.pos);
        if (newPos != null) {
            this.env.removeAgent(newPos);
            this.starvationTime = 0;
            this.moveTo(newPos);
            return true;
        } else {
            this.starvationTime++;
            return false;
        }
	}

	@Override
	public void doIt() {
		// S'il se nourrit, il ne se déplace pas (car déplacé pour manger)
        this.breed();
        boolean hasEaten = this.feed();
        if (!hasEaten) {
            this.move();
        }
        if (this.mustDie()) {
            this.env.removeAgent(this.pos);
        }
        this.aging();
	}

    private boolean mustDie() {
        return this.starvationTime >= STARVATION_AGE;
    }
	
	public boolean isEatable() {
		return false;
	}
	
	@Override
	public void createAgent(Position pos) {
		new Shark(this.env, pos);
	}

    public String toString() {
        return "S";
    }

}
