package core;

import core.view.StatusPanel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Cette classe représente l'environnement dans lequel évolue l'agent
 * @author Julia Leven et Jérémy Bossut
 */
public class Environment {
	
	// Le nombre de lignes constituant l'environnement
	private int nbRows;
	
	// Le nombre de colonnes constituant l'environnement
	private int nbCols;
	
	// La map représentant l'environnement
	private Agent[][] map;
	
	// Le plus grand identifiant d'un agent que l'on a rencontré dans l'environnement
	private int idMaxAgent;

	// La map dans laquelle est stockée le nombre d'agents avec leurs noms
	protected Map<String,Integer> nbAgentsByName;
	
	/**
	 * Le constructeur représentant l'environnement 
	 * @param nbRows le nombre de lignes constituant l'environnement
	 * @param nbCols le nombre de colonnes constituant l'environnement
	 */
	public Environment(int nbRows, int nbCols) {
		this.nbRows = nbRows;
		this.nbCols = nbCols;
		// Initialisation de la map
		this.map = new Agent[this.nbRows][this.nbCols];
		for(int i = 0; i < this.nbRows; i++) {
			for(int j = 0; j < this.nbCols; j++) {
				this.map[i][j] = null;
			}
		}
		// Initialisation de l'identifiant maximal rencontré
		this.idMaxAgent = 0;
		this.nbAgentsByName = new HashMap<String,Integer>();
	}
	
	/**
	 * Cette méthode retourne le nombre de lignes 
	 * @return le nombre de lignes
	 */
	public int getNbRows() {
		return this.nbRows;
	}
	
	/**
	 * Cette méthode retourne le nombre de colonnes
	 * @return le nombre de colonnes
	 */
	public int getNbCols() {
		return this.nbCols;
	}
	
	/**
	 * Cette méthode retourne la map représentant l'environnement
	 * @return la map représentant l'environnement
	 */
	public Agent[][] getMap() {
		return map;
	}
	
	/**
	 * Cette méthode permet de récupérer l'identifiant maximum. On pourra ainsi connaitre l'agent rencontré qui a le plus grand identifiant
	 * @return l'identifiant maximal
	 */
	public int getIdMaxAgent() {
		return this.idMaxAgent;
	}
	
	/**
	 * Cette méthode permet de modifier l'identifiant maximum. On pourra ainsi connaitre l'agent rencontré qui a le plus grand identifiant
	 * @param idMaxAgent le nouvel identifiant maximal
	 */
	public void setIdMaxAgent(int idMaxAgent) {
		this.idMaxAgent = idMaxAgent;
	}
	
	/**
     * Cette méthode permet de récupérer la map contenant le nombre d'espèces en fonction du nom
     * @return la map contenant le nombre d'espèces en fonction du nom
     */
    public Map<String,Integer> getNameNbAgent() {
    	return this.nbAgentsByName;
    }
    
    /**
     * Cette méthode permet de récupérer le nombre d'agent appartenant à une espèce
     * @param name le nombre de l'espèce
     * @return le nombre d'agent appartenant à une espèce
     */
    public int getNbAgent(String name) {
    	return this.nbAgentsByName.get(name);
    }
    
    /**
     * Cette méthode retourne le nombre d'agents évoluants dans l'environnement
     * @return le nombre d'agents évoluants dans l'environnement
     */
    public int getAllNbAgent() {
    	int cpt = 0;
    	Set<String> listNameAgent = this.getNameNbAgent().keySet();
		for(String nameAgent : listNameAgent) {
			cpt += this.getNbAgent(nameAgent);
		}
		return cpt;
    }
    
    
    /**
     * Cette méthode permet de récupérer la liste des agents évoluant dans l'environnement
     * @return la liste des agents évoluant dans l'environnement
     */
    public ArrayList<Agent> getAgentsList() {
        ArrayList<Agent> agents = new ArrayList<Agent>();      
        for (int i = 0 ; i < this.nbRows ; i++) {
            for (int j = 0 ; j < this.nbCols ; j++) {
            	Agent a = this.map[i][j]; 
            	if (a != null) {
            		agents.add(a);
            	}
            }
        }
        Collections.shuffle(agents);
        return agents;
    }
    
	
	/**
	 * Cette méthode permet de savoir si la position passée en paramètre est valide
	 * @param pos la position pour laquelle on souhaite savoir si elle est valide
	 * @return true si la position est valide, false sinon
	 */
	public boolean isValidPosition(Position pos) {
		int i = pos.getRow();
		int j = pos.getCol();
		return i < this.nbRows && i >= 0 && j < this.nbCols && j >= 0 ;
	}
	
	/**
	 * Cette méthode permet d'ajouter une position à la liste des positions voisines pour la position passée en paramètre
	 * @param voisins la liste des positions voisines de la position passée en paramètre
	 * @param pos la position
	 */
	public void addValidPosition(ArrayList<Position> voisins, Position pos) {
        if (this.isValidPosition(pos)) {
        	voisins.add(pos);
        }
	}
	
    
    /**
     * Cette méthode permet de récupérer les positions voisines de la position passée en paramètre, sur laquelle il y a un agent mangeable
     * @param pos la position
     * @return une liste contenant les positions voisines de la position passée en paramètre, sur laquelle il y a un agent mangeable
     */
    public ArrayList<Position> getNeighborsList(Position pos) {
        ArrayList<Position> neighbors = new ArrayList<Position>();

        this.addValidPosition(neighbors, pos.left());
        this.addValidPosition(neighbors, pos.right());
        this.addValidPosition(neighbors, pos.up());
        this.addValidPosition(neighbors, pos.down());
        this.addValidPosition(neighbors, pos.leftup());
        this.addValidPosition(neighbors, pos.rightup());
        this.addValidPosition(neighbors, pos.leftdown());
        this.addValidPosition(neighbors, pos.rightdown());

        return neighbors;
    }

