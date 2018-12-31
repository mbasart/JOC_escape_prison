package API.model;

public class RelationUserGame {
    public String userName;
    public String nameGame;

    public RelationUserGame(){

    }

    public RelationUserGame(String userName, String nameGame){
        this.userName = userName;
        this.nameGame = nameGame;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }
}
