package ir.ac.kntu;

import ir.ac.kntu.delivery.*;
import ir.ac.kntu.meals.Accompaniment;
import ir.ac.kntu.meals.ColdDrinks;
import ir.ac.kntu.meals.Food;
import ir.ac.kntu.meals.HotDrink;
import ir.ac.kntu.member.Admin;
import ir.ac.kntu.member.User;
import ir.ac.kntu.menus.RestaurantMenu;
import ir.ac.kntu.stores.*;

import java.time.LocalTime;
import java.util.ArrayList;

public class Storage {
    private Admin admin;

    public Admin makeStorage(){
        this.admin = new Admin("admin","admin");
        admin.setRestaurants(makeRestaurant());
        admin.setCoffeeShops(makeCoffeeShop());
        admin.setDeliverers(makeDeliverer());
        admin.setUsers(makeUser());
        admin.getDeliverers().get(0).addEmployers(admin.getRestaurants().get(0));
        admin.getDeliverers().get(1).addEmployers(admin.getCoffeeShops().get(0));
        return this.admin;
    }

    public ArrayList<Restaurant> makeRestaurant(){
        ArrayList<RestaurantMenu> menus = makeMenuRestaurant();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Restaurant r1 = new Restaurant("Torob", Address.AZADI,"66148920", StoreType.LUXURY,2,
                Situation.OPEN,makeTime(9,0),makeTime(20,0),menus.get(0));

        Restaurant r2 = new Restaurant("Havij",Address.DARROUS,"77642132",StoreType.NORMAL,
                4,Situation.OPEN, makeTime(12,0),makeTime(23,0),menus.get(1));

        Restaurant r3 = new Restaurant("Gishniz",Address.GISHA,"94316587",StoreType.ECONOMY,
                3,Situation.OPEN, makeTime(11,0),makeTime(20,30),menus.get(2));

        Restaurant r4 = new Restaurant("Gojeh",Address.LAVIZAN,"32549816",StoreType.NORMAL,
                5,Situation.OPEN, makeTime(10,30),makeTime(22,0),menus.get(3));

        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);
        restaurants.add(r4);
        return restaurants;
    }

    public ArrayList<CoffeeShop> makeCoffeeShop(){
        ArrayList<RestaurantMenu> menus = makeMenuCoffee();
        ArrayList<CoffeeShop> coffeeShops = new ArrayList<>();
        CoffeeShop c1 = new CoffeeShop("Denj",Address.DARAKEH,"46556521",StoreType.LUXURY,
                4, Situation.OPEN, makeTime(13,0),makeTime(22,0),menus.get(1));

        CoffeeShop c2 = new CoffeeShop("Lenj",Address.NEZAMABAD,"91516142",StoreType.NORMAL,
                2,Situation.OPEN, makeTime(10,0),makeTime(19,0),menus.get(0));

        CoffeeShop c3 = new CoffeeShop("Shab",Address.SADEGHIYEH,"79153462",StoreType.ECONOMY,
                3,Situation.OPEN, makeTime(15,0),makeTime(23,0),menus.get(2));

        CoffeeShop c4 = new CoffeeShop("denj",Address.SARDARJANGAL,"12456598",StoreType.LUXURY,
                2,Situation.OPEN, makeTime(16,0),makeTime(21,30),menus.get(3));

        coffeeShops.add(c1);
        coffeeShops.add(c2);
        coffeeShops.add(c3);
        coffeeShops.add(c4);
        return coffeeShops;
    }

    public LocalTime makeTime(int h, int m){
        return LocalTime.of(h,m);
    }

    public ArrayList<RestaurantMenu> makeMenuRestaurant(){
        ArrayList<RestaurantMenu> menus = new ArrayList<>();
        RestaurantMenu m1 = new RestaurantMenu();
        RestaurantMenu m2 = new RestaurantMenu();
        RestaurantMenu m3 = new RestaurantMenu();
        RestaurantMenu m4 = new RestaurantMenu();
        m1.addToMainFoods(Food.BEEF_NOODLE);
        m2.addToMainFoods(Food.PASTA_ALFREDO);
        m3.addToMainFoods(Food.KABAB_KOBIDEH);
        m4.addToMainFoods(Food.MUSHROOM_PIZZA);
        m1.addToColdDrinks(ColdDrinks.COKA);
        m3.addToColdDrinks(ColdDrinks.BERRY_SMOOTHIE);
        m2.addToHotDrinks(HotDrink.HOT_CHOCOLATE);
        m4.addToHotDrinks(HotDrink.CAPPUCCINO);
        m1.addToAccompaniments(Accompaniment.HAWAIIAN_SALAD);
        m3.addToAccompaniments(Accompaniment.CHEESECAKE);
        menus.add(m1);
        menus.add(m2);
        menus.add(m3);
        menus.add(m4);
        return menus;
    }

    public ArrayList<RestaurantMenu> makeMenuCoffee(){
        ArrayList<RestaurantMenu> menus = new ArrayList<>();
        RestaurantMenu m1 = new RestaurantMenu();
        RestaurantMenu m2 = new RestaurantMenu();
        RestaurantMenu m3 = new RestaurantMenu();
        RestaurantMenu m4 = new RestaurantMenu();
        m1.addToMainFoods(Food.LASAGNA);
        m4.addToMainFoods(Food.HOT_DOG);
        m1.addToColdDrinks(ColdDrinks.CHOCOLATE_FRAPPE);
        m3.addToColdDrinks(ColdDrinks.BERRY_SMOOTHIE);
        m2.addToHotDrinks(HotDrink.BLACK_TEA);
        m4.addToHotDrinks(HotDrink.LATTE);
        m1.addToAccompaniments(Accompaniment.JELLY);
        m3.addToAccompaniments(Accompaniment.CHEESECAKE);
        menus.add(m1);
        menus.add(m2);
        menus.add(m3);
        menus.add(m4);
        return menus;
    }

    public ArrayList<Delivery> makeDeliverer(){
        ArrayList<Delivery> deliveries = new ArrayList<>();
        Delivery d1 = new Delivery(Vehicle.MOTORCYCLE,DeliverySalaryType.BY_HOUR,new ArrayList<Store>(),
                makeDays());
        d1.changeAvailable(Available.FREE);

        Delivery d2 = new Delivery(Vehicle.CAR,DeliverySalaryType.BY_ORDER,new ArrayList<Store>(),
                makeDays());
        d2.changeAvailable(Available.FREE);

        deliveries.add(d1);
        deliveries.add(d2);
        return deliveries;
    }

    public ArrayList<Day> makeDays(){
        Day[] allDay = Day.values();
        ArrayList<Day> days = new ArrayList<>();
        days.add(allDay[(int)(6*Math.random())]);
        days.add(allDay[(int)(6*Math.random())]);
        return days;
    }

    public ArrayList<User> makeUser(){
        ArrayList<User> users = new ArrayList<>();
        User u1 = new User("09126546548",Address.BEHBOODI);
        User u2 = new User("02144956563",Address.JALALALAHMAD);
        User u3 = new User("09304536679",Address.KAMRANIYEH);
        User u4 = new User("04665465564",Address.SAADATABAD);

        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        return users;
    }

}
