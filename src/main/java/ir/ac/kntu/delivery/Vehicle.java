package ir.ac.kntu.delivery;

public enum Vehicle {
    CAR(2),MOTORCYCLE(12);
    private final int distancePerTime; // this field considered to predict the estimated time of deliver

    Vehicle(int distancePerTime){
        this.distancePerTime = distancePerTime;
    }

    public int getDistancePerTime() {
        return distancePerTime;
    }
}
