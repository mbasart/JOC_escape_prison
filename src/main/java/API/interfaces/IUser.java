package API.interfaces;

public interface IUser {

    public int addUser (String userName, String password, Boolean isAdmin, int money);
    void clear();
}
