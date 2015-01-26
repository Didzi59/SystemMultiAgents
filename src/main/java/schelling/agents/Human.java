package schelling.agents;

import core.Agent;
import core.Environment;
import core.Position;
import schelling.District;

/**
 * Cette classe représente un humain
 * @author Leven Julia et Bossut Jéremy
 */
public abstract class Human extends Agent {
	
	// Le seuil de tolérance
	protected int tolerance;
	
	// La similarité avec ses voisins
	protected int similarity;
	
	/**
	 * Le constructeur représentant un humain
	 * @param env l'environnement dans lequel évolue l'humain
	 * @param pos la position de l'humain dans l'environnement
	 */
	public Human(Environment env, Position pos, int tolerance) {
		super(env, 3, pos);
		this.tolerance = tolerance;
	}
	
	/**
	 * Cette méthode récupère la similarité entre l'agent et le reste de son environnement
	 * @return l'actuelle similarité
	 */
	public int getSimilarity() {
		return this.similarity;
	}
	
	/**
	 * Cette méthode permet modifie la similarité entre l'agent et le reste de son environnement
	 * @param similarity la nouvelle similarité
	 */
	public int setSimilarity(int similarity) {
		return this.similarity = similarity;
	}
	
	/**
	 * Cette méthode permet de savoir si l'humain passé en paramètre est de la même catégorie que l'humain actuel
	 * @param h
	 * @return true si les deux humains sont de la même catégorie
	 */
	public boolean isSameType(Human h) {
		return h.getColor() == this.getColor();
	}
	
	/**
	 * Cette méthode permet de calculer la similarité entre l'humain et le rester de son quartier
	 */
	public void calculateSimilarity() {
		District envHuman = (District) this.getEnv();
		int nbSameTypeNeighbors = envHuman.getNbSameTypeNeighbors(this.getPos());
        int nbNeighbors = envHuman.getNbNeighbors(this.getPos());
        if (nbNeighbors == 0) {
        	this.setSimilarity(100);
        } else {
        	this.setSimilarity(nbSameTypeNeighbors*100/nbNeighbors);
        }
	}

	/**
	 * Cette méthode permet de savoir si l'humain est satisfait de son quartier
	 * @return true si l'agent est statisfait de son quartier, false sinon
	 */
	public boolean isSatisfied() {
		return this.similarity >= this.tolerance;
	}
	
	/**
	 * Cette méthode correspond aux actions réalisées par l'humain lorsque c'est son tour
	 * Il peut déménager si le quartier ne lui convient pas 
	 */
	public void doIt() {
		this.calculateSimilarity();
		if (!this.isSatisfied()) {
			this.moveTo(this.env.getRandomFreePosition());
		}
	}

}
