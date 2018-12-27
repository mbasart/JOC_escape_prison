package API.model;

public class Thing {


    //public int idThing;
    public String thingName;
    public String function;
    public int initialPosition;

    public Thing(String thingName, String function, int initialPosition) {
        //this.idThing = idThing;
        this.thingName = thingName;
        this.function = function;
        this.initialPosition = initialPosition;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }
}
