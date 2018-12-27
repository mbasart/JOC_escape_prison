package API.interfaces;

import API.model.User;

public interface IUser {

    public int addUser (String userName, String password, int isAdmin, int money);
    public User getUser(int iduser);
    void clear();
}
