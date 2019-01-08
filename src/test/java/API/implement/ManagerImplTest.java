package API.implement;

import API.interfaces.Manager;
import org.junit.Assert;
import org.junit.Test;

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


    //Test de coses que fem servir despres

    @Test
    public void loginDB(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals(2,this.manager.login("Meritxell", "holahola"));
        Assert.assertEquals(1,this.manager.login("Joselito","1234"));
        Assert.assertEquals(0,this.manager.login("Meritxell","hol"));
        Assert.assertEquals(3,this.manager.login("Noelia","holahola22"));
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

    @Test
    public void adminDB(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals(0,this.manager.admin("Meritxell"));
        this.manager.clear();
    }

    @Test
    public void newGameDB(){
        this.manager = ManagerImpl.getInstance();
        //Assert.assertEquals(1,this.manager.newGame("Meritxell","partida10"));
        //Assert.assertEquals(2,this.manager.newGame("Paula","partida11"));
        Assert.assertEquals(3, this.manager.newGame("Meritxell","partida5"));
        this.manager.clear();
    }

    @Test
    public void loadGameDB(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals("partida1",this.manager.loadAllGames().get(0).getNameGame());
        Assert.assertEquals("partida2",this.manager.loadAllGames().get(1).getNameGame());
        Assert.assertEquals("partida3",this.manager.loadAllGames().get(2).getNameGame());
        Assert.assertEquals("partida4",this.manager.loadAllGames().get(3).getNameGame());
        Assert.assertEquals("partida5",this.manager.loadAllGames().get(4).getNameGame());
        Assert.assertEquals("game1",this.manager.loadAllGames().get(5).getNameGame());
        this.manager.clear();
    }

    @Test
    public void loadUserDB(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals("Meritxell",this.manager.loadAllUsers().get(0).getUserName());
        Assert.assertEquals("Victor",this.manager.loadAllUsers().get(1).getUserName());
        Assert.assertEquals("Sergi",this.manager.loadAllUsers().get(2).getUserName());
        Assert.assertEquals("PatataFregida",this.manager.loadAllUsers().get(9).getUserName());
        this.manager.clear();
    }

    @Test
    public void loadGamesOfUser(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals("partida5",this.manager.loadGamesOfUser("Meritxell").get(0).getNameGame());
        Assert.assertEquals("partida4",this.manager.loadGamesOfUser("Meritxell").get(1).getNameGame());
        Assert.assertEquals(3,this.manager.loadGamesOfUser("Meritxell").get(0).getHealthPoints());
        this.manager.clear();
    }

    @Test
    public void getGameOfUserDB(){
        this.manager = ManagerImpl.getInstance();
        Assert.assertEquals("partida4",this.manager.getGameOfUser("Meritxell","partida4").getNameGame());
        this.manager.clear();
    }
}