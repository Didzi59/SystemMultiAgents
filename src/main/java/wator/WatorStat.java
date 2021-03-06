package wator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import wator.agents.Animal;
import core.Environment;
import core.Position;
import core.Statistiques;

/**
 * Cette classe permet de faire des statistiques
 * @author Julia Leven et Jérémy Bossut
 */
public class WatorStat extends Statistiques {
	
	
	// La map dans laquelle on stockera le nom de l'agent associé au nombre de cet agent pour chaque tour
	private Map<String,Integer[]> mapNbAgent;
	
	// La map dans laquelle on stockera l'âge associé au nombre de l'agent
	private Map<String,Integer[]> mapAgeAgent;
	
	/**
	 * Le constructeur permettant de réaliser des statistiques
	 * @param env l'environnement pour lequel on fait des statistiques
	 */
	public WatorStat(Environment env) {
		super(env);
		this.mapNbAgent = new HashMap<String, Integer[]>();
		this.mapAgeAgent = new HashMap<String, Integer[]>();
		// Supression des fichiers 
		this.removeFile("FileCSV/fileWatorEvolution.csv");
		this.removeFile("FileCSV/fileWatorAge.csv");
	}
	
	public Map<String,Integer[]> getNbAgent() {
		return this.mapNbAgent;
	}
	
	public void initMapNbAgentInit() {
		this.mapNbAgent.clear();
		Set<String> listNameAgent = this.getEnv().getNameNbAgent().keySet();
		for(String nameAgent : listNameAgent) {
			Integer[] values = new Integer[1];
			values[0] = 0;
			this.mapNbAgent.put(nameAgent, values);
		}
	}

	/**
	 * Cette méthode retourne la map contenant le nombre d'agent pour chaque espèce d'agent
	 * @return la map contenant le nombre d'agent pour chaque espèce d'agent
	 */
	public Map<String,Integer[]> getMapNbAgent() {
		return this.mapNbAgent;
	}

	/**
	 * Cette méthode permet de transformer les données d'une map en ligne que l'on peut ajouter à un fichier CSV et interpréter avec gnuplot
	 * @param map la map contenant les données à sauvegarder
	 * @return la ligne constituée des données à sauvegarder au format CSV
	 */
	public String transformMapToLine(Map<String,Integer[]> map) {
		String line = "";
		String key = "";
		int cpt = 0;
		//this.sortMap(map);
		Set<String> keyMap = map.keySet();
		Iterator<String> iteratorSet = keyMap.iterator();
		// Parcours de la map
		while (iteratorSet.hasNext()) {
			key = iteratorSet.next();
			// Cas de la map pour les âge 
			if (map.get(key).length > 1) {
				line+= this.getChrono()+";"+key+";";
				for (int i = 0 ; i < map.get(key).length; i++) {
					line += map.get(key)[i]+";";
				}
				line += "\n";
			// Cas de la map pour le nombre d'agents
			} else {
				if (cpt == 0 ) {
					line += this.getChrono()+";";
				}
				line += map.get(key)[0]+";";
			}
			cpt++;
		}
		return line;
	}
	
