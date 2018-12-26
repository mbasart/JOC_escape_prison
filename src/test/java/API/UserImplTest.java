package API;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserImplTest {
    IUser user;

    @Test
    public void addUserDB(){
        this.user = UserImpl.getInstance();
        this.user.addUser("Meritxell", "holahola",false,500);
        this.user.clear();
    }

}