package API.implement;

import API.interfaces.Manager;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerImplTest {
    Manager manager;

    @Test
    public void addUser(){
        this.manager = ManagerImpl.getInstance();
        this.manager.addUser("Carla","1234",0,400,0);
        this.manager.clear();
    }

    @Test
    public void getUserTest(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals("Carla",this.manager.getUser(9).getUserName());
        this.manager.clear();
    }

    @Test
    public void updateUserTest(){
        this.manager = ManagerImpl.getInstance();
        this.manager.updateUser(9,"Carla","1234hola",1,400,0);
        this.manager.clear();
    }

    @Test
    public void loginDB(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals(2,this.manager.login("Meritxell", "holahola"));
        Assert.assertEquals(1,this.manager.login("Joselito","1234"));
        Assert.assertEquals(0,this.manager.login("Meritxell","hol"));
        this.manager.clear();
    }

    @Test
    public void registerDB(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals(false,this.manager.register("Meritxell","holahola"));
        this.manager.clear();
    }

    @Test
    public void bannedDB(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals(1,this.manager.banned("Joselito"));
        this.manager.clear();
    }
}