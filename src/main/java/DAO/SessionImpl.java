package DAO;

import API.model.Game;
import API.model.User;
import DAO.util.ObjectHelper;
import DAO.util.QueryHelper;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionImpl implements Session {
    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(SessionImpl.class);

    private final Connection conn;

    public SessionImpl(Connection conn) {

        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);
        log.info(insertQuery); //"INSERT INTO Employee (ID, name, surname, salary) VALUES (?,?,?,?)"

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1, 0);
            int i = 2;

            //"INSERT INTO Employee (ID, name, surname, salary) VALUES (0,'Juan','lopez',33333333)"

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            log.info(pstm.toString()); //vull veure si dona el insert amb els valors introduits

            //pstm.executeQuery();
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try{
            this.conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Object get(Object o, int ID) {
        Class theClass = o.getClass();
        String selectQuery = QueryHelper.createQuerySELECT(o);
        log.info(selectQuery);

        Object entity = null;

        PreparedStatement pstm = null;
        ResultSet result = null;

        try{
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, ID);
            log.info("Anem a executar la Query.");
            result = pstm.executeQuery();
            log.info("Query executada satisfactoriament.");

            while (result.next()){
                Field[] fields = theClass.getDeclaredFields();
                result.getString(1);
                for(int i = 0; i < fields.length; i ++){
                    ResultSetMetaData rsmd = result.getMetaData();
                    String name = rsmd.getColumnName(i+2);
                    log.info("The column name is: "+ name);
                    ObjectHelper.setter(o,name, result.getObject(i+2));
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }

        return o;
    }

    public void update(Object object, int id){
        String updateQuery = QueryHelper.createQueryUPDATE(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            int i = 1;

            for(String field: ObjectHelper.getFields(object)){
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.setObject(i,id);

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void delete(Object object, int ID) {
        String deleteQuery = QueryHelper.createQueryDELETE(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(deleteQuery);
            pstm.setObject(1, ID);

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Game> findAllGames(Object o) {
        Class theClass = o.getClass();
        String findAllQuery = QueryHelper.findAllQuery(o);

        List<Game> listOfGames = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(findAllQuery);
            rs = pstm.executeQuery();

            while (rs.next()){

                Game game = new Game(rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
                listOfGames.add(game);
                game = null;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfGames;
    }

    public List<User> findAllUsers(Object o){
        Class theClass = o.getClass();
        String findAllQuery = QueryHelper.findAllQuery(o);

        List<User> listOfUsers = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement pstm = null;

        try{
            pstm = conn.prepareStatement(findAllQuery);
            rs = pstm.executeQuery();

            while (rs.next()){
                User user = new User(rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5), rs.getInt(6));
                listOfUsers.add(user);
                user = null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return listOfUsers;
    }

    public List<String> findGamesUser(Object o, String userName){
        Class theClass = o.getClass();
        String findGamesQuery = QueryHelper.createQuerySearchGamesUser(o);

        List<String> listOfGames = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement pstm = null;

        try{
            pstm = conn.prepareStatement(findGamesQuery);
            pstm.setObject(1,userName);
            rs =pstm.executeQuery();

            while (rs.next()){
                String gameName = rs.getString(3);
                listOfGames.add(gameName);
                gameName = null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return listOfGames;
    }

    public Object login(Object o,String userName){

        Class theClass = o.getClass();
        String selectQuery = QueryHelper.createQueryLOGIN(o);
        log.info(selectQuery);

        PreparedStatement pstm = null;
        ResultSet result = null;

        try{
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, userName);

            result = pstm.executeQuery();
            log.info("Query executada satisfactoriament.");

           while (result.next()) {
                log.info("Guardem valors");
                Field[] fields = theClass.getDeclaredFields();
                result.getString(1);
                for (int i = 0; i < fields.length; i++) {
                    ResultSetMetaData rsmd = result.getMetaData();
                    String name = rsmd.getColumnName(i + 2);
                    log.info("The column name is: " + name);
                    ObjectHelper.setter(o, name, result.getObject(i + 2));
                    log.info("Valors guardats");
                }
           }

        }catch (SQLException e){
            e.printStackTrace();
            return o;
        }catch (NoSuchMethodException e){
            e.printStackTrace();
            return o;
        }

        return o;
    }

    public Object checkGame(Object o, String nameGame){
        Class theClass = o.getClass();
        String selectQuery = QueryHelper.createQueryCheckGame(o);
        log.info(selectQuery);

        PreparedStatement pstm = null;
        ResultSet result = null;

        try{
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, nameGame);

            result = pstm.executeQuery();
            log.info("Query executada satisfactoriament.");

            while (result.next()){
                log.info("Guardem valors");
                Field[] fields = theClass.getDeclaredFields();
                result.getString(1);

                for(int i=0;i<fields.length;i++){
                    ResultSetMetaData rsmd = result.getMetaData();
                    String name = rsmd.getColumnName(i + 2);
                    log.info("The column name is: " + name);
                    ObjectHelper.setter(o, name, result.getObject(i + 2));
                    log.info("Valors guardats correctament");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return o;
        }catch (NoSuchMethodException e){
            e.printStackTrace();
            return o;
        }

        return o;
    }

    public void updateUserName(Object object, String userName){
        String updateQuery = QueryHelper.createQueryUPDATEuserName(object);

        PreparedStatement pstm = null;

        try {
            log.info("Entrem a la funcio del update pel userName");
            pstm = conn.prepareStatement(updateQuery);
            int i = 1;

            for(String field: ObjectHelper.getFields(object)){
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.setObject(i,userName);

            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
