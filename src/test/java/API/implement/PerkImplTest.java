package API.implement;

import API.interfaces.IPerk;
import org.junit.Test;

import static org.junit.Assert.*;

public class PerkImplTest {
    IPerk perk;

    @Test
    public void addPerkDB(){
        this.perk = PerkImpl.getInstance();
        this.perk.addPerk("mesVida","vida",25);
        this.perk.clear();
    }

}