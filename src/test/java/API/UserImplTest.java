package API;

import API.implement.UserImpl;
import API.interfaces.IUser;
import org.junit.Test;

public class UserImplTest {
    IUser user;

    @Test
    public void addUserDB(){
        this.user = UserImpl.getInstance();
        this.user.addUser("Meritxell", "holahola",false,500);
        this.user.clear();
    }

}