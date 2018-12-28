package API.model;

public class Thing {


    //public int idThing;
    public String thingName;
    public String use;
    public int initialPosition;

    public Thing(String thingName, String use, int initialPosition) {
        //this.idThing = idThing;
        this.thingName = thingName;
        this.use = use;
        this.initialPosition = initialPosition;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }
}
