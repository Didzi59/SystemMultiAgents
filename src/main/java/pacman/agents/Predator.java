package pacman.agents;

import java.awt.Color;

import core.Environment;
import core.Position;

public class Predator extends PacmanAgent {

	public Predator(Environment env, Position pos) {
		super(env, 5, pos);
	}

	public Color getColor() {
		return Color.YELLOW;
	}

	@Override
	public String getName() {
		return "Predator";
	}

}
