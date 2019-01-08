package API.model;

public class Level {

    //public int numLevel;
    public String map;
    public int numLevel;


    public Level(String map, int playerPosition) {
        this.map = map;
        this.numLevel = playerPosition;
    }

    public Level(){

    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getNumLevel() {
        return numLevel;
    }

    public void setNumLevel(int numLevel) {
        this.numLevel = numLevel;
    }
}
