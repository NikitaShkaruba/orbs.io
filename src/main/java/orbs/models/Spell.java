package orbs.models;

import orbs.models.enums.OrbType;
import orbs.models.enums.SpellType;

import java.awt.geom.Point2D;

// Spell casted by mage(maybe no only by him?), stores coordinates, spell-type and caster(his mage)
public class Spell {
    private OrbType[] components;
    private Point2D.Double coordinates;
    private double directionAngle;

    public Spell(OrbType[] components) {
        this.components = components;
    }

    public Point2D.Double getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Point2D.Double point) {
        coordinates.setLocation(point);
    }
    public SpellType getType() {
        return composeSpell(components);
    }

    private SpellType composeSpell(OrbType[] components) {
        // Todo: remove composeSpell plug
        return SpellType.Firebal;
    }
}

