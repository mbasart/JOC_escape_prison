package DAO;

import java.util.List;

public interface Session {
    void save(Object entity);
    void close();
    Object get(Object theClass, int ID);
    void update(Object object, int id);
    void delete(Object object, int id);
    List<Object> findAll(Object o);
}
