package pacman.agents;

import java.awt.Color;

import core.Environment;
import core.Position;

public class Prey extends PacmanAgent {

	public Prey(Environment env, Position pos) {
		super(env, 6, pos);
	}

	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public String getName() {
		return "Prey";
	}

}
