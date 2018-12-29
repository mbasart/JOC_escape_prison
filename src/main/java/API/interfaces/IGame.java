package API.interfaces;

import API.model.Game;

public interface IGame {

    public int addGame(int isCompleted, int gameLength, int healthPoints);
    public Game getGame(int gameID);
    public void updateGame(int idGame, int isCompleted, int gameLength, int healthPoints);

    void clear();
}
