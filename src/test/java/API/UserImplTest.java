package API;

import API.implement.UserImpl;
import API.interfaces.IUser;
import org.junit.Assert;
import org.junit.Test;

public class UserImplTest {
    IUser user;

    @Test
    public void addUserDB(){
        this.user = UserImpl.getInstance();
        //this.user.addUser("Meritxell", "holahola",false,500);
        this.user.addUser("Patata2", "holahola22",1,400,1);
        this.user.clear();
    }

    @Test
    public void selectUserDB(){
        this.user = UserImpl.getInstance();
        //this.user.getUser(2);
        Assert.assertEquals("Victor", this.user.getUser(2).getUserName());
    }

    @Test
    public void updateUserDB(){
        this.user = UserImpl.getInstance();
        this.user.updateUser(6,"Patata2","hello1",0,300,0);
    }

    @Test
    public void deleteUserDB(){
        this.user = UserImpl.getInstance();
        this.user.deleteUser(7);
        this.user.clear();
    }

}