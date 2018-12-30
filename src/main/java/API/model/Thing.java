package API.model;

public class Thing {

    public String thingName;
    public String useThing;
    public int initialPosition;

    public Thing (String thingName, String useThing, int initialPosition){
        this.thingName = thingName;
        this.useThing = useThing;
        this.initialPosition = initialPosition;
    }

    public Thing(){}

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public String getUseThing() {
        return useThing;
    }

    public void setUseThing(String useThing) {
        this.useThing = useThing;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }
}
