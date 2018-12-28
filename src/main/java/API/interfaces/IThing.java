package API.interfaces;

import API.model.Thing;

public interface IThing {
    public int addThing (String thingName, String useThing, int initialPosition);
    public Thing getThing(int thingID);

    void clear();
}
