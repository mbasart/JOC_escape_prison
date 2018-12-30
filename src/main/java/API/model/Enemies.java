package API.model;

public class Enemies {


    //public int idEnemy;
    public String movementType;
    public int initialPosition;
    public int healthPoints;
    public int speed;

    public Enemies(String movementType, int initialPosition, int healthPoints, int speed) {
        //this.idEnemy = idEnemy;
        this.movementType = movementType;
        this.initialPosition = initialPosition;
        this.healthPoints = healthPoints;
        this.speed = speed;
    }

    public Enemies(){}

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
