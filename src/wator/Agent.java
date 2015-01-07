package wator;

import java.util.ArrayList;
import java.util.Collections;

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

	public abstract void doIt();
	
	public void breed() {
		/* Bébé poisson : si il y a un espace libre autour, on le met aléatoirement autour sur un espace libre,
		 * sinon on choisit un espace libre aléatoire dans la grille
		 */
	}
	
	public void move() {
		ArrayList<Position> freeNeighborsList = this.env.getFreeNeighborsList(this.pos);
		//randomized position
		Collections.shuffle(freeNeighborsList);
		Position newPos = freeNeighborsList.get(0);
		//update de la position de l'agent et préciser à l'environnement qu'il n'est plus là et qu'il s'est déplacé 
		this.env.moveAgent(this.pos, newPos);
		this.setPos(newPos);
		//Ne pas oublier dans doIt de modifier l'âge de l'agent
	}
	
	public void aging() {
		this.age++;
	}
	
	public abstract boolean isEatable();
}
