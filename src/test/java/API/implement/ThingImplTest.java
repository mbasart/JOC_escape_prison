package API.implement;

import API.interfaces.IThing;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThingImplTest {
    IThing thing;

    @Test
    public void addThingDB(){
        this.thing = ThingImpl.getInstance();
        this.thing.addThing("claus","obrir",4);
        this.thing.clear();
    }

    @Test
    public void getThingDB(){
        this.thing = ThingImpl.getInstance();
        this.thing.getThing(1);
        this.thing.clear();
    }

    @Test
    public void updateThingDB(){
        this.thing = ThingImpl.getInstance();
        this.thing.updateThing(1,"navalla","punxar",50);
        this.thing.clear();
    }

    @Test
    public void deleteThingDB(){
        this.thing = ThingImpl.getInstance();
        this.thing.deleteThing(2);
        this.thing.clear();
    }


}