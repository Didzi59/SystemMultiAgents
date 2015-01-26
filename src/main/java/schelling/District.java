package schelling;

import java.util.ArrayList;

import javax.swing.JLabel;

import core.Agent;
import core.Environment;
import core.Position;
import core.view.StatusPanel;
import schelling.agents.GreenHuman;
import schelling.agents.Human;
import schelling.agents.RedHuman;

/**
 * Cette classe représente un quartier
 * @author Leven Julia et Bossut Jérémy
 */
public class District extends Environment {
	
	// L'objet permettant de réaliser des statistiques sur l'environnement
	private SchellingStat schellingStat;

	/**
	 * Le constructeur représentant un environnement
	 * @param nbRows le nombre de lignes constituant l'environnement
	 * @param nbCols le nombre de colonnes constituant l'environnement
	 */
	public District(int nbRows, int nbCols) {
		super(nbRows, nbCols);
		this.schellingStat = new SchellingStat(this);
	}
	
	/**
	 * Cette méthode permet d'initialiser l'environnement
	 * @param nbGreenHumans le nombre d'humains verts dans l'environnement
     * @param nbRedHumans le nombre d'humains rouges dans l'environnement
	 * @param tolerance la tolerance des humains
	 */
	public void init(int nbGreenHumans, int nbRedHumans, int tolerance) {
		for (int i=0; i < nbGreenHumans; i++) {
			Position pos = this.getRandomFreePosition();
			new GreenHuman(this, pos, tolerance);
		}
        for (int i=0; i < nbRedHumans; i++) {
            Position pos = this.getRandomFreePosition();
            new RedHuman(this, pos, tolerance);
        }
	}
	
	/**
	 * @param voisins la liste des voisins qui satisfaient un humain à la position pos passée en paramètre
	 * @param pos la position de l'humain
	 * @param posNeighbor la position du voisin de l'humain
	 */
	public void addSameTypeHumanValidPosition(ArrayList<Position> voisins, Position pos, Position posNeighbor) {
		Human actualHuman = (Human) this.getAgent(pos);
		if (this.isValidPosition(posNeighbor)) {
            Human humanNeighbor = (Human) this.getAgent(posNeighbor);
            if (humanNeighbor != null && humanNeighbor.isSameType(actualHuman))
			    voisins.add(posNeighbor);
		}
	}
	
	/**
	 * Cette méthode récupère la liste des voisins qui satisfont un humain à la position passée en paramètre
	 * @param pos la position de l'humain
	 * @return la liste des voisins qui satisfont un humain à la position passée en paramètre
	 */
	public int getNbSameTypeNeighbors(Position pos) {
		 ArrayList<Position> neighbors = new ArrayList<Position>();

		 this.addSameTypeHumanValidPosition(neighbors, pos, pos.left());
		 this.addSameTypeHumanValidPosition(neighbors, pos, pos.right());
		 this.addSameTypeHumanValidPosition(neighbors, pos, pos.up());
		 this.addSameTypeHumanValidPosition(neighbors, pos, pos.down());
		 this.addSameTypeHumanValidPosition(neighbors, pos, pos.leftup());
		 this.addSameTypeHumanValidPosition(neighbors, pos, pos.rightup());
		 this.addSameTypeHumanValidPosition(neighbors, pos, pos.leftdown());
		 this.addSameTypeHumanValidPosition(neighbors, pos, pos.rightdown());

		 return neighbors.size();
	}

    /**
     * Cette méthode permet de récupérer le nombre de positions voisines
     * @param pos la position
     * @return le nombre de positions voisines
     */
    public int getNbNeighbors(Position pos) {
        int cpt = 0;
        if (this.isValidPosition(pos.left()) && this.getAgent(pos.left()) != null) cpt++;
        if (this.isValidPosition(pos.right()) && this.getAgent(pos.right()) != null) cpt++;
        if (this.isValidPosition(pos.up()) && this.getAgent(pos.up()) != null) cpt++;
        if (this.isValidPosition(pos.down()) && this.getAgent(pos.down()) != null) cpt++;
        if (this.isValidPosition(pos.leftup()) && this.getAgent(pos.leftup()) != null) cpt++;
        if (this.isValidPosition(pos.rightup()) && this.getAgent(pos.rightup()) != null) cpt++;
        if (this.isValidPosition(pos.leftdown()) && this.getAgent(pos.leftdown()) != null) cpt++;
        if (this.isValidPosition(pos.rightdown()) && this.getAgent(pos.rightdown()) != null) cpt++;
        return cpt;
    }

    /**
	 * Cette méthode permet de savoir si le jeu doit se terminer
	 * @return true si le jeu doit se terminer, false sinon
	 */
    public boolean isTerminated() {
        ArrayList<Agent> agents = this.getAgentsList();
        boolean res = true;
        for (Agent a : agents) {
            Human h = (Human) a;
            res = res && h.isSatisfied();
        }
        return res;
    }
	
	/**
	 * Cette méthode permet d'ajouter des entêtes aux fichiers de statistiques créés
	 */
	public void printFirstLineStats() {
		return;
	}
	
	/**
	 * Cette méthode permet de générer les statistiques
	 */
	public void generateStatByTurn() {
		this.schellingStat.generateStatByTurn();
	}	

	
	public void updateStatusPanel(StatusPanel statusPanel) {
		statusPanel.getExtraLabel().setText(String.valueOf(this.schellingStat.getSatisfaction()));
	}

    public void addStatusPanelLabels(StatusPanel statusPanel) {
        statusPanel.setExtraLabel(new JLabel(String.valueOf("0")));
        statusPanel.add(new JLabel("Satisfaction"));
        statusPanel.add(statusPanel.getExtraLabel());
    }
	
}
