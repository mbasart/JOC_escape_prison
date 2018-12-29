package API.implement;

import API.interfaces.IUser;
import API.model.User;
import DAO.FactorySession;
import DAO.Session;
import org.apache.log4j.Logger;

public class UserImpl implements IUser {

    private static UserImpl instance;

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(UserImpl.class);

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



    public int addUser(String userName, String password, int isAdmin, int money, int isBanned) {
        Session session = null;
        int userID = 0;
        try {
            session = FactorySession.openSession();
            User user = new User(userName, password, isAdmin, money, isBanned);
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

    public User getUser(int iduser) {
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User)session.get(new User("","",0,0,0), iduser);
            log.info("Employee: "+ user.getUserName() + " Password: "+user.getPassword() + " Money: "+Double.toString(user.getMoney())+" Admin: "+Integer.toString(user.getIsAdmin())+" Banned: "+Integer.toString(user.getIsBanned()));
        }
        catch (Exception e) {
            // LOG
            log.error("Error al obtenir un employee");
        }
        finally {
            session.close();
        }

        return user;
    }

    public void updateUser(int iduser, String userName, String password, int isAdmin, int money, int isBanned) {
        User user = this.getUser(iduser);
        user.setUserName(userName);
        user.setPassword(password);
        user.setIsAdmin(isAdmin);
        user.setMoney(money);
        user.setIsBanned(isBanned);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(user, iduser);
        }
        catch (Exception e) {
            // LOG
            log.error("Error al actualitzar un employee");
        }
        finally {
            session.close();
        }
    }

    public void deleteUser(int iduser) {
        User user = this.getUser(iduser);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(user,iduser);
        }
        catch (Exception e) {
            // LOG
            log.error("Error al borrar un employee");
        }
        finally {
            session.close();
        }

    }

}
