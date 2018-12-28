package API.implement;

import API.interfaces.ILevel;
import API.model.Level;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelImplTest {

    ILevel level;

    @Test
    public void addLevelDB(){
        this.level = LevelImpl.getInstance();
        this.level.addLevel("numero2",10);
        this.level.clear();
    }

    @Test
    public void getLevelDB(){
        this.level = LevelImpl.getInstance();
        this.level.getLevel(2);
        this.level.clear();
    }

}