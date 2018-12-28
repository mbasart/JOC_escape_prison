package API.implement;

import API.interfaces.IGame;
import API.model.Game;
import DAO.FactorySession;
import DAO.Session;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;

public class GameImpl implements IGame {

    private static GameImpl instance;

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(GameImpl.class);

    private GameImpl(){

    }

    public static IGame getInstance(){
        if(instance == null){
            instance = new GameImpl();
        }
        return instance;
    }

    public void clear(){
        instance = new GameImpl();
    }

    public int addGame(int isCompleted, int gameLength, int healthPoints) {
        Session session = null;
        int gameID = 0;
        try {
            session = FactorySession.openSession();
            Game game = new Game(isCompleted,gameLength,healthPoints);
            session.save(game);
            log.info("Completed: " + Integer.toString(game.getIsCompleted()) + " GameLength: " + Integer.toString(game.getGameLength())+" Health:" + Integer.toString(game.getHealthPoints())); //per veure si el employee esta be
        }
        catch (Exception e) {
            // LOG
            log.error("Error al afegir un nou employee");
        }
        finally {
            session.close();
        }

        return gameID;
    }

    public Game getGame(int gameID) {
        Session session = null;
        Game game = null;
        try {
            session = FactorySession.openSession();
            game = (Game) session.get(new Game(0,0,0), gameID);
            log.info("Completed: "+ Integer.toString(game.getIsCompleted()) + " Length: "+Integer.toString(game.getGameLength()) + " Health: "+Integer.toString(game.getHealthPoints()));
        }
        catch (Exception e) {
            // LOG
            log.error("Error al obtenir un employee");
        }
        finally {
            session.close();
        }

        return game;
    }
}
