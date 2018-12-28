package API.interfaces;

import API.model.Thing;

public interface IThing {

    public int addThing (String thingName, String use, int initialPosition);
    public Thing getThing(int idThing);
    void clear();
}
