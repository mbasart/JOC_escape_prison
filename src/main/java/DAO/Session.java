package DAO;

import API.model.Game;
import API.model.User;

import java.util.List;

public interface Session {
    void save(Object entity);
    void close();
    Object get(Object theClass, int ID);
    void update(Object object, int id);
    void delete(Object object, int id);

    List<Game> findAllGames(Object o);
    List<User> findAllUsers(Object o);

    Object login(Object object, String userName);
    Object checkGame(Object o, String nameGame);
    void updateUserName(Object object, String userName);
}
