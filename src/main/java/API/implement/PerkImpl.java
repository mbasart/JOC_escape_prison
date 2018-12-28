package API.implement;

import API.interfaces.IPerk;
import API.model.Perk;
import DAO.FactorySession;
import DAO.Session;
import org.apache.log4j.Logger;

public class PerkImpl implements IPerk {

    private static PerkImpl instance;

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(PerkImpl.class);

    private PerkImpl(){

    }

    public static IPerk getInstance(){
        if(instance == null){
            instance = new PerkImpl();
        }
        return instance;
    }

    public void clear(){
        instance = new PerkImpl();
    }

    public int addPerk(String perkName, String use, int price) {
        Session session = null;
        int perkID = 0;
        try {
            session = FactorySession.openSession();
            Perk perk = new Perk(perkName,use,price);
            session.save(perk);
            log.info("name: " + perk.getPerkName() + " Function: " + perk.getUse() + " Price: "+ Integer.toString(perk.getPrice())); //per veure si el employee esta be
        }
        catch (Exception e) {
            // LOG
            log.error("Error al afegir un nou employee");
        }
        finally {
            session.close();
        }

        return perkID;
    }
}
