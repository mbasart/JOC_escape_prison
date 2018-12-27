package API.implement;

import API.interfaces.IThing;
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
}
