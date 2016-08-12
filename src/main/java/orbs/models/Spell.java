package orbs.models;

import orbs.models.enums.OrbEnum;
import orbs.models.enums.SpellTypeEnum;
import java.awt.geom.Point2D;

// Spell casted by mage(maybe no only by him?), stores coordinates, spell-type and caster(his mage)
public class Spell {
    private OrbEnum[] components;
    private SpellTypeEnum SpellType;
    private Point2D.Double coordinates;
    private double directionAngle;

    public Spell(OrbEnum[] components) {
        this.components = components;
        this.SpellType = detectSpellType(components);
    }

    public Point2D.Double getCoordinates() {
        return this.coordinates;
    }
    public void setCoordinates(Point2D.Double point) {
        this.coordinates.setLocation(point);
    }
    public SpellTypeEnum getType() {
        return this.SpellType;
    }

    private SpellTypeEnum detectSpellType(OrbEnum[] components) {
        // Todo: remove detectSpellType plug
        return SpellTypeEnum.Firebal;
    }
}

