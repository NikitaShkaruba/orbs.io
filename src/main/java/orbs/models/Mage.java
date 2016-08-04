package orbs.models;

import orbs.models.enums.Orb;
import java.awt.geom.Point2D;

// It's a player, bean stores his position, health, mana, currently casted orbs
public class Mage {
    private Point2D.Double coordinates;
    private Orb[] orbs;
    private String name;

    public Mage(String name, Point2D.Double coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.orbs = new Orb[3];
    }

    public void move(double x, double y) {
        this.coordinates.setLocation(x, y);
    }
    public Spell cast() {
        return new Spell(orbs);
    }
    public String toJson() {
        // Todo: refactor this shit omg, i can't read
        char quotes = '"';
        char tab = '\t';
        char newLine = '\n';

        return quotes + name + quotes + " {" + newLine +
                tab + quotes + "x" + quotes +": " + coordinates.getX() + newLine +
                tab + quotes + "y" + quotes +": " + coordinates.getX() + newLine +
                "}";
    }

    public Point2D.Double getCoordinates() {
        return this.coordinates;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
