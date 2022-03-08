package ir.ac.kntu.stores;

import ir.ac.kntu.delivery.Address;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.menus.RestaurantMenu;
import ir.ac.kntu.menus.Score;

import java.time.LocalTime;
import java.util.ArrayList;

public class Store {
    private String name;
    private Address address;
    private String phoneNumber;
    private StoreType storeType;
    private int delivererNum;
    private Delivery[] deliveries = new Delivery[delivererNum];
    private Situation situation;
    private LocalTime startWorkTime;
    private LocalTime endWorkTime;
    private double score = 5;
    private Points points;
    private ArrayList<String> comments = new ArrayList<>();
    private RestaurantMenu menu = new RestaurantMenu();

    public Store(String name,Address address, String phoneNumber, StoreType storeType, int delivererNum,
                 Situation situation,LocalTime startWorkTime, LocalTime endWorkTime, RestaurantMenu menu) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.storeType = storeType;
        this.delivererNum = delivererNum;
        this.situation = situation;
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.menu = menu;
        this.menu.setStoreType(this.storeType);
        this.points = new Points(this);
    }

    Store(){

    }

    public void changeName(String name){
        if (name.length()!=0) {
            this.name = name;
        }
    }

    public void changeAdd(String add){
        if (add.length()!=0) {
            this.address = Address.valueOf(add.toUpperCase());
        }
    }

    public void changePhone(String phone){
        if (phone.length()!=0) {
            this.phoneNumber = phone;
        }
    }

    public void changeType(String type){
        if (type.length()!=0) {
            this.storeType = StoreType.valueOf(type.toUpperCase());
        }
    }

    public void changeSituation(String situation){
        if (situation.length()!=0) {
            this.situation = Situation.valueOf(situation.toUpperCase());
        }
    }

    public void changeStartTime(String hour,String min){
        if (hour.length()!=0 && min.length()!=0){
            this.endWorkTime = LocalTime.of(Integer.parseInt(hour),Integer.parseInt(min));
        }
    }

    public void changeEndTime(String hour, String min){
        if (hour.length()!=0 && min.length()!=0){
            this.startWorkTime = LocalTime.of(Integer.parseInt(hour),Integer.parseInt(min));
        }
    }

    public void changeScore(Score score){ // change score of restaurant
        if (this.score+score.getScoreChanger()>0){
            setScore(this.score+score.getScoreChanger());
        }
    }

    public Delivery isDelivererAvailableForStore(){
        for (Delivery delivery : deliveries){
            if (delivery.isAvailable()){
                return delivery;
            }
        }
        return null;
    }

    //----------- SET METHODS -----------//

    public void setScore(double score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    public void setMenu(RestaurantMenu menu) {
        this.menu = menu;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDelivererNum(int delivererNum) {
        this.delivererNum = delivererNum;
    }

    public void setStartWorkTime(LocalTime startWorkTime) {
        this.startWorkTime = startWorkTime;
    }


    //-----------------------------------//

    //----------- GET METHODS -----------//

    public double getScore() {
        return score;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public Points getPoints() {
        return points;
    }

    public StoreType getStoreType() {
        return storeType;
    }

    public RestaurantMenu getMenu() {
        return menu;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public LocalTime getEndWorkTime() {
        return endWorkTime;
    }

    public LocalTime getStartWorkTime() {
        return startWorkTime;
    }


    //-----------------------------------//


    @Override
    public String toString() {
        return  "Store name = '" + name + '\'' +
                ", address = " + address +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", storeType = " + storeType +
                ", delivererNum = " + delivererNum +
                ", situation = " + situation +
                ", startWorkTime = " + startWorkTime +
                ", endWorkTime = " + endWorkTime +
                ", score = " + score +
                ", comments = " + comments;
    }
}
