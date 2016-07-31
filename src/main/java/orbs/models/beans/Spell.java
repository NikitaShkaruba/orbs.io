package orbs.models.beans;

// Spell casted by mage(maybe no only by him?), stores coordinates, spell-type and caster(his mage)
public class Spell {
    private Orb[] components;
    private SpellType type;

    public Spell(Orb[] components) {
        this.components = components;
        type = getSpellType(components);
    }

    private SpellType getSpellType(Orb[] components) {
        // Todo: remove getSpellType plug
        return SpellType.Firebal;
    }
}

