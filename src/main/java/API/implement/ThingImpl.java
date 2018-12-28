package API.implement;

import API.interfaces.IThing;
import API.model.Thing;
import DAO.FactorySession;
import DAO.Session;
import org.apache.log4j.Logger;

public class ThingImpl implements IThing {

    private static ThingImpl instance;

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(ThingImpl.class);

    private ThingImpl(){

    }

    public static IThing getInstance(){
        if(instance == null){
            instance = new ThingImpl();
        }
        return instance;
    }

    public void clear(){
        instance = new ThingImpl();
    }

    public int addThing(String thingName, String use, int initialPosition) {
        Session session = null;
        int thingID = 0;
        try {
            session = FactorySession.openSession();
            Thing thing = new Thing(thingName, use, initialPosition);
            session.save(thing);
            log.info("name: " + thingName + " Function: " + use + " Position: "+ Integer.toString(thing.getInitialPosition())); //per veure si el employee esta be
        }
        catch (Exception e) {
            // LOG
            log.error("Error al afegir un nou employee");
        }
        finally {
            session.close();
        }

        return thingID;
    }

    public Thing getThing(int idThing) {
        Session session = null;
        Thing thing = null;
        try {
            session = FactorySession.openSession();
            thing = (Thing) session.get(new Thing("","",0), idThing);
            log.info("Thing Name: "+ thing.getThingName() + " Function: "+thing.getUse() + " Position: "+Double.toString(thing.getInitialPosition()));
        }
        catch (Exception e) {
            // LOG
            log.error("Error al obtenir un employee");
        }
        finally {
            session.close();
        }

        return thing;
    }
}
