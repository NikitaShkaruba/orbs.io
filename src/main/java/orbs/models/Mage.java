package orbs.models;

import orbs.models.enums.OrbEnum;
import java.awt.geom.Point2D;
import java.util.ArrayList;

// It's a player, bean stores his position, health, mana, currently casted orbs
public class Mage {
    private Point2D.Double coordinates;
    private String name;
    public OrbsCollection orbs;
    private ArrayList<Spell> castedSpells;

    public Mage(String name, Point2D.Double coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.orbs = new OrbsCollection();
        this.castedSpells = new ArrayList<Spell>();
    }

    public void move(double x, double y) {
        this.coordinates.setLocation(x, y);
    }

    public Spell cast() {
        Spell casted = new Spell(this.orbs.pullOrbs());
        this.castedSpells.add(casted);
        return casted;
    }

    public Point2D.Double getCoordinates() {
        return this.coordinates;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
