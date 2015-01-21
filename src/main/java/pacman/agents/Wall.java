package pacman.agents;

import java.awt.Color;
import java.awt.Graphics;

import core.Environment;
import core.Position;


public class Wall extends PacmanAgent {
	
	public Wall(Environment env, Position pos) {
		super(env, 4, pos);
	}
	
	public String getName() {
		return "Wall";
	}
	
    public Color getColor() {
        return Color.BLACK;
    }
    
	public void representationAgent(Graphics g, int x, int y, int squareWidth, int squareHeight) {
		g.setColor(this.getColor());
		g.fillRect(x * squareWidth, y * squareHeight, squareWidth, squareHeight);
	}

}
