package schelling.agents;

import core.Environment;
import core.Position;

import java.awt.*;

/**
 * Cette classe représente un humain rouge
 * @author Leven Julia et Bossut Jéremy
 */
public class RedHuman extends Human {

    /**
     * Le constructeur représentant un humain rouge
     * @param env l'environnement dans lequel évolue l'humain
     * @param pos la position de l'humain dans l'environnement
     * @param tolerance la tolerance de l'humain aux autres
     */
    public RedHuman(Environment env, Position pos, int tolerance) {
        super(env, pos, tolerance);
    }

    public String getName() {
        return "RedHuman";
    }

    public Color getColor() {
        return Color.RED;
    }
    
}
