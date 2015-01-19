package core.view;

import core.Agent;
import core.Environment;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class BoardPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	
	// L'environnement
	private Environment env;
	
	/**
	 * Le constructeur représentant visuellement la map correspondant à l'environnement
	 * @param env l'environnement
	 */
    public BoardPanel(Environment env) {
        super();
        this.env = env;
    }

    /**
     * Cette méthode dessine les composants de l'environnement
     */
    @Override
    protected void paintComponent(Graphics g) {
   		 super.paintComponent(g);
   		 Agent[][] board = this.env.getMap();
   		 int squareHeight = getHeight() / board.length;
   		 int squareWidth = getWidth() / board[0].length;
   		 
   		 for (int y = 0; y < board.length; y++) {
   			 for (int x = 0; x < board[y].length; x++) {
   				 if (board[y][x] == null) {
   					 g.setColor(Color.BLUE);
   					 g.fillRect(x * squareWidth, y * squareHeight, squareWidth, squareHeight);
   				 } else {
   					 board[y][x].representationAgent(g,this,x,y);
   				 }
   			 }
   		 }
    }
    
    /**
     * Cette méthode met à jour la vue de l'environnement
     */
	public void update(final Observable o, final Object arg) {
        this.repaint();
    }
}
