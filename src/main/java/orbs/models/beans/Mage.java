package orbs.models.beans;

import java.awt.geom.Point2D;
import java.util.Random;

// It's a player, bean stores his position, health, mana, currently casted orbs
public class Mage {
    private Point2D.Double coordinates;
    private Spell spell;
    private Orb[] orbs;

    public Mage(double startX, double startY) {
        Random rand = new Random();

        this.coordinates.setLocation(startX, startY);
        this.spell = null;
        this.orbs = new Orb[3];
    }

    public Point2D.Double getCoordinates() {
        return this.coordinates;
    }

    public void invoke() {
        this.spell = new Spell(orbs);
    }
    public void cast() {
        // Todo: add cast logic
    }
    public void move(double x, double y) {
        this.coordinates.setLocation(x, y);
    }
}
