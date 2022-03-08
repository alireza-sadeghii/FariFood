package ir.ac.kntu.menus;

import ir.ac.kntu.stores.StoreType;
import ir.ac.kntu.delivery.Address;
import ir.ac.kntu.meals.Accompaniment;
import ir.ac.kntu.meals.ColdDrinks;
import ir.ac.kntu.meals.Food;
import ir.ac.kntu.meals.HotDrink;
import ir.ac.kntu.member.Admin;
import ir.ac.kntu.stores.CoffeeShop;
import ir.ac.kntu.stores.Restaurant;
import ir.ac.kntu.stores.Store;

import java.time.LocalTime;
import java.util.Scanner;

public class StoreAppMenu {
    private Admin admin;
    private Scanner scanner;

    public StoreAppMenu(Admin admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void showMenu(){
        System.out.println("1- Watch store information");
        System.out.println("2- Make new Store");
        System.out.println("3- Change store information");
        System.out.println("4- watch food comment");
        System.out.println("5- Exit");
        String choose = scanner.nextLine();
        menuSwitch(choose);
    }

    private void menuSwitch(String input){
        switch (input){
            case "1":
                System.out.println(giveDesiredStore().toString());
                break;
            case "2":
                makeNewStore();
                break;
            case "3":
                changeStoreInfo();
                break;
            case "4":
                commentFood();
                break;
            default:
                break;
        }
    }

    private void commentFood(){
        Store store = giveDesiredStore();
        System.out.println("Please enter name of food :");
        String name = scanner.nextLine().toUpperCase().replace(' ','_');
        store.getPoints().commentOf(name);
    }


    private void changeStoreInfo(){
        Store store = giveDesiredStore();
        System.out.println("Enter new content to change any of the fields");
        System.out.println("If you do not intend to change the field, leave it blank");
        System.out.println("Enter new name :");
        store.changeName(scanner.nextLine());
        System.out.println("Enter new address :");
        store.changeAdd(scanner.nextLine());
        System.out.println("Enter new phone number :");
        store.changePhone(scanner.nextLine());
        System.out.println("Enter new store type : [normal,luxury,economy]");
        store.changeType(scanner.nextLine());
        System.out.println("Enter new situation : [open,close]");
        store.changeSituation(scanner.nextLine());
        System.out.println("Enter new start time : [hour:min]");
        store.changeStartTime(scanner.nextLine(),scanner.nextLine());
        System.out.println("Enter new end time : [hour:min]");
        store.changeEndTime(scanner.nextLine(),scanner.nextLine());
    }

    private void makeNewStore(){
        String storeType = whatStore();
        if (storeType.equalsIgnoreCase("restaurant")){
            Restaurant newStore = new Restaurant();
            giveInfo(newStore);
            admin.getRestaurants().add(newStore);

        } else{
            CoffeeShop newStore = new CoffeeShop();
            giveInfo(newStore);
            admin.getCoffeeShops().add(newStore);
        }

    }

    private void giveInfo(Store newStore){
        System.out.println("Enter store name : ");
        newStore.setName(scanner.nextLine());
        System.out.println("Enter store phone number : ");
        newStore.setPhoneNumber(scanner.nextLine());
        System.out.println("Enter Address : ");
        newStore.setAddress(Address.valueOf(scanner.nextLine().toUpperCase()));
        System.out.println("Enter type of store : [Luxury , normal , economy]");
        newStore.setStoreType(StoreType.valueOf(scanner.nextLine().toUpperCase()));
        System.out.println("Enter deliverer number :");
        newStore.setDelivererNum(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter start work time : [hour:min]");
        String start = scanner.nextLine();
        newStore.setStartWorkTime(LocalTime.of(extractTime(start,"h"),extractTime(start,"m")));
        System.out.println("Enter end work time : [hour:min]");
        String end = scanner.nextLine();
        newStore.setStartWorkTime(LocalTime.of(extractTime(end,"h"),extractTime(end,"m")));
        storeMenuWrite(newStore);
    }

    private void storeMenuWrite(Store store){
        giveFoods(store);
        giveColds(store);
        giveHots(store);
        giveAccompaniments(store);
    }

    private void giveFoods(Store store){
        System.out.println("Please enter the name of foods, and if the number of foods is ended enter \"end\"");
        String food = scanner.nextLine().toUpperCase().replace(' ','_');;
        while(!food.equalsIgnoreCase("end")){
            store.getMenu().addToMainFoods(Food.valueOf(food));
            food = scanner.nextLine().toUpperCase().replace(' ','_');
        }
    }

    private void giveColds(Store store){
        System.out.println("Please enter the name of cold drinks, and if the number of drinks is ended enter \"end\"");
        String drinks = scanner.nextLine().toUpperCase().replace(' ','_');
        while(!drinks.equalsIgnoreCase("end")){
            store.getMenu().addToColdDrinks(ColdDrinks.valueOf(drinks));
            drinks = scanner.nextLine().toUpperCase().replace(' ','_');
        }
    }

    private void giveHots(Store store){
        System.out.println("Please enter the name of hot drinks, and if the number of drinks is ended enter \"end\"");
        String drinks = scanner.nextLine().toUpperCase().replace(' ','_');
        while(!drinks.equalsIgnoreCase("end")){
            store.getMenu().addToHotDrinks(HotDrink.valueOf(drinks));
            drinks = scanner.nextLine().toUpperCase().replace(' ','_');
        }
    }

    private void giveAccompaniments(Store store){
        System.out.println("Please enter the name of accompaniments,and if the number of foods is ended enter \"end\"");
        String accompaniments = scanner.nextLine().toUpperCase().replace(' ','_');
        while(!accompaniments.equalsIgnoreCase("end")){
            store.getMenu().addToAccompaniments(Accompaniment.valueOf(accompaniments));
            accompaniments = scanner.nextLine().toUpperCase().replace(' ','_');
        }
    }

    private int extractTime(String time,String t){
        String[] workTime =  time.split(":");
        if (t.equalsIgnoreCase("h")){
            return Integer.parseInt(workTime[0]);
        }
        return Integer.parseInt(workTime[1]);
    }

    private String whatStore(){
        System.out.println("What type of store is desired?");
        System.out.println("- Restaurant");
        System.out.println("- CoffeeShops");
        return scanner.nextLine();
    }

    private Store[] allStoreOfType(String type){
        if (type.equalsIgnoreCase("restaurant")){
            return admin.getRestaurants().toArray(new Store[0]);
        }
        return admin.getCoffeeShops().toArray(new CoffeeShop[0]);
    }

    private Store giveDesiredStore(){
        Store[] all = allStoreOfType(whatStore());
        System.out.println("Please enter Store name :");
        String storeName = scanner.nextLine();
        for (Store store : all){
            if (store.getName().equalsIgnoreCase(storeName)){
                return store;
            }
        }
        return all[0];
    }

    //----------- GET METHODS -----------//

    public Admin getAdmin() {
        return admin;
    }

    //-----------------------------------//
}
