package API.implement;

import API.interfaces.Manager;
import API.model.*;
import DAO.FactorySession;
import DAO.Session;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    //***************************************************************************************************************

    public int login(String userName, String password){
        Session session = null;
        //User user = null;
        int value = 0;

        User user = null;
        try {
            log.info("Funcio login");
            session = FactorySession.openSession();
            user = (User) session.login(new User("", "", 0, 0, 0), userName);
            if (user.getPassword().equals(password)) {
                value = 2; //aixo vol dir que el usuari es correcte
                if (user.getIsBanned() == 1)
                    value = 1; //aixo vol dir que esta banned
                else if(user.getIsAdmin() == 1)
                    value = 3; //aixo vol dir que es admin
            }
            else
                value = 0; //aixo vol dir que el password o el nom es incorrecte
            //log.info("Employee: "+ user.getUserName() + " Password: "+user.getPassword() + " Money: "+Double.toString(user.getMoney())+" Admin: "+Integer.toString(user.getIsAdmin())+" Banned: "+Integer.toString(user.getIsBanned()));
        } catch (Exception e) {
            // LOG
            log.error("Error al obtenir un employee");
        } finally {
            session.close();
        }

        return value;
    }

    //obte un usari a partir del nom d'un usuari
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

    //comprova si un usuari esta a la taula d'usuaris
    public Boolean checkUser(String userName){
        Session session = null;
        Boolean encontrado = false;
        User user = null;
        try{
            log.info("funcio checkUser");
            session = FactorySession.openSession();
            user = (User)session.login(new User("","",0,0,0), userName);
            if(user.getUserName().equals(userName))
                encontrado = true; //vol dir que l'usuari existeix
            else
                encontrado = false; //vol dir que l'usuari NO existeix
        }catch (Exception e){
            log.error("Error al obtenir un user");
        }finally {
            session.close();
        }
        return encontrado;
    }

    //comprova si un game esta a la taula de games
    public Boolean checkGame(String nameGame){
        Session session = null;
        Boolean encontrado = false;
        Game game = null;
        try{
            log.info("funcio checkGame");
            session = FactorySession.openSession();
            game = (Game)session.checkGame(new Game(0,0,0,""),nameGame);
            if(game.getNameGame().equals(nameGame))
                encontrado = true; //aixo vol dir que ja existeix
            else
                encontrado = false; //aixo vol dir que no s'ha trobat i per tant no existeix
        }catch (Exception e){
            log.error("Error al obtenir un game");
        }finally {
            session.close();
        }
        return encontrado;
    }

    public Boolean register(String userName, String password){
        Boolean encontrado= false;
        if(this.login(userName,password)== 2)
            encontrado = false; //aixo vol dir que l'usuari ja existeix
        //else if(this.login(userName,password)==0)
            //encontrado = false;
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
            encontrado = true; //aixo vol dir que el usuari no existeix i s'ha registrat correctament
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

    public int admin(String userName){
        User user = this.getUserByName(userName);
        user.setUserName(userName);
        int isAdmin = 0;
        if(user.getIsAdmin()== 0)
            isAdmin = 1;
        else if (user.getIsAdmin() == 1)
            isAdmin = 0;
        user.setIsAdmin(isAdmin);

        Session session = null;
        try {
            log.info("admin user del manager");
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

        return isAdmin;
    }

    public int newGame(String userName, String nameGame) {
        Session session = null;
        int success = 0;

        int isCompleted = 0;
        int gameLength = 0;
        int healthPoints = 3;

        if(this.checkUser(userName)== false)
            success = 2; //no s'ha insertat a la base de dades perque el user no existeix
        else {
            if(this.checkGame(nameGame)== true)
                success = 3; //ja existeix aquest nom de game i per tant no el pot fer servir
            else {
                try {
                    session = FactorySession.openSession();
                    Game game = new Game(isCompleted, gameLength, healthPoints, nameGame);
                    session.save(game);
                    //log.info("Completed: " + Integer.toString(game.getIsCompleted()) + " GameLength: " + Integer.toString(game.getGameLength())+" Health:" + Integer.toString(game.getHealthPoints())); //per veure si el employee esta be

                    RelationUserGame relationusergame = new RelationUserGame(userName, nameGame);
                    session.save(relationusergame);

                    RelationGameLevel relationgamelevel = new RelationGameLevel(nameGame, 1);
                    session.save(relationgamelevel);

                    success = 1; // aixo vol dir que s'ha insertat el game satisfactoriament
                } catch (Exception e) {
                    // LOG
                    log.error("Error al afegir un nou employee");
                    success = 2; //aixo vol dir que no s'ha insertat el game a la base de dades
                } finally {
                    session.close();
                }
            }
        }

        return success;
    }

    public List<Game> loadAllGames(){

        Session session = null;
        List<Game> gameList = new ArrayList<>();
        Game game = null;


        try {
            session = FactorySession.openSession();

            gameList = session.findAllGames(new Game(0, 0, 0, ""));


        }catch (Exception e){
            log.error("Error al obtenir la llistade games");
        } finally {
            session.close();
        }

        return gameList;
    }

    public List<User> loadAllUsers(){

        Session session = null;
        List<User> userList = new ArrayList<>();
        User user = null;

        try{
            session = FactorySession.openSession();

            userList = session.findAllUsers(new User("","",0,0,0));
        }catch (Exception e){
            log.error("Error al obtenir la llista de users");
        }finally {
            session.close();
        }
        return userList;
    }

    public List<Game> loadGamesOfUser (String userName){
        Session session = null;
        List<Game> gameList = new ArrayList<>();
        Game game = null;

        List<String> gamesUser = new ArrayList<>();

        try{
            session = FactorySession.openSession();

            gamesUser = session.findGamesUser(new RelationUserGame("",""),userName);

            for (int i = 0;gamesUser.size() > i ; i++){
                log.info(gamesUser.get(i));
                game = (Game)session.checkGame(new Game(0,0,0,""),gamesUser.get(i));
                gameList.add(game);
            }

        }catch (Exception e){
            log.error("Error al obtenir la llista de games de un jugador");
        }finally {
            session.close();
        }

        return gameList;
    }

    public Game getGameOfUser (String userName, String gameName){
        Session session = null;
        Game game = null;
        List<String> gamesUser = new ArrayList<>();
        Game gameEncontrado = null;

        try{
            session = FactorySession.openSession();
            gamesUser = session.findGamesUser(new RelationUserGame("",""),userName);

            for(int i = 0; gamesUser.size() > i; i++){
                game = (Game)session.checkGame(new Game(0,0,0,""),gamesUser.get(i));

                if(gameName.equals(game.getNameGame())){
                    gameEncontrado = game;
                }
            }

        }catch (Exception e){
            log.error("Error al obtenir el game");
        }finally {
            session.close();
        }
        return gameEncontrado;
    }

    public Boolean checkGameOfUser(String userName, String gameName){
        Session session = null;
        List<String> gamesUser = new ArrayList<>();
        Boolean encontrado = false;

        try{
            session = FactorySession.openSession();
            gamesUser = session.findGamesUser(new RelationUserGame("",""),userName);

            for(int i = 0; gamesUser.size() > i;i++){
                if(gameName.equals(gamesUser.get(i)))
                    encontrado = true; //hem trobat el nom del joc
            }
        }catch (Exception e){
            log.error("Error al trobar el joc");
        }finally {
            session.close();
        }
        return encontrado;
    }

    public Level getLevelOfGame (String nameGame){
        Session session = null;
        Level level = null;
        int numLevel = 0;


        try{
            session = FactorySession.openSession();
            numLevel = session.findLevelGame(new RelationGameLevel("",0),nameGame);

            level = (Level)session.checkLevel(new Level("",0), numLevel);

        }catch (Exception e){
            log.error("Error al obtenir el Level");
        }finally {
            session.close();
        }
        return level;
    }

    public Boolean checkPartidaAcabada(String nameGame){
        Session session = null;
        Game game = null;
        Boolean isCompleted = false;
        try{
            log.info("funcio checkGame");
            session = FactorySession.openSession();
            game = (Game)session.checkGame(new Game(0,0,0,""),nameGame);
            if(game.getNameGame().equals(nameGame)) {
                if(game.getIsCompleted() == 1)
                    isCompleted = true; //aixo vol dir que la partida esta acabada
                else
                    isCompleted = false; //la partida NO esta acabada
            }
        }catch (Exception e){
            log.error("Error al obtenir un game");
        }finally {
            session.close();
        }
        return isCompleted;
    }

    public int updateGameOfUser(String userName, String nameGame, int gameLength, int healthPoints){
        Game game = this.getGameOfUser(userName,nameGame);
        game.setGameLength(gameLength);
        game.setHealthPoints(healthPoints);

        Session session = null;
        int resultat = 0;

        if(!checkUser(userName))
            resultat = 3; //no existeix usuari
        else if(!checkGame(nameGame))
            resultat = 4; //no existeix partida amb aquest nom
        else if(checkPartidaAcabada(nameGame))
            resultat = 2; //ja s'ha completat la partida
        else{
            try{
                session = FactorySession.openSession();

                int numLevel;

                numLevel = session.findLevelGame(new RelationGameLevel("", 0), nameGame);
                RelationGameLevel relationgamelevel = new RelationGameLevel(nameGame, numLevel + 1);
                session.updateGame(relationgamelevel, nameGame);
                session.updateGame(game, nameGame);

                if(numLevel == 2){
                    game.setIsCompleted(1);
                    session.updateGame(game,nameGame);
                }
                resultat = 1; //actualitzat correctament
            }catch (Exception e){
                log.error("Error al actualitzar un game");
            }
        }
        return resultat;
    }
}
