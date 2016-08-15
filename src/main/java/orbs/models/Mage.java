package orbs.models;

import orbs.models.enums.OrbType;

import java.awt.geom.Point2D;

// It's a player, bean stores his position, health, mana, currently casted orbs
public class Mage {
    private Point2D.Double coordinates;
    private transient OrbType[] orbs;
    private transient String name;

    public Mage(String name, Point2D.Double coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        orbs = new OrbType[3];
    }

    public void move(double x, double y) {
        coordinates.setLocation(x, y);
    }
    public Spell cast() {
        return new Spell(orbs);
    }

    public Point2D.Double getCoordinates() {
        return coordinates;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
