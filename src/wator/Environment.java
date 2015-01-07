package wator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

public class Environment extends Observable {
	
	private Agent[][] map;
	
	//Hauteur = nombre de lignes
	private static final int NB_ROWS = 20;
	//Largeur = nombre de colonnes
	private static final int NB_COLS = 20;
	
	public Environment() {
		this.map = new Agent[NB_ROWS][NB_COLS];
		for(int i = 0; i < NB_ROWS; i++) {
			for(int j = 0; j < NB_COLS; j++) {
				this.map[i][j] = null;
			}
		}
	}

	public Agent[][] getMap() {
		return map;
	}
	
	public boolean isValidPosition(Position pos) {
		int i = pos.getRow();
		int j = pos.getCol();
		return i < NB_ROWS && i >= 0 && j < NB_COLS && j >= 0 ;
	}
	
	public ArrayList<Position> addValidPosition(ArrayList<Position> voisins, Position pos) {
        if (this.isValidPosition(pos)) {
        	voisins.add(pos);
        }
        return voisins;
	}
	
	public ArrayList<Position> addFreeValidPosition(ArrayList<Position> voisins, Position pos) {
        if (this.isValidPosition(pos) && this.map[pos.getRow()][pos.getCol()] == null) {
        	voisins.add(pos);
        }
        return voisins;
	}
	
    /**
     * Cette méthode permet de récupérer les positions voisines de la position passée en paramètre
     * @param pos la position
     * @return une liste contenant les positions voisines
     */
    public ArrayList<Position> getNeighborsList(Position pos) {
        ArrayList<Position> neighbors = new ArrayList<Position>();
        
        neighbors = this.addValidPosition(neighbors, pos.left());
        neighbors = this.addValidPosition(neighbors, pos.right());
        neighbors = this.addValidPosition(neighbors, pos.up());
        neighbors = this.addValidPosition(neighbors, pos.down());
        neighbors = this.addValidPosition(neighbors, pos.leftup());
        neighbors = this.addValidPosition(neighbors, pos.rightup());
        neighbors = this.addValidPosition(neighbors, pos.leftdown());
        neighbors = this.addValidPosition(neighbors, pos.rightdown());
        
        return neighbors;
    }
    
    public ArrayList<Position> getFreeNeighborsList(Position pos) {
        ArrayList<Position> freeNeighbors = new ArrayList<Position>();
        
        freeNeighbors = this.addFreeValidPosition(freeNeighbors, pos.left());
        freeNeighbors = this.addFreeValidPosition(freeNeighbors, pos.right());
        freeNeighbors = this.addFreeValidPosition(freeNeighbors, pos.up());
        freeNeighbors = this.addFreeValidPosition(freeNeighbors, pos.down());
        freeNeighbors = this.addFreeValidPosition(freeNeighbors, pos.leftup());
        freeNeighbors = this.addFreeValidPosition(freeNeighbors, pos.rightup());
        freeNeighbors = this.addFreeValidPosition(freeNeighbors, pos.leftdown());
        freeNeighbors = this.addFreeValidPosition(freeNeighbors, pos.rightdown());
        
        return freeNeighbors;
    }
    
    public Position getRandomFreeNeighborPosition(Position pos) {
        ArrayList<Position> freeNeighbors = this.getFreeNeighborsList(pos);
		if (!freeNeighbors.isEmpty()) {
	        Collections.shuffle(freeNeighbors);
	        return freeNeighbors.get(0);
		}
		return null;
    } 
    
    public Position getRandomFreePosition() {
        ArrayList<Position> freePositions = new ArrayList<Position>();
        for (int i = 0 ; i < NB_ROWS ; i++) {
            for (int j = 0 ; j < NB_COLS ; j++) {
            	freePositions = this.addFreeValidPosition(freePositions, new Position(i,j));
            }
        }
		if (!freePositions.isEmpty()) {
	        Collections.shuffle(freePositions);
	        return freePositions.get(0);
		}
		return null;
    }   

	public void moveAgent(Position pos, Position newPos) {
		Agent a = this.map[pos.getRow()][pos.getCol()];
		this.map[newPos.getRow()][newPos.getCol()] = a;
		this.map[pos.getRow()][pos.getCol()] = null;
		
		a.setEnv(this);
	}
	
	public void addAgent(Agent agent) {
		Position pos = agent.getPos();
		this.map[pos.getRow()][pos.getCol()] = agent;
	}
}