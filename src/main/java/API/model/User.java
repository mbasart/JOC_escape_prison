package API.model;

public class User {
    //public int idUser;
    public String userName;
    private String password;
    private int isAdmin;
    public double money;
    public int isBanned;

    public User (String userName, String password, int isAdmin, int money,int isBanned){
        //this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.money = money;
        this.isBanned = isBanned;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }
}
