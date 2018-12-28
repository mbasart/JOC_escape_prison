package API.implement;

import API.interfaces.IEnemies;
import API.model.Enemies;
import DAO.FactorySession;
import DAO.Session;
import org.apache.log4j.Logger;

public class EnemiesImpl implements IEnemies {

    private static EnemiesImpl instance;

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(EnemiesImpl.class);

    private EnemiesImpl(){

    }

    public static IEnemies getInstance(){
        if(instance == null){
            instance = new EnemiesImpl();
        }
        return instance;
    }

    public void clear(){
        instance = new EnemiesImpl();
    }

    public int addEnemies(String movementType, int initialPosition, int healthPoints, int speed) {
        Session session = null;
        int enemiesID = 0;
        try {
            session = FactorySession.openSession();
            Enemies enemies = new Enemies(movementType,initialPosition,healthPoints,speed);
            session.save(enemies);
            log.info("Movement: " + enemies.getMovementType() + " Position: " + Integer.toString(enemies.getInitialPosition())+" Health: "+Integer.toString(enemies.getHealthPoints())+" Speed: "+Integer.toString(enemies.getSpeed())); //per veure si el employee esta be
        }
        catch (Exception e) {
            // LOG
            log.error("Error al afegir un nou employee");
        }
        finally {
            session.close();
        }

        return enemiesID;
    }

    public Enemies getEnemies(int enemiesID) {
        Session session = null;
        Enemies enemies = null;
        try {
            session = FactorySession.openSession();
            enemies = (Enemies) session.get(new Enemies("",0,0,0), enemiesID);
            log.info("Movement: " + enemies.getMovementType() + " Position: " + Integer.toString(enemies.getInitialPosition())+" Health: "+Integer.toString(enemies.getHealthPoints())+" Speed: "+Integer.toString(enemies.getSpeed()));
        }
        catch (Exception e) {
            // LOG
            log.error("Error al obtenir un employee");
        }
        finally {
            session.close();
        }

        return enemies;
    }
}
