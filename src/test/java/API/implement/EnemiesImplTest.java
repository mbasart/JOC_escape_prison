package API.implement;

import API.interfaces.IEnemies;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemiesImplTest {
    IEnemies enemies;

    @Test
    public void addEnemiesDB(){
        this.enemies = EnemiesImpl.getInstance();
        this.enemies.addEnemies("vertical",225,500,35);
        this.enemies.clear();
    }

    @Test
    public void getEnemiesDB(){
        this.enemies = EnemiesImpl.getInstance();
        this.enemies.getEnemies(2);
        this.enemies.clear();
    }

    @Test
    public void updateEnemiesDB(){
        this.enemies = EnemiesImpl.getInstance();
        this.enemies.updateEnemies(2,"vertical",225,500,20);
    }

}