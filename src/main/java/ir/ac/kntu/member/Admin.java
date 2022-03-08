package ir.ac.kntu.member;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.stores.CoffeeShop;
import ir.ac.kntu.stores.Restaurant;

import java.util.ArrayList;

public class Admin {
    private String username;
    private String password;
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<CoffeeShop> coffeeShops = new ArrayList<>();
    private ArrayList<Delivery> deliverers = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();


    public Admin(String username,String password){
        setUsername(username);
        setPassword(password);
    }

    public boolean adminLogIn(String username, String password){ //admin log in check
        if (username.equals(this.username) && password.equals(this.password)){
            System.out.println("Welcome !");
            return true;
        }else if (!username.equals(this.username)){
            System.out.println("Your username is incorrect.");
            return false;
        }
        System.out.println("Your password is incorrect.");
        return false;
    }

    //------------ SET METHODS ----------//

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCoffeeShops(ArrayList<CoffeeShop> coffeeShops) {
        this.coffeeShops = coffeeShops;
    }

    public void setDeliverers(ArrayList<Delivery> deliverers) {
        this.deliverers = deliverers;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    //-----------------------------------//

    //------------ GET METHODS ----------//

    public ArrayList<CoffeeShop> getCoffeeShops() {
        return coffeeShops;
    }

    public ArrayList<Delivery> getDeliverers() {
        return deliverers;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    //-----------------------------------//

}
