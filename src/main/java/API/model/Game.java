package API.model;

public class Game {

    //public int idGame;
    public int isCompleted;
    public int gameLength;
    public int healthPoints;

    public Game(int isCompleted, int gameLength, int healthPoints) {
        //this.idGame = idGame;
        this.isCompleted = isCompleted;
        this.gameLength = gameLength;
        this.healthPoints = healthPoints;
    }

    public Game(){}

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getGameLength() {
        return gameLength;
    }

    public void setGameLength(int gameLength) {
        this.gameLength = gameLength;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
