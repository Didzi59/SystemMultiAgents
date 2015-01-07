package wator;

public abstract class Agent {
	
	protected Position pos;
	protected Environment env;
	protected int age;
	protected int breedingAge;
	
	public Agent(Environment env, Position pos, int breedingAge) {
		this.age = 0;
		this.pos = pos;
		this.breedingAge = breedingAge;
		env.addAgent(this);
		this.env = env;
	}
	
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	public int getAge() {
		return age;
	}

	public int getBreedingAge() {
		return breedingAge;
	}

	public abstract void doIt(); //Ne pas oublier dans doIt de modifier l'âge de l'agent
	
	public void breed() {
		/* Bébé poisson : si il y a un espace libre autour, on le met aléatoirement autour sur un espace libre,
		 * sinon on choisit un espace libre aléatoire dans la grille
		 */
		if (this.age != 0 && this.age % this.breedingAge == 0) {
			Position newPos = this.env.getRandomFreeNeighborPosition(this.pos);
			if (newPos == null) {
				newPos = this.env.getRandomFreePosition();
			}
			if (newPos != null) {
				this.createAgent(newPos);
			}
		}		
	}
	
	public abstract void createAgent(Position pos);


	public void move() {
		Position newPos = this.env.getRandomFreeNeighborPosition(this.pos);
		if (newPos != null) {
			//update de la position de l'agent et préciser à l'environnement qu'il n'est plus là et qu'il s'est déplacé 
			this.env.moveAgent(this.pos, newPos);
			this.setPos(newPos);			
		}
	}
	
	public void aging() {
		this.age++;
	}
	
	public abstract boolean isEatable();
}
