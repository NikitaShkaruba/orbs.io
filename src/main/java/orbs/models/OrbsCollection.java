package orbs.models;

import orbs.models.enums.OrbEnum;

//It is collection with orbs for each Mage
public class OrbsCollection {

    private OrbEnum[] orbs;
    private int lenght = 3;

    public OrbsCollection() {
        this.orbs = new OrbEnum[this.lenght];
    }

    public OrbsCollection(int OrbsCount) {
        if(OrbsCount > 0) {
            this.lenght = OrbsCount;
        }
        this.orbs = new OrbEnum[this.lenght];
    }

    public boolean appendOrb(OrbEnum orb) {
        boolean Inserted = false;
        if(orb != null){
            for(int i = 0; i < this.lenght; i++) {
                if (this.orbs[i] == null){
                    this.orbs[i] = orb;
                    Inserted = true;
                    break;
                }
            }
        }
        return Inserted;
    }

    public OrbEnum[] getOrbs(){
        return this.orbs;
    }

    public OrbEnum[] pullOrbs(){
        OrbEnum[] resultOrbs = this.orbs;
        this.orbs = new OrbEnum[this.lenght];
        return resultOrbs;
    }

    public int getLength(){
        return this.lenght;
    }
}
