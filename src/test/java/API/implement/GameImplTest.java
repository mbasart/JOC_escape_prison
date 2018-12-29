package API.implement;

import API.interfaces.IGame;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameImplTest {
    IGame game;

    @Test
    public void addGameDB(){
        this.game = GameImpl.getInstance();
        this.game.addGame(0,30,400);
        this.game.clear();
    }

    @Test
    public void getGameDB(){
        this.game = GameImpl.getInstance();
        this.game.getGame(2);
        this.game.clear();
    }

    @Test
    public void updateGameDB(){
        this.game = GameImpl.getInstance();
        this.game.updateGame(2,0,40,300);
        this.game.clear();
    }
}