package orbs.models.beans;

import java.awt.geom.Point2D;

// It's a player, bean stores his position, health, mana, currently casted orbs
public class Mage {
    private Point2D.Double coordinates;
    private Spell spell;
    private Orb[] orbs;

    public Mage(double startX, double startY) {
        this.coordinates.setLocation(startX, startY);
        this.spell = null;
        this.orbs = new Orb[3];
    }

    public Point2D.Double getCoordinates() {
        return this.coordinates;
    }
    public void move(double x, double y) {
        this.coordinates.setLocation(x, y);
    }

    public void cast() {
        this.spell = new Spell(orbs);
        // Todo: add world bean integration
        //
    }
}