	/**
	 * Cette méthode permet d'ajouter une position libre à la liste des positions voisines et libres pour la position passée en paramètre
	 * @param voisins la liste des positions voisines et libres de la position passée en paramètre
	 * @param pos la position
	 */
	public void addFreeValidPosition(ArrayList<Position> voisins, Position pos) {
        if (this.isValidPosition(pos) && this.map[pos.getRow()][pos.getCol()] == null) {
        	voisins.add(pos);
        }
	}
	
    
    /**
     * Cette méthode permet de récupérer les positions voisines et libres de la position passée en paramètre, sur laquelle il y a un agent mangeable
     * @param pos la position
     * @return une liste contenant les positions voisines et libres de la position passée en paramètre, sur laquelle il y a un agent mangeable
     */
    public ArrayList<Position> getFreeNeighborsList(Position pos) {
        ArrayList<Position> freeNeighbors = new ArrayList<Position>();

        this.addFreeValidPosition(freeNeighbors, pos.left());
        this.addFreeValidPosition(freeNeighbors, pos.right());
        this.addFreeValidPosition(freeNeighbors, pos.up());
        this.addFreeValidPosition(freeNeighbors, pos.down());
        this.addFreeValidPosition(freeNeighbors, pos.leftup());
        this.addFreeValidPosition(freeNeighbors, pos.rightup());
        this.addFreeValidPosition(freeNeighbors, pos.leftdown());
        this.addFreeValidPosition(freeNeighbors, pos.rightdown());

        return freeNeighbors;
    }

    /**
     * Cette méthode permet de récupérer aléatoirement une position libre
     * @return une position libre
     */
    public Position getRandomFreePosition() {
        ArrayList<Position> freePositions = new ArrayList<Position>();
        // On récupère toutes les positions libress
        for (int i = 0 ; i < this.nbRows ; i++) {
            for (int j = 0 ; j < this.nbCols ; j++) {
            	this.addFreeValidPosition(freePositions, new Position(i,j));
            }
        }
        // On mélange la liste
		if (!freePositions.isEmpty()) {
	        Collections.shuffle(freePositions);
	        // On retourne le premier élément
	        return freePositions.get(0);
		} 
		return null;
    }
    
    /**
   	 * Cette méthode permet d'ajouter un agent à l'environnement
   	 * @param agent l'agent qu'on ajoute à l'environnement
   	 */
   	public void addAgent(Agent agent) {
        Position pos = agent.getPos();
        int oldNbAgent = 0;
        if (this.nbAgentsByName.containsKey(agent.getName()))
            oldNbAgent = this.nbAgentsByName.get(agent.getName());
        this.map[pos.getRow()][pos.getCol()] = agent;
        this.nbAgentsByName.put(agent.getName(), oldNbAgent + 1);
    }
    
    /**
     * Cette méthode permet de récupérer l'agent à la position passée en paramètre
     * @param pos la position
     * @return l'agent qui se situe sur la position passée en paramètre
     */
	public Agent getAgent(Position pos) {
		if (this.isValidPosition(pos)) {
			return this.map[pos.getRow()][pos.getCol()];
		}
		return null;
	}

	/**
	 * Cette méthode permet de supprimer un agent de l'environnement
	 * @param pos la position sur laquelle se situe l'agent à supprimer
	 */
    public void removeAgent(Position pos) {
    	String agentName =  this.map[pos.getRow()][pos.getCol()].getName();
        int oldNbAgent = this.nbAgentsByName.get(agentName);
        this.map[pos.getRow()][pos.getCol()] = null;
        this.nbAgentsByName.put(agentName, oldNbAgent - 1);
    }

    /**
     * Cette méthode permet à l'agent de se déplacer
     * @param pos la position actuelle de l'agent
     * @param newPos la position où l'agent se déplace
     */
	public void moveAgent(Position pos, Position newPos) {
		Agent a = this.map[pos.getRow()][pos.getCol()];
		this.map[newPos.getRow()][newPos.getCol()] = a;
		this.map[pos.getRow()][pos.getCol()] = null;
	}

    public boolean isTerminated() {
        return false;
    }
    
    /**
	 * Cette méthode permet d'ajouter des entêtes aux fichiers de statistiques créés
	 */
    public void printFirstLineStats() {
    	//this.printFirstLineStats();
    }
    
    /**
	 * Cette méthode permet de générer les statistiques
	 */
    public void generateStatByTurn() {
    	this.generateStatByTurn();
    }

	public void updateStatusPanel(StatusPanel statusPanel) {}

    public void addStatusPanelLabels(StatusPanel statusPanel) {}
    
    public Color getDefaultColor() {
    	return Color.BLUE;
    }
}