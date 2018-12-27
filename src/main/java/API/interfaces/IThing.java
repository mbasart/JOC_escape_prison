package API.interfaces;

import API.model.Thing;

public interface IThing {

    public int addThing (String thingName, String function, int initialPosition);
    public Thing getThing(int idThing);
    void clear();
}
