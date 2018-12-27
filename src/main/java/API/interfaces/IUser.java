package API.interfaces;

import API.model.User;

public interface IUser {

    public int addUser (String userName, String password, Boolean isAdmin, int money);
    public User getUser(int iduser);
    void clear();
}