	/**
	 * Cette méthode permet d'ajouter un entête au fichier
	 * @param line l'entête à ajouter au fichier
	 * @param pathnameFolder le chemin d'accès du dossier vers le fichier qui contiendra les statistiques
	 * @param pathnameFile le chemin d'accès vers le fichier qui contiendra les statistiques
	 */
	public void printFirstLine(String line, String pathnameFolder, String pathnameFile) {
		if (!this.createFolder(pathnameFolder).exists()) {
			System.out.println("Le dossier dont le chemin d'accès est le suivant"+pathnameFolder+"n'existe pas");
			return;
		} else {
			try {
				FileWriter fw = new FileWriter(pathnameFolder+"/"+pathnameFile,true);
				this.addLineToFile(line, fw);
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
	}
	
	/**
	 * Cette méthode permet d'ajouter des entêtes aux fichiers de statistiques créés
	 */
	public void printFirstLineStats() {
		this.printFirstLine("Numéro du tour; Requin; Poisson","FileCSV","fileWatorEvolution.csv");
		this.printFirstLine("Numéro du tour; Age; Poisson; Requin","FileCSV","fileWatorAge.csv");
	}
	
	/**
	 * Cette méthode permet de créer et d'ajouter des données dans un fichier au format CSV
	 * @param pathnameFolder le chemin d'accès du dossier vers le fichier qui contiendra les statistiques
	 * @param pathnameFile le chemin d'accès vers le fichier qui contiendra les statistiques
	 * @param listNbAgent les maps contenant les données à enregistrer au format CSV
	 */
	public void createFileWithData(String pathnameFolder, String pathnameFile, ArrayList<Map<String, Integer[]>> listNbAgent) {
		if (!this.createFolder(pathnameFolder).exists()) {
			System.out.println("Le dossier dont le chemin d'accès est le suivant"+pathnameFolder+"n'existe pas");
			return;
		} else {
			try {
				FileWriter fw = new FileWriter(pathnameFolder+"/"+pathnameFile,true);
				String line = "";
				for(Map<String,Integer[]> map : listNbAgent) {
					line += this.transformMapToLine(map);
				}
				this.addLineToFile(line, fw);
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
	}
	
	/**
	 * Cette méthode permet d'incrémenter le nombre d'agent vivant pour une espèce donnée
	 * @param a un agent
	 */
	public void getNbAgentByTurn(Animal a) {
		if (a != null) {
			Integer[] value = null;
			if (this.mapNbAgent.containsKey(a.getName())) {
				value = this.mapNbAgent.get(a.getName());
				int oldNbAgent = value[0];
				value[0] = oldNbAgent+1;
			} else {
				value = new Integer[1];
				value[0] = 1;
			}
			this.mapNbAgent.put(a.getName(),value);
		}
	}
	
	/**
	 * Cette méthode permet de sauvegarder les statistiques du nombre d'agents en fonction du temps dans un fichier CSV.
	 */
	public void saveNbAgentByTurn() {
		ArrayList<Map<String, Integer[]>> listNbAgent = new ArrayList<Map<String,Integer[]>>();
		listNbAgent.add(this.mapNbAgent);
		this.createFileWithData("FileCSV","fileWatorEvolution.csv",listNbAgent);
	}
	
	/**
	 * Cette méthode permet d'incrémenter le nombre d'agent pour un âge donné
	 * @param a l'agent
	 */
	public void getAgeAgentByTurn(Animal a) {
		if (a != null) {
			String ageAgent = ""+a.getAge();
			Integer[] value = null;
			if (this.mapAgeAgent.containsKey(ageAgent)) {
				value = this.mapAgeAgent.get(ageAgent);
				int oldNbAgent = value[a.getId()];
				value[a.getId()] = oldNbAgent+1;
			} else {
				value = this.initTab(this.getEnv().getIdMaxAgent()+1);
				value[a.getId()] = 1;
			}
			this.mapAgeAgent.put(ageAgent,value);
		}
	}
	
	/**
	 * Cette méthode permet d'initialiser un tableau
	 * @param maxIdAgent
	 * @return le tableau initialisé
	 */
	public Integer[] initTab( int maxIdAgent ) {
		Integer[] values = new Integer[maxIdAgent];
		for (int i = 0; i < maxIdAgent; i++) {
			values[i] = 0;
		}
		return values;
	} 
	
	/**
	 * Cette méthode permet d'obtenir les données pour les statistiques
	 * @param a l'agent
	 */
	public void getStats(Animal a) {
		this.getNbAgentByTurn(a);
		this.getAgeAgentByTurn(a);
	}
	
	/**
	 * Cette méthode permet de sauvegarder les statistiques du nombre d'agents en fonction de l'âge dans un fichier CSV.
	 */
	public void saveAgeAgentByTurn() {
		// On récupère les statistiques tous les 10 tours
		if (this.getChrono()%10 == 0) {
			ArrayList<Map<String,Integer[]>> listNbAgent = new ArrayList<Map<String,Integer[]>>();
			listNbAgent.add(this.mapAgeAgent);
			
			this.createFileWithData("FileCSV","fileWatorAge.csv",listNbAgent);
		}
	}
	
	/**
	 * Cette méthode permet de sauvegarder les statistiques réalisées sur l'environnement dans un fichier CSV
	 */
	public void saveWithData() {
		this.saveNbAgentByTurn();
		this.saveAgeAgentByTurn();
	}

	/**
	 * Cette méthode permet de calculer les statistiques
	 */
	 public void generateStatByTurn() {
		this.initMapNbAgentInit();
		this.mapAgeAgent.clear();
		for (int i = 0; i < this.getEnv().getNbRows(); i++) {
			for (int j = 0; j< this.getEnv().getNbCols(); j++) {
				Animal a = (Animal) this.getEnv().getAgent(new Position(i,j));
				this.getStats(a);
			}
		}
		this.saveWithData();
		super.generateStatByTurn();
 	} 
}