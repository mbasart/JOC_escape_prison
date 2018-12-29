package API.implement;

import API.interfaces.ILevel;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelImplTest {

    ILevel level;

    @Test
    public void addLevelDB(){
        this.level = LevelImpl.getInstance();
        this.level.addLevel("numero3",15);
        this.level.clear();
    }

    @Test
    public void getLevelDB(){
        this.level = LevelImpl.getInstance();
        //this.level.getLevel(2);
        Assert.assertEquals("numero1",this.level.getLevel(1).getMap());
        this.level.clear();
    }

    @Test
    public void updateLevelDB(){
        this.level = LevelImpl.getInstance();
        this.level.updateLevel(1,"numero1",4);
    }

    @Test
    public void deleteLevelDB(){
        this.level = LevelImpl.getInstance();
        this.level.deleteLevel(2);
    }

}