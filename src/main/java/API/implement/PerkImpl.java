package API.implement;

import API.interfaces.IPerk;
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
}
