package DAO;

public interface Session {
    void save(Object entity);
    void close();
    Object get(Object theClass, int ID);
    void update(Object object, int id);
    void delete(Object object, int id);
}
