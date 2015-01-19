package schelling.agents;

import core.Environment;
import core.Position;

import java.awt.*;

/**
 * Cette classe représente un humain vert
 * @author Leven Julia et Bossut Jéremy
 */
public class GreenHuman extends Human {

    /**
     * Le constructeur représentant un humain vert
     * @param env l'environnement dans lequel évolue l'humain
     * @param pos la position de l'humain dans l'environnement
     * @param tolerance la tolerance de l'humain aux autres
     */
    public GreenHuman(Environment env, Position pos, int tolerance) {
        super(env, pos, tolerance);
    }

    public String getName() {
        return "GreenHuman";
    }

    public Color getColor() {
        return Color.GREEN;
    }
}