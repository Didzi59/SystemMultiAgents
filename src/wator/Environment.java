package wator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe représente l'environnement dans lequel évolue l'agent
 * @author Julia Leven et Jérémy Bossut
 */
public class Environment {
	
	// La map représentant l'environnement
	private Agent[][] map;
	
	// Le plus grand identifiant d'un agent que l'on a rencontré dans l'environnement
	private int idMaxAgent;
	
	// L'objet permettant de réaliser des statistiques sur l'environnement
	private WatorStat watorStat;

	private Set<String> setNames;
	
	// Le nombre de lignes
	public static final int NB_ROWS = 40;
	
	// Le nombre de colonnes
	public static final int NB_COLS = 40;
	
	/**
	 * Le constructeur représentant l'environnement 
	 */
	public Environment() {
		// Initialisation de la map
		this.map = new Agent[NB_ROWS][NB_COLS];
		for(int i = 0; i < NB_ROWS; i++) {
			for(int j = 0; j < NB_COLS; j++) {
				this.map[i][j] = null;
			}
		}
		// Initialisation de l'identifiant maximal rencontré
		this.idMaxAgent = 0;
		// Initialisation de la classe permettant de réaliser des statistiques et de l'environnement
		this.watorStat = new WatorStat(this);
		this.setNames = new HashSet<String>();
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
	
	public int getNbFish() {
		ArrayList<Agent> lag = this.getAgentsList();
		int cpt = 0;
		for (Agent a : lag) {
			if (a.isEatable()) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public int getNbShark() {
		ArrayList<Agent> lag = this.getAgentsList();
		int cpt = 0;
		for (Agent a : lag) {
			if (!a.isEatable()) {
				cpt++;
			}
		}
		return cpt;
	}
	
	/**
	 * Cette méthode retourne le nombre de lignes 
	 * @return le nombre de lignes
	 */
	public int getNbRows() {
		return NB_ROWS;
	}
	
	/**
	 * Cette méthode retourne le nombre de colonnes
	 * @return le nombre de colonnes
	 */
	public int getNbCols() {
		return NB_COLS;
	}
	
	/**
	 * Cette méthode permet de savoir si la position passée en paramètre est valide
	 * @param pos la position pour laquelle on souhaite savoir si elle est valide
	 * @return true si la position est valide, false sinon
	 */
	public boolean isValidPosition(Position pos) {
		int i = pos.getRow();
		int j = pos.getCol();
		return i < NB_ROWS && i >= 0 && j < NB_COLS && j >= 0 ;
	}
	
	/**
	 * Cette méthode permet d'ajouter une position libre à la liste des positions voisines et libres pour la position passée en paramètre
	 * @param voisins la liste des positions voisines et libres de la position passée en paramètre
	 * @param pos la position
	 * @return la liste des positions voisines et libres pour la position passée en paramètre
	 */
	public ArrayList<Position> addFreeValidPosition(ArrayList<Position> voisins, Position pos) {
        if (this.isValidPosition(pos) && this.map[pos.getRow()][pos.getCol()] == null) {
        	voisins.add(pos);
        }
        return voisins;
	}

	/**
	 * Cette méthode permet d'ajouter une position sur laquelle il y a agent mangeable par l'agent situé à la position passée en paramètre
	 * @param voisins la liste des positions voisines de la position passée en paramètre
	 * @param pos la position
	 * @return la liste des positions voisines de la position passée en paramètre, sur laquelle il y a un agent mangeable
	 */
    public ArrayList<Position> addEatableValidPosition(ArrayList<Position> voisins, Position pos) {
        Agent a = this.getAgent(pos);
        if (this.isValidPosition(pos) && a != null && a.isEatable()) {
            voisins.add(pos);
        }
        return voisins;
    }
	
    /**
     * Cette méthode permet de récupérer les positions voisines et libres de la position passée en paramètre
     * @param pos la position
     * @return une liste contenant les positions voisines et libres de la position passée en paramètre
     */
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

    /**
     * Cette méthode permet de récupérer les positions voisines et libres de la position passée en paramètre, sur laquelle il y a un agent mangeable
     * @param pos la position
     * @return une liste contenant les positions voisines et libres de la position passée en paramètre, sur laquelle il y a un agent mangeable
     */
    public ArrayList<Position> getEatableNeighborsList(Position pos) {
        ArrayList<Position> eatableNeighbors = new ArrayList<Position>();

        eatableNeighbors = this.addEatableValidPosition(eatableNeighbors, pos.left());
        eatableNeighbors = this.addEatableValidPosition(eatableNeighbors, pos.right());
        eatableNeighbors = this.addEatableValidPosition(eatableNeighbors, pos.up());
        eatableNeighbors = this.addEatableValidPosition(eatableNeighbors, pos.down());
        eatableNeighbors = this.addEatableValidPosition(eatableNeighbors, pos.leftup());
        eatableNeighbors = this.addEatableValidPosition(eatableNeighbors, pos.rightup());
        eatableNeighbors = this.addEatableValidPosition(eatableNeighbors, pos.leftdown());
        eatableNeighbors = this.addEatableValidPosition(eatableNeighbors, pos.rightdown());

        return eatableNeighbors;
    }
    
    /**
     * Cette méthode permet de récupérer la liste des agents évoluant dans l'environnement
     * @return la liste des agents évoluant dans l'environnement
     */
    public ArrayList<Agent> getAgentsList() {
        ArrayList<Agent> agents = new ArrayList<Agent>();      
        for (int i = 0 ; i < NB_ROWS ; i++) {
            for (int j = 0 ; j < NB_COLS ; j++) {
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
     * Cette méthode permet de récupérer le nom de toutes les espèces existantes
     * @return un ensemble contenant le nom de toutes les espèces existantes
     */
    public Set<String> getSetNames() {
    	return this.setNames;
    }
    
    
    /**
     * Cette méthode permet de récupérer aléatoirement, une position voisine à la position passée en paramètre, qui est libre 
     * @param pos la position
     * @return une position voisine et libre
     */
    public Position getRandomFreeNeighborPosition(Position pos) {
        ArrayList<Position> freeNeighbors = this.getFreeNeighborsList(pos);
		if (!freeNeighbors.isEmpty()) {
	        Collections.shuffle(freeNeighbors);
	        return freeNeighbors.get(0);
		}
		return null;
    }

    /**
     * Cette méthode permet de récupérer aléatoirement, une position voisine à la position passée en paramètre, sur laquelle il y a un agent mangeable
     * @param pos la position
     * @return une position voisine sur laquelle il y a un agent mangeable
     */
    public Position getRandomEatableNeighborPosition(Position pos) {
        ArrayList<Position> eatableNeighbors = this.getEatableNeighborsList(pos);
        if (!eatableNeighbors.isEmpty()) {
            Collections.shuffle(eatableNeighbors);
            return eatableNeighbors.get(0);
        }
        return null;
    }

    /**
     * Cette méthode permet de récupérer aléatoirement une position libre
     * @return une position libre
     */
    public Position getRandomFreePosition() {
        ArrayList<Position> freePositions = new ArrayList<Position>();
        // On récupère toutes les positions libress
        for (int i = 0 ; i < NB_ROWS ; i++) {
            for (int j = 0 ; j < NB_COLS ; j++) {
            	freePositions = this.addFreeValidPosition(freePositions, new Position(i,j));
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
     * Cette méthode permet à l'agent de se déplacer
     * @param pos la position actuelle de l'agent
     * @param newPos la position où l'agent se déplace
     */
	public void moveAgent(Position pos, Position newPos) {
		Agent a = this.map[pos.getRow()][pos.getCol()];
		this.map[newPos.getRow()][newPos.getCol()] = a;
		this.map[pos.getRow()][pos.getCol()] = null;
		
		a.setEnv(this);
	}
	
	/**
	 * Cette méthode permet d'ajouter un agent à l'environnement
	 * @param agent l'agent qu'on ajoute à l'environnement
	 */
	public void addAgent(Agent agent) {
        Position pos = agent.getPos();
        this.map[pos.getRow()][pos.getCol()] = agent;
        this.setNames.add(agent.getName());
    }

	/**
	 * Cette méthode permet de supprimer un agent de l'environnement
	 * @param pos la position sur laquelle se situe l'agent à supprimer
	 */
    public void removeAgent(Position pos) {
        this.map[pos.getRow()][pos.getCol()] = null;
    }

    
    /**
	 * Cette méthode permet d'ajouter des entêtes aux fichiers de statistiques créés
	 */
    public void printFirstLineStats() {
    	this.watorStat.printFirstLineStats();
    }
    
    /**
     * Cette méthode permet de générer les statistiques
     */
    public void generateStatByTurn() {
    	this.watorStat.generateStatByTurn();
    }
    
    /**
     * Cette méthode permet d'afficher un agent sur la vue
     */
    public String toString() {
    	String s = "";
        for (int i = 0 ; i < NB_ROWS ; i++) {
            for (int j = 0 ; j < NB_COLS ; j++) {
                Agent a = this.map[i][j];
                if (a != null) {
                	s += a.toString();
                } else {
                	s += " ";
                }
            }
            s += "\n";
        }
        return s;
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
}