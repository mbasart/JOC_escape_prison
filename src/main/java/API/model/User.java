package API.model;

public class User {
    //public int idUser;
    public String userName;
    private String password;
    private Boolean isAdmin;
    public int money;

    public User (String userName, String password, Boolean isAdmin, int money){
        //this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.money = money;
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
