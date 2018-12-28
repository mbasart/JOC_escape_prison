package API.interfaces;

import API.model.Perk;

public interface IPerk {

    public int addPerk(String perkName, String usePerk, int price);
    public Perk getPerk(int perkID);

    void clear();
}
