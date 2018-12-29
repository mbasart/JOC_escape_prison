package API.interfaces;

import API.model.Perk;

public interface IPerk {

    public int addPerk(String perkName, String usePerk, int price);
    public Perk getPerk(int perkID);
    public void updatePerk(int idPerk, String perkName, String usePerk, int price);

    void clear();
}
