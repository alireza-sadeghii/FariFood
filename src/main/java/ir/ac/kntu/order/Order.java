package ir.ac.kntu.order;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.menus.Document;
import ir.ac.kntu.member.User;
import ir.ac.kntu.meals.Accompaniment;
import ir.ac.kntu.meals.ColdDrinks;
import ir.ac.kntu.meals.Food;
import ir.ac.kntu.meals.HotDrink;
import ir.ac.kntu.stores.Store;

import java.util.ArrayList;

public class Order {
    private double price;
    private Store store;
    private OrderSituation orderSituation;
    private ArrayList<Food> foodContent = new ArrayList<>();
    private ArrayList<ColdDrinks> coldDrinkContent = new ArrayList<>();
    private ArrayList<HotDrink> hotDrinkContent = new ArrayList<>();
    private ArrayList<Accompaniment> accompanimentContent = new ArrayList<>();
    private Delivery deliverer;
    private User user;

    public Order(Store store,OrderSituation orderSituation,User user) {
        this.store = store;
        this.user = user;
    }

    public void orderPrint(){
        if (foodContent.size()+coldDrinkContent.size()+hotDrinkContent.size()+accompanimentContent.size() == 0){
            System.out.println("Your order is empty.");
            return;
        }
        Document bill = new Document(store.getStoreType(),foodContent,coldDrinkContent,hotDrinkContent,
                                        accompanimentContent, deliverer,store.getAddress(),user,calculateTotalPrice());
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        bill.timeAndDatePrint();
        bill.printDivider();    // print line divider
        bill.printClient();     // print client number and address
        bill.printTime();       // print estimated time to deliver
        bill.printNewLine();
        bill.printNewLine();
        bill.foodPrinter();
        bill.printNewLine();    // insert empty line
        bill.accompanimentPrinter();
        bill.printNewLine();
        bill.coldDrinkPrinter();
        bill.printNewLine();
        bill.hotDrinkPrinter();
        bill.printNewLine();
        bill.printDivider();
        bill.printNewLine();
        bill.printTotalPrice();
        bill.printNewLine();
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    public void currentPrint(){
        Document bill = new Document(store.getStoreType(),foodContent,coldDrinkContent,hotDrinkContent,
                                                                                        accompanimentContent);
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        bill.printNewLine();
        bill.foodPrinter();
        bill.printNewLine();    // insert empty line
        bill.accompanimentPrinter();
        bill.printNewLine();
        bill.coldDrinkPrinter();
        bill.printNewLine();
        bill.hotDrinkPrinter();
        bill.printNewLine();
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    //------ CALCULATE ORDERS PRICE -----//

    public double calculateTotalPrice(){ // calculate price of all orders
        double price = 0;
        price += calculateDrinksPrice();
        price += calculateFoodPrice();
        price += calculateAccompaniment();
        return price * (1+store.getStoreType().getExpensivePercentage());
    }

    public double calculateFoodPrice(){ // calculate foods price
        double price = 0;
        for (Food food : foodContent){
            price += food.getBasePrice();
        }
        return price;
    }

    public double calculateDrinksPrice(){ // calculate drinks price
        double price = 0;
        for (ColdDrinks coldDrinks : coldDrinkContent){
            price += coldDrinks.getBasePrice();
        }
        for (HotDrink hotDrink : hotDrinkContent){
            price += hotDrink.getBasePrice();
        }
        return price;
    }

    public double calculateAccompaniment(){ // calculate accompaniments price
        double price = 0;
        for(Accompaniment accompaniment : accompanimentContent){
            price += accompaniment.getBasePrice();
        }
        return price;
    }

    //-----------------------------------//

    //------- SET ORDER SITUATION -------//

    public void situationChanger(String newState){
        setOrderSituation( OrderSituation.valueOf(newState.toUpperCase()) );
    }

    //-----------------------------------//

    public void addFood(Food food){ // add food to ordered food
        this.foodContent.add(food);
    }

    public void addColdDrink(ColdDrinks coldDrinks){ // add cold drink to ordered drinks
        this.coldDrinkContent.add(coldDrinks);
    }

    public void addHotDrink(HotDrink hotDrink){ // add hot drink to ordered drinks
        this.hotDrinkContent.add(hotDrink);
    }

    public void addAccompaniment(Accompaniment accompaniment){ // add accompaniment to ordered list
        this.accompanimentContent.add(accompaniment);
    }

    //---------- SET METHODS ------------//

    public void setOrderSituation(OrderSituation orderSituation) {
        this.orderSituation = orderSituation;
    }

    public void setDeliverer(Delivery deliverer) {
        this.deliverer = deliverer;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    //-----------------------------------//

    //---------- GET METHODS ------------//

    public User getUser() {
        return user;
    }

    public Store getStore() {
        return store;
    }

    public ArrayList<Food> getFoodContent() {
        return foodContent;
    }

    public OrderSituation getOrderSituation() {
        return orderSituation;
    }

    public Delivery getDeliverer() {
        return deliverer;
    }


    //-----------------------------------//
}
