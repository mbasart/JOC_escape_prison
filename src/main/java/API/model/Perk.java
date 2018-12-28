package API.model;

public class Perk {


    //public int idPerk;
    public String perkName;
    public String use;
    public int price;

    public Perk(String perkName, String use, int price) {
        //this.idPerk = idPerk;
        this.perkName = perkName;
        this.use = use;
        this.price = price;
    }

    public String getPerkName() {
        return perkName;
    }

    public void setPerkName(String perkName) {
        this.perkName = perkName;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
