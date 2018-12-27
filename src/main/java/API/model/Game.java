package API.model;

public class Game {

    public int idGame;
    public Boolean isCompleted;
    public int gameLength;
    public int healthPoints;

    public Game(int idGame, Boolean isCompleted, int gameLength, int healthPoints) {
        this.idGame = idGame;
        this.isCompleted = isCompleted;
        this.gameLength = gameLength;
        this.healthPoints = healthPoints;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
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
