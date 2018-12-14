package DAO;

public interface Session {
    void save(Object entity);
    void close();
    Object get(Object theClass, int ID);
}
