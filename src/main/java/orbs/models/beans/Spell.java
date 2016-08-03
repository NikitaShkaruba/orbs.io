package orbs.models.beans;

import java.awt.geom.Point2D;

// Spell casted by mage(maybe no only by him?), stores coordinates, spell-type and caster(his mage)
public class Spell {
    private Orb[] components;
    private SpellType type;
    private Point2D.Double coordinates;

    public Spell(Orb[] components) {
        this.components = components;
        type = detectSpellType(components);
    }

    public Point2D.Double getCoordinates() {
        return coordinates;
    }
    public SpellType getType() {
        return type;
    }

    private SpellType detectSpellType(Orb[] components) {
        // Todo: remove detectSpellType plug
        return SpellType.Firebal;
    }
}

