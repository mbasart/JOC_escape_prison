package API.implement;

import API.interfaces.IThing;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThingImplTest {
    IThing thing;

    @Test
    public void addThingDB(){
        this.thing = ThingImpl.getInstance();
        this.thing.addThing("navalla","punxar",25);
        this.thing.clear();
    }

    @Test
    public void getThingDB(){
        this.thing = ThingImpl.getInstance();
        this.thing.getThing(1);
        this.thing.clear();
    }

}