package API.implement;

import API.interfaces.ILevel;
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
}
