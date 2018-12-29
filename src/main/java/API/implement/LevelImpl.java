package API.implement;

import API.interfaces.ILevel;
import API.model.Level;
import DAO.FactorySession;
import DAO.Session;
import org.apache.log4j.Logger;

public class LevelImpl implements ILevel {

    private static LevelImpl instance;

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(LevelImpl.class);

    private LevelImpl(){

    }

    public static ILevel getInstance(){
        if(instance == null){
            instance = new LevelImpl();
        }
        return instance;
    }

    public void clear(){
        instance = new LevelImpl();
    }

    public int addLevel(String map, int playerPosition) {
        Session session = null;
        int levelID = 0;
        try {
            session = FactorySession.openSession();
            Level level = new Level(map, playerPosition);
            session.save(level);
            log.info("nameLevel: " + map + " PlauerPosition: " + Integer.toString(level.getPlayerPosition())); //per veure si el employee esta be
        }
        catch (Exception e) {
            // LOG
            log.error("Error al afegir un nou employee");
        }
        finally {
            session.close();
        }

        return levelID;
    }

    public Level getLevel(int levelID) {
        Session session = null;
        Level level = null;
        try {
            session = FactorySession.openSession();
            level = (Level)session.get(new Level("",0), levelID);
            log.info("Level: "+ level.getMap() + " PlayerPosition: "+Integer.toString(level.getPlayerPosition()));
        }
        catch (Exception e) {
            // LOG
            log.error("Error al obtenir un employee");
        }
        finally {
            session.close();
        }

        return level;
    }

    public void updateLevel(int idLevel, String map, int playerPosition) {
        Level level = this.getLevel(idLevel);
        level.setMap(map);
        level.setPlayerPosition(playerPosition);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(level, idLevel);
        }
        catch (Exception e) {
            // LOG
            log.error("Error al actualitzar un level");
        }
        finally {
            session.close();
        }
    }
}
