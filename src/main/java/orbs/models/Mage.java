package orbs.models;

import orbs.models.enums.Orb;
import java.awt.geom.Point2D;

// It's a player, bean stores his position, health, mana, currently casted orbs
public class Mage {
    private Point2D.Double coordinates;
    private Orb[] orbs;
    private String name;

    public Mage(double startX, double startY) {
        this.coordinates.setLocation(startX, startY);
        this.orbs = new Orb[3];
    }

    public void move(double x, double y) {
        this.coordinates.setLocation(x, y);
    }
    public Spell cast() {
        return new Spell(orbs);
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
