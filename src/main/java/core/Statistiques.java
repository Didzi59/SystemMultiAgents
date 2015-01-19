package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Statistiques {
	
	// L'environnement
	private Environment env;
	
	// Le numéro du tour
	private int chrono;
	
	/**
	 * Le constructeur permettant de faire des statistiques
	 * @param env l'environnement
	 */
	public Statistiques(Environment env) {
		this.env = env;
		this.chrono = 0;
	}
	
	/**
	 * Cette méthode retourne l'environnement
	 * @return l'environnement
	 */
	public Environment getEnv() {
		return this.env;
	}
	
	/**
	 * Cette méthode récupère le numéro du tour
	 * @return le numéro du tour
	 */
	public int getChrono() {
		return this.chrono;
	}
	
	/**
	 * Cette méthode incrémente le numéro du tour
	 */
	public void setChrono() {
		this.chrono++;
	}

	
	/**
	 * Cette méthode permet de créer un nouveau dossier
	 * @param pathnameFolder le chemin d'accès vers ce nouveau dossier
	 * @return le nouveau dossier
	 */
	public File createFolder(String pathnameFolder) {
		File folder = new File(pathnameFolder);
		folder.mkdir();
		return folder;
	}
	
	/**
	 * Cette méthode permet de supprimer un fichier si il existe déjà
	 * @param pathnameFile le chemin d'accès vers le fichier à supprimer
	 */
	public void removeFile(String pathnameFile) {
		File fileToRemove = new File(pathnameFile);
		if (fileToRemove.exists())
			fileToRemove.delete();
	}
	
	/**
	 * Cette méthode permet d'écrire une nouvelle ligne dans le fichier représenté par le FileWriter fw
	 * @param line la nouvelle ligne à ajouter
	 * @param fw le fichier
	 */
	public void addLineToFile(String line, FileWriter fw) {
		try {
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(line+"\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	 public void generateStatByTurn() {
		 this.setChrono();
	 }
	
}