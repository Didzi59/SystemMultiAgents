package core;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * Cette classe contient toutes les méthodes permettant de parser une ligne de commande
 * @author Julia Leven et Jérémy Bossut
 */
public abstract class Main {
	
	// La liste des arguments passés en ligne de commande
	@Argument
	private static List<String> arguments = new ArrayList<String>();
	
	/**
	 * Cette méthode récupère la liste des arguments passés en ligne de commande
	 * @return la liste des arguments passés en ligne de commande
	 */
	public static List<String> getListArguments() {
		return arguments;
	}
	
	/**
	 * Cette méthode parse la ligne de commande
	 * @param args les arguments passés en ligne de commande
	 */
	public void doMain(String[] args) {
		CmdLineParser parser = new CmdLineParser(this);
		try {
			parser.parseArgument(args);
			if( arguments.isEmpty() )
				throw new CmdLineException(parser,"No argument is given");
		} catch (CmdLineException e) {
			System.err.println(e.getMessage() + "\n");
			parser.printUsage(System.out);
			System.exit(0);
		}
	}
	 
}
