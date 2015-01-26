package pacman.agents;

import java.awt.Color;
import java.util.*;

import core.Agent;
import core.Environment;
import core.Position;
import pacman.Board;

/**
 * Cette classe représente une proie
 * @author Jérémy Bossut et Julia Leven
 */
public class Prey extends PacmanAgent {

	/**
	 * Le constructeur représentant une proie
	 * @param env l'environnement dans lequel évolue une proie
	 * @param pos la position d'une proie dans l'environnement
	 */
	public Prey(Environment env, Position pos) {
		super(env, 6, pos);
	}

	/**
	 * Cette méthode récupère la couleur représentant une proie
	 * @return la couleur représentant une proie
	 */
	public Color getColor() {
		return Color.GREEN;
	}

	/**
	 * Cette méthode retourne le nom de l'agent
	 * @return le nom de l'agent
	 */
	public String getName() {
		return "Prey";
	}

	/**
     * Cette méthode permet de savoir si la proie est mangeable
     * @return true, la proie est toujours mangeable;
     */
	public boolean isEatable() {
		return true;
	}

    /**
     * Cette méthode correpond aux actions réalisées par l'agent lorsque c'est son tour
     */
    public void doIt() {
        Position barycenter = this.getBarycenter();
        Position oppositePosition = null;
        if (this.pos.getRow() < barycenter.getRow()) {
            oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.up());
            if (this.pos.getCol() < barycenter.getCol()) {
                oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.left());
                oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.leftup());
            } else {
                oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.right());
                oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.rightup());
            }
        } else {
            oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.down());
            if (this.pos.getCol() < barycenter.getCol()) {
                oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.left());
                oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.leftdown());
            } else {
                oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.right());
                oppositePosition = changeIfValidAndFree(oppositePosition, this.pos.rightdown());
            }

        }
        if (oppositePosition != null) {
            this.moveTo(oppositePosition);
        }
    }

    /**
     * Cette méthode modifie la position si aucun agent ne se situe sur cette position
     * @param position la position
     * @param newPosition la nouvelle position
     * @return la nouvelle position
     */
    private Position changeIfValidAndFree(Position position, Position newPosition) {
        if (this.env.isValidPosition(newPosition) && this.env.getAgent(newPosition) == null) {
            position = newPosition;
        }
        return position;
    }
    
    /**
     * Cette méthode vérifie que la proie n'est pas coincée entre des murs
     * @return vrai si la proie est bloquée, faux sinon
     */
    public boolean isBlocked() {
    	int nbZeros = 0;
    	int rows = this.env.getNbRows();
    	int cols = this.env.getNbCols();
    	int[][] dijkstraValues = this.computeDijkstra();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dijkstraValues[i][j] == 0) {
                    nbZeros++;
                }
            }
        }
    	return nbZeros > (rows*cols/4);
    }

    /**
     * Cette méthode récupère la distance entre la position pos et la proie
     * @param pos la position
     * @return  la distance entre la position pos et la proie
     */
    public int distanceTo(Position pos) {
        return this.computeDijkstra()[pos.getRow()][pos.getCol()];
    }
    
    /**
     * Cette méthode initialise la map des distances 
     * @return la map des distances
     */
    private int[][] initTab() {
        int[][] tab = new int[this.env.getNbRows()][this.env.getNbCols()];
        for (int i = 0; i < this.env.getNbRows(); i++) {
            for (int j = 0; j < this.env.getNbCols(); j++) {
                PacmanAgent a = (PacmanAgent) this.env.getAgent(new Position(i,j));
                if ((a != null) && a.isWall()) {
                    tab[i][j] = -1;
                } else {
                    tab[i][j] = 0;
                }
            }
        }
        return tab;
    }

    /**
     * Cette méthode remplit la map des distances avec toutes les distances
     * @return la map des distances
     */
    public int[][] computeDijkstra() {
        Board board = (Board) this.env;
        if (board.dijkstraMemory.containsKey(this.pos)) {
            return board.dijkstraMemory.get(this.pos);
        }
        int[][] dijkstraValues = this.initTab();
        LinkedList<Position> neighbors = new LinkedList<Position>();
        neighbors.add(this.pos);
        computeDijkstra(dijkstraValues, neighbors);
        board.dijkstraMemory.put(this.pos,dijkstraValues);
        return dijkstraValues;
    }

    /**
     * Cette méthode remplit la map des distances avec toutes les distances
     * @param dijkstraValues le tableau contenant toutes les distances
     * @param neighbors les voisins
     */
    private void computeDijkstra(int[][] dijkstraValues, LinkedList<Position> neighbors) {
        int value;
        while (!neighbors.isEmpty()) {
            Position pos = neighbors.pop();
            value = dijkstraValues[pos.getRow()][pos.getCol()];
            List<Position> positions = this.env.getNeighborsList(pos);
            for (Position p : positions) {
                if (dijkstraValues[p.getRow()][p.getCol()] == 0 && this.env.getAgent(p) != this) {
                    dijkstraValues[p.getRow()][p.getCol()] = value + 1;
                    neighbors.add(p);
                }
            }
        }
    }

    /**
     * Cette méthode renvoie la liste des prédateurs évoluant dans l'environnement
     * @return la liste des prédateurs évoluant dans l'environnement
     */
    private ArrayList<Predator> getPredatorsList() {
        ArrayList<Agent> agents = this.env.getAgentsList();
        ArrayList<Predator> predators = new ArrayList<Predator>();
        for (Agent agent : agents) {
            PacmanAgent a = (PacmanAgent) agent;
            if (!a.isEatable() && !a.isWall()) {
                predators.add((Predator)a);
            }
        }
        return predators;
    }

    /**
     * Cette méthode récupère le barycentre pour chaque prédateur évoluant dans l'environnement
     * @return le barycentre pour chaque prédateur évoluant dans l'environnement
     */
    private Position getBarycenter() {
        ArrayList<Predator> predators = this.getPredatorsList();
        int x = 0;
        int y = 0;
        for (Predator predator : predators) {
            x += predator.getPos().getRow();
            y += predator.getPos().getCol();
        }
        x = x/predators.size();
        y = y/predators.size();
        return new Position(x,y);
    }

}
