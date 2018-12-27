package API.implement;

import API.interfaces.IEnemies;
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
}
