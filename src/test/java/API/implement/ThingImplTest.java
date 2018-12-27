package API.implement;

import API.interfaces.IThing;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThingImplTest {
    IThing thing;

    @Test
    public void addThing(){
        this.thing = ThingImpl.getInstance();
        this.thing.addThing("Navalla","punxar",3);
        this.thing.clear();
    }

}