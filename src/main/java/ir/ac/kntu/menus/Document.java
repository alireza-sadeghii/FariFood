package ir.ac.kntu.menus;

import ir.ac.kntu.stores.StoreType;
import ir.ac.kntu.delivery.Address;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.Vehicle;
import ir.ac.kntu.meals.Accompaniment;
import ir.ac.kntu.meals.ColdDrinks;
import ir.ac.kntu.meals.Food;
import ir.ac.kntu.meals.HotDrink;
import ir.ac.kntu.member.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Document {
    private StoreType storeType;
    private ArrayList<Food> mainFoods;
    private ArrayList<ColdDrinks> coldDrinks;
    private ArrayList<HotDrink> hotDrinks;
    private ArrayList<Accompaniment> accompaniments;
    private Delivery delivery;
    private User user;
    private Address restaurantAdd;
    private double price;

    public Document(StoreType storeType, ArrayList<Food> mainFoods, ArrayList<ColdDrinks> coldDrinks,
                    ArrayList<HotDrink> hotDrinks, ArrayList<Accompaniment> accompaniments,Delivery delivery,
                                            Address restaurantAdd,User user,double price) {
        this.storeType = storeType;
        this.mainFoods = mainFoods;
        this.coldDrinks = coldDrinks;
        this.hotDrinks = hotDrinks;
        this.accompaniments = accompaniments;
        this.user = user;
        this.delivery = delivery;
        this.restaurantAdd = restaurantAdd;
        this.price = price;
    }

    public Document(StoreType storeType, ArrayList<Food> mainFoods, ArrayList<ColdDrinks> coldDrinks,
                    ArrayList<HotDrink> hotDrinks, ArrayList<Accompaniment> accompaniments) {
        this.storeType = storeType;
        this.mainFoods = mainFoods;
        this.coldDrinks = coldDrinks;
        this.hotDrinks = hotDrinks;
        this.accompaniments = accompaniments;
    }

    Document(){

    }

    public void timeAndDatePrint(){ // print time and date order
        String hour = Integer.toString(LocalTime.now().getHour());
        String minute = Integer.toString(LocalTime.now().getMinute());
        if (minute.length()==1){
            minute = "0"+ minute;
        }
        String date = LocalDate.now().toString();
        System.out.println(withSpaceGenerator(hour+":"+minute,date,' '));
    }

    public void printDivider(){ // print line for divide time and date from orders
        System.out.println(withSpaceGenerator("","",'━'));
    }

    public void printClient(){ // print client phone number
        String number = "Client Number : " + user.getPhoneNumber();
        String address = "Client Address : " + user.getAddress().name();
        System.out.println(withSpaceGenerator(number,address,' '));
    }

    public long preparingTime(){
        long time = 0;
        for (Food food : mainFoods){
            time += food.getPreparationTime();
        }
        for (ColdDrinks coldDrink : coldDrinks){
            time += coldDrink.getPreparationTime();
        }
        for (HotDrink hotDrink: hotDrinks){
            time += hotDrink.getPreparationTime();
        }
        for (Accompaniment accompaniment : accompaniments){
            time += accompaniment.getPreparationTime();
        }
        return time;
    }

    public void printTime() { // calculate and print estimated time for delivery
        int distance = user.getAddress().getDistance() + restaurantAdd.getDistance();
        long time = Math.round((double) (distance) / Vehicle.CAR.getDistancePerTime());
        time += preparingTime();
        System.out.println(withSpaceGenerator("Estimated time : " + Long.toString(time)+"min", "", ' '));
    }

    public void printNewLine(){ // print new empty line
        System.out.println(withSpaceGenerator("","",' '));
    }

    public int calculateDeliveryPrice(int totalPrice){ // set discount for orders with price upper than 500000
        if (totalPrice > 500000){
            return 0;
        }
        return 15000;
    }

    public void printTotalPrice(){ // print total price of orders

        int deliveryPrice = calculateDeliveryPrice((int)this.price);
        String foodPrice = "Foods :    " + Integer.toString((int)this.price);
        String priceDelivery = "Delivery : " + Integer.toString(deliveryPrice);

        System.out.println(withSpaceGenerator(foodPrice,"",' '));
        this.price += deliveryPrice;
        System.out.println(withSpaceGenerator(priceDelivery,"",' '));
        printNewLine();

        if (user.offerForNumOfOrder()){
            double discount = this.price * (0.9);
            String discountPrice = "Discounted price : " + Integer.toString((int)discount);
            System.out.println(withSpaceGenerator(discountPrice, "",' '));
            return;
        }

        String total = "Total :    " + Integer.toString((int)price);
        System.out.println(withSpaceGenerator(total, "",' '));
    }

    public void foodPrinter(){
        for (Food mainFood : this.mainFoods) {
            String forPrint = mainFood.nameGenerator();
            int price = (int)(mainFood.getBasePrice() * (1+this.storeType.getExpensivePercentage()));
            System.out.println(withSpaceGenerator(forPrint,Integer.toString(price),'-'));
        }
    }

    public void coldDrinkPrinter(){
        for (ColdDrinks coldDrinks : this.coldDrinks){
            String forPrint = coldDrinks.nameGenerator();
            int price = (int)(coldDrinks.getBasePrice()*(1+ this.storeType.getExpensivePercentage()));
            System.out.println(withSpaceGenerator(forPrint,Integer.toString(price),'-'));
        }
    }

    public void hotDrinkPrinter(){
        for(HotDrink hotDrink : this.hotDrinks){
            String forPrint = hotDrink.nameGenerator();
            int price = (int)(hotDrink.getBasePrice()*(1+ this.storeType.getExpensivePercentage()));
            System.out.println(withSpaceGenerator(forPrint,Integer.toString(price),'-'));
        }
    }

    public void accompanimentPrinter(){
        for (Accompaniment accompaniment : this.accompaniments){
            String forPrint = accompaniment.nameGenerator();
            int price = (int)(accompaniment.getBasePrice()*(1+ this.storeType.getExpensivePercentage()));
            System.out.println(withSpaceGenerator(forPrint,Integer.toString(price),'-'));
        }
    }

    public String withSpaceGenerator(String name,String price,char ch){ //this method use for help to print menu
        String space = "║ " + name;
        for (int i=0;i<60-price.length()-name.length();i++){
            space += ch;
        }
        space += " " + price + " ║";
        return space;
    }

}
