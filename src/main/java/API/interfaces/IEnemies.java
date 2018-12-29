package API.interfaces;

import API.model.Enemies;

public interface IEnemies {
    public int addEnemies(String movementType, int initialPosition, int healthPoints, int speed);
    public Enemies getEnemies(int enemiesID);
    public void updateEnemies(int idEnemies, String movementType, int initialPosition, int healthPoints, int speed);
    public void deleteEnemies(int idEnemies);
    void clear();
}
