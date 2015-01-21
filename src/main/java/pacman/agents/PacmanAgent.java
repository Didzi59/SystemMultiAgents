package pacman.agents;

import java.awt.Graphics;

import core.Agent;
import core.Environment;
import core.Position;

public abstract class PacmanAgent extends Agent {

	public PacmanAgent(Environment env, int idAgent, Position pos) {
		super(env, idAgent, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doIt() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void representationAgent(Graphics g, int x, int y, int squareWidth, int squareHeight) {
		g.setColor(this.getColor());
		g.fillOval(x * squareWidth, y * squareHeight, squareWidth, squareHeight);
	}

}
