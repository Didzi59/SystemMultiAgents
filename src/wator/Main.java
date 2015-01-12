package wator;

/**
 * La classe permettant de lancer le programme
 * @author Julia Leven et Jérémy Bossut
 *
 */
public class Main {

	public static void main(String[] args){
		if (args.length < 1) {
			System.out.println("Veuillez indiquer le nombre de tours à effectuer. Pour cela tapez la commande suivante : java -jar wator_BOSSUT_LEVEN.jar nbTours");
		} else {
			int nbTurn = Integer.parseInt(args[0]);
			new SMA(nbTurn).run();
		}
	}

}
