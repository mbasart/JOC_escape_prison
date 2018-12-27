package API;

import API.implement.UserImpl;
import API.interfaces.IUser;
import org.junit.Test;

public class UserImplTest {
    IUser user;

    @Test
    public void addUserDB(){
        this.user = UserImpl.getInstance();
        //this.user.addUser("Meritxell", "holahola",false,500);
        this.user.addUser("Patata", "holahola22",1,400,1);
        this.user.clear();
    }

    @Test
    public void selectUserDB(){
        this.user = UserImpl.getInstance();
        this.user.getUser(5);
    }

}