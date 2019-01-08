package API.model;

public class RelationGameLevel {
    public String nameGame;
    public int numLevel;

    public RelationGameLevel(){}

    public RelationGameLevel(String nameGame, int numLevel){
        this.nameGame = nameGame;
        this.numLevel = numLevel;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public int getNumLevel() {
        return numLevel;
    }

    public void setNumLevel(int numLevel) {
        this.numLevel = numLevel;
    }
}
