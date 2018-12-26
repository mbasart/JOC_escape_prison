package API;

import API.model.User;
import DAO.FactorySession;
import DAO.Session;
import org.apache.log4j.Logger;

public class UserImpl implements IUser {
    private static IUser instance;

    private UserImpl(){

    }

    public static IUser getInstance(){
        if(instance == null){
            instance = new UserImpl();
        }
        return instance;
    }

    public void clear(){
        instance = new UserImpl();
    }

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(UserImpl.class);

    public int addUser(String userName, String password, Boolean isAdmin, int money) {
        Session session = null;
        int userID = 0;
        try {
            session = FactorySession.openSession();
            User user = new User(userName, password, isAdmin, money);
            session.save(user);
            log.info("name: " + userName + "surname: " + password); //per veure si el employee esta be
        }
        catch (Exception e) {
            // LOG
            log.error("Error al afegir un nou user");
        }
        finally {
            session.close();
        }

        return userID;
    }
}
