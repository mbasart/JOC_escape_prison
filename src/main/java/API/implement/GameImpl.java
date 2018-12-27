package API.implement;

import API.interfaces.IGame;
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
}
