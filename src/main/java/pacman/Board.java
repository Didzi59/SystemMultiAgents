package pacman;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pacman.agents.*;
import core.Environment;
import core.Position;

public class Board extends Environment {
	
	private Map<Position, int[][]> dijkstraMap;
	
	public Board(int nbRows, int nbCols) {
		super(nbRows, nbCols);
		this.dijkstraMap = new HashMap<Position, int[][]>();
	}
	
	public void init(int nbPreys, int nbPredators, int nbWalls) {
		for (int i=0; i < nbWalls; i++) {
			Position pos = this.getRandomFreePosition();
			new Wall(this, pos);
		}
        for (int i=0; i < nbPreys; i++) {
            Position pos = this.getRandomFreePosition();
            new Prey(this, pos);
        }

        for (int i=0; i < nbPredators; i++) {
            Position pos = this.getRandomFreePosition();
            new Predator(this, pos);
        }
        
	}
	
    public Color getDefaultColor() {
    	return Color.LIGHT_GRAY;
    }
    
    //TODO: récupère toutes les proies sur la map
    public Set<Prey> getPreys() {
    	return new HashSet<Prey>();
    }
    
    public void computeDijkstraMap() {
    	Set<Prey> set = this.getPreys();
    	for (Prey prey : set) {
    		Position pos = prey.getPos();
    		if (!this.dijkstraMap.containsKey(pos)) {
        		this.dijkstraMap.put(pos, this.computeDijkstraValues(pos));
    		}
    	}
    }
    
    private void addValue(int[][] tab, Position pos, int value) {
    	if (this.isValidPosition(pos) && tab[pos.getRow()][pos.getCol()] == -1) {
    		tab[pos.getRow()][pos.getCol()] = value;
    	}
    }
    
    public int[][] computeDijkstraValues(Position pos) {
    	int[][] res = new int[this.getNbRows()][this.getNbCols()]; //init à -1 partout
    	
    	this.addValue(res, pos, 0);
    	this.addValue(res, pos.left(), 1);
    	this.addValue(res, pos.right(), 1);
    	this.addValue(res, pos.up(), 1);
    	this.addValue(res, pos.down(), 1);
    	
    	
    	return res;
    }

}
