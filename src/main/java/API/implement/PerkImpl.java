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
        if(instance == null)
            instance = new PerkImpl();
        return instance;
    }

    public void clear(){
        instance = new PerkImpl();
    }

    public int addPerk(String perkName, String usePerk, int price) {
        Session session = null;
        int perkID = 0;
        try {
            session = FactorySession.openSession();
            Perk perk = new Perk(perkName,usePerk,price);
            session.save(perk);
            log.info("name: " + perk.getPerkName() + " Use: " + perk.getUsePerk() + " Price: "+ Integer.toString(perk.getPrice())); //per veure si el employee esta be
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

    public Perk getPerk(int perkID) {
        Session session = null;
        Perk perk = null;
        try {
            session = FactorySession.openSession();
            perk = (Perk)session.get(new Perk("","",0), perkID);
            log.info("Name: "+ perk.getPerkName() + " Use: "+perk.getUsePerk() + " Price: "+Integer.toString(perk.getPrice()));
        }
        catch (Exception e) {
            // LOG
            log.error("Error al obtenir un employee");
        }
        finally {
            session.close();
        }

        return perk;
    }

    public void updatePerk(int idPerk,String perkName, String usePerk, int price) {
        Perk perk = this.getPerk(idPerk);
        perk.setPerkName(perkName);
        perk.setUsePerk(usePerk);
        perk.setPrice(price);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(perk, idPerk);
        }
        catch (Exception e) {
            // LOG
            log.error("Error al actualitzar un perk");
        }
        finally {
            session.close();
        }
    }
}
