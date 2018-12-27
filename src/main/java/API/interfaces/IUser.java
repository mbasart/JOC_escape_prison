package API.interfaces;

import API.model.User;

public interface IUser {

    public int addUser (String userName, String password, int isAdmin, int money, int isBanned);
    public User getUser(int iduser);
    void clear();
}
