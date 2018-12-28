package API.interfaces;

import API.model.Level;

public interface ILevel {

    public int addLevel (String map, int playerPosition);
    public Level getLevel(int levelID);

    void clear();
}
