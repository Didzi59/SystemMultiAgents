package schelling;

import java.io.FileWriter;
import java.io.IOException;
import schelling.agents.Human;
import core.Environment;
import core.Position;
import core.Statistiques;

/**
 * Cette classe permet de réaliser des statistiques sur le modèle de ségrégation de Schelling
 * @author Leven Julia et Bossut Jérémy
 */
public class SchellingStat extends Statistiques {
	
	/**
	 * Le constructeur permettant de réaliser des statistiques sur le modèle de Schelling
	 * @param env l'environnement
	 */
	public SchellingStat(Environment env) {
		super(env);
		this.removeFile("FileCSV/similarity.csv");
	}
	
	/**
	 * Cette méthode récupère la somme des similarités de tous les agents évoluants dans l'environnement
	 * @return la somme des similarités de tous les agents évoluants dans l'environnement
	 */
	public int getSumSimilarity() {
		int sumSimilarity = 0;
		for (int i = 0; i < this.getEnv().getNbRows(); i++) {
			for (int j = 0; j< this.getEnv().getNbCols(); j++) {
				Human h = (Human) this.getEnv().getAgent(new Position(i,j));
				if (h != null) {
					sumSimilarity += h.getSimilarity();
				}
			}
		}
		return sumSimilarity;
	}
	
	/**
	 * Cette méthode récupère la statisfaction moyenne des agents
	 * @return la statisfaction moyenne des agents
	 */
	public int getSatisfaction() {
		return this.getSumSimilarity()/this.getEnv().getAllNbAgent();
	}
	
	/**
	 * Cette méthode permet de générer les statistiques pour l'environnement District
	 */
	public void generateStatByTurn() {		
		super.generateStatByTurn();
		if (!this.createFolder("FileCSV").exists()) {
			System.out.println("Le dossier dont le chemin d'accès est le suivant"+"FileCSV"+"n'existe pas");
			return;
		} else {
			try {
				FileWriter fw = new FileWriter("FileCSV/similarity.csv",true);
				this.addLineToFile(this.getChrono()+";"+this.getSatisfaction(), fw);
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
	}

}