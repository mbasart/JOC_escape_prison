package API.model;

public class Level {

    public int numLevel;
    public String map;
    public int playerPosition;


    public Level(int numLevel, String map, int playerPosition) {
        this.numLevel = numLevel;
        this.map = map;
        this.playerPosition = playerPosition;
    }

    public int getNumLevel() {
        return numLevel;
    }

    public void setNumLevel(int numLevel) {
        this.numLevel = numLevel;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }
}
