package API.model;

public class Level {

    //public int numLevel;
    public String map;
    public int playerPosition;


    public Level(String map, int playerPosition) {
        this.map = map;
        this.playerPosition = playerPosition;
    }

    public Level(){

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
