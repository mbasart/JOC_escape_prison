package API.implement;

import API.interfaces.IPerk;
import org.junit.Test;

import static org.junit.Assert.*;

public class PerkImplTest {
    IPerk perk;

    @Test
    public void addPerkDB(){
        this.perk = PerkImpl.getInstance();
        this.perk.addPerk("vida","mesVida",25);
        this.perk.clear();
    }

    @Test
    public void getPerkDB(){
        this.perk = PerkImpl.getInstance();
        this.perk.getPerk(1);
        this.perk.clear();
    }

    @Test
    public void updatePerkDB(){
        this.perk = PerkImpl.getInstance();
        this.perk.updatePerk(1,"vida","mesVida",50);
        this.perk.clear();
    }

}