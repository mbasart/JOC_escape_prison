package API.interfaces;

import API.model.Thing;

public interface IThing {
    public int addThing (String thingName, String useThing, int initialPosition);
    public Thing getThing(int thingID);
    public void updateThing(int idThing, String thingName, String useThing, int initialPosition);
    public void deleteThing (int idThing);

    void clear();
}
