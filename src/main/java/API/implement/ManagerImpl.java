package API.implement;

import API.interfaces.Manager;
import API.model.User;
import DAO.FactorySession;
import DAO.Session;
import org.apache.log4j.Logger;

import java.io.BufferedOutputStream;

public class ManagerImpl implements Manager {
    private static ManagerImpl instance;

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(UserImpl.class);

    private ManagerImpl(){

    }

    public static Manager getInstance(){
        if(instance == null){
            instance = new ManagerImpl();
        }
        return instance;
    }

    public void clear(){
        instance = new ManagerImpl();
    }

    public int addUser(String userName, String password, int isAdmin, int money, int isBanned) {
        Session session = null;
        int userID = 0;
        try {
            log.info("add User del Manager");
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
            log.info("get User del manager");
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
            log.info("update user del manager");
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

    //A partir d'aqui funcions que es necessiten pel joc de veritat!!!!!!!!!!

    public Boolean login(String userName, String password){
        Session session = null;
        //User user = null;
        Boolean encontrado = false;
        User user= null;
        try {
            log.info("Funcio login");
            session = FactorySession.openSession();
             user = (User)session.login(new User("","",0,0,0), userName);
             if(user.getPassword().equals(password))
                 encontrado = true;
             else
                 encontrado = false;
            //log.info("Employee: "+ user.getUserName() + " Password: "+user.getPassword() + " Money: "+Double.toString(user.getMoney())+" Admin: "+Integer.toString(user.getIsAdmin())+" Banned: "+Integer.toString(user.getIsBanned()));
        }
        catch (Exception e) {
            // LOG
            log.error("Error al obtenir un employee");
        }
        finally {
            session.close();
        }
        return encontrado;
    }

    private User getUserByName(String userName){
        Session session = null;
        Boolean encontrado = false;
        User user = null;
        try{
            log.info("funcio getUserByName");
            session = FactorySession.openSession();
            user = (User)session.login(new User("","",0,0,0), userName);
            if(user.getUserName().equals(userName))
                encontrado = true;
            else
                encontrado = false;
        }catch (Exception e){
            log.error("Error al obtenir un user");
        }finally {
            session.close();
        }
        return user;
    }

    public Boolean register(String userName, String password){
        Boolean encontrado= false;
        if(this.login(userName,password)== true)
            encontrado = false;
        else {
            Session session = null;
            int isAdmin = 0;
            int money = 0;
            int isBanned = 0;
            try {
                log.info("Register en el Manager");
                session = FactorySession.openSession();
                User user = new User(userName, password, isAdmin, money, isBanned);
                session.save(user);
                log.info("name: " + userName + "surname: " + password); //per veure si el employee esta be
            } catch (Exception e) {
                // LOG
                log.error("Error al afegir un nou user");
            } finally {
                session.close();
            }
            encontrado = true;
        }

        return encontrado;
    }

    public int banned(String userName) {
        User user = this.getUserByName(userName);
        user.setUserName(userName);
        int isBanned=0;
        if(user.getIsBanned()==0)
            isBanned = 1;
        else if(user.getIsBanned()== 1)
            isBanned = 0;
        user.setIsBanned(isBanned);

        Session session = null;
        try {
            log.info("banned user del manager");
            session = FactorySession.openSession();
            session.updateUserName(user, userName);
        }
        catch (Exception e) {
            // LOG
            log.error("Error al actualitzar un employee");
        }
        finally {
            session.close();
        }

        return isBanned;
    }
}
