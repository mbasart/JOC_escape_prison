package API.implement;

import API.interfaces.IGame;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameImplTest {
    IGame game;

    @Test
    public void addGameDB(){
        this.game = GameImpl.getInstance();
        this.game.addGame(0,40,400, "partida1");
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
        this.game.updateGame(2,0,40,300, "partida2");
        this.game.clear();
    }

    @Test
    public void deleteGameDB(){
        this.game = GameImpl.getInstance();
        this.game.deleteGame(3);
        this.game.clear();
    }
}