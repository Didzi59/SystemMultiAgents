package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import wator.Agent;
import wator.Environment;

public class BoardPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private Environment env;

    public BoardPanel(Environment env) {
        super();
        this.env = env;
    }

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
                    g.drawImage(board[y][x].getImage(),
                    		x * squareWidth, y * squareHeight, 
                    		x * squareWidth+squareWidth, y * squareHeight+squareHeight,
                            0, 0,
                            128, 128,
                            Color.BLUE,
                            null);
                }
            }
        }
    }
    
	@Override
	public void update(final Observable o, final Object arg) {
        this.repaint();
    }
}
