package API.interfaces;

import API.model.Game;
import API.model.Level;
import API.model.User;

import java.util.List;

public interface Manager {

    public int addUser (String userName, String password, int isAdmin, int money, int isBanned);
    public User getUser(int iduser);
    public void updateUser (int iduser, String userName, String password, int isAdmin, int money, int isBanned);
    void clear();

    //public int addGame(String userName, int isCompleted, int gameLength, int healthPoints);
    //public List<Game> getGames(String userName);
    //public List<Game> getAllGames();
    //public void updateGame(int idGame, int isCompleted, int gameLength, int healthPoints);

    //public Level getLevel(int levelID);
    //public int addLevel (String map, int playerPosition);

    int login(String userName, String password);
    Boolean register(String userName, String password);
    int banned(String userName);
}
