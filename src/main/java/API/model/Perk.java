package API.model;

public class Perk {

    public String perkName;
    public String usePerk;
    public int price;

    public Perk(String perkName, String usePerk, int price){
        this.perkName = perkName;
        this.usePerk = usePerk;
        this.price = price;
    }

    public String getPerkName() {
        return perkName;
    }

    public void setPerkName(String perkName) {
        this.perkName = perkName;
    }

    public String getUsePerk() {
        return usePerk;
    }

    public void setUsePerk(String usePerk) {
        this.usePerk = usePerk;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
