package API.model;

public class Perk {


    public int idPerk;
    public String perkName;
    public String function;
    public int price;

    public Perk(int idPerk, String perkName, String function, int price) {
        this.idPerk = idPerk;
        this.perkName = perkName;
        this.function = function;
        this.price = price;
    }

    public int getIdPerk() {
        return idPerk;
    }

    public void setIdPerk(int idPerk) {
        this.idPerk = idPerk;
    }

    public String getPerkName() {
        return perkName;
    }

    public void setPerkName(String perkName) {
        this.perkName = perkName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
