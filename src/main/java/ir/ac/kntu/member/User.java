package ir.ac.kntu.member;

import ir.ac.kntu.delivery.Address;
import ir.ac.kntu.meals.Food;
import ir.ac.kntu.order.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String phoneNumber;
    private Address address;
    private ArrayList<Order> ordersHistory = new ArrayList<>();
    private ArrayList<String> feedBackHistory = new ArrayList<>();

    public User(String phoneNumber,Address address) {
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public void seeFeedBackHistory(){
        for (String feedBack : feedBackHistory){
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            String[] comment = feedBack.split("/=/");
            System.out.println("About : " + comment[0]);
            System.out.println(comment[1]);
            System.out.println("The points you gave it : " + comment[2]);
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
    }

    public void seeLovelyFood(){
        Map<Food,Integer> lovely = new HashMap<Food,Integer>();
        for (Order order : ordersHistory){
            for (Food food : order.getFoodContent()){
                if (!lovely.containsKey(food)){
                    lovely.put(food,1);
                } else {
                    lovely.put(food,lovely.get(food)+1);
                }
            }
        }
        Food favorite = searchMap(lovely);
        System.out.println("Your favorite food in base your orders is : " + favorite.nameGenerator());
    }

    public Food searchMap(Map<Food,Integer> lovely){
        Food favorite = ordersHistory.get(0).getFoodContent().get(0);
        for(Order order : ordersHistory){
            for (Food food : order.getFoodContent()){
                if (lovely.get(food)>lovely.get(favorite)){
                    favorite = food;
                }
            }
        }
        return favorite;
    }

    public void reOrderLastOrder(){ // reOrdering last order
        Order lastOrder = ordersHistory.get(ordersHistory.size()-1);
        lastOrder.orderPrint();
        ordersHistory.add(lastOrder);
    }

    public boolean offerForNumOfOrder(){
        return ordersHistory.size() % 5 == 1;
    }

    //------------ SET METHODS ----------//

    public void setAddress(Address address) {
        this.address = address;
    }

    //-----------------------------------//

    //------------ GET METHODS ----------//

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public ArrayList<Order> getOrdersHistory() {
        return ordersHistory;
    }

    public ArrayList<String> getFeedBackHistory() {
        return feedBackHistory;
    }

    //-----------------------------------//


    @Override
    public String toString() {
        return "Number '" + phoneNumber + "'"+
                " to '" + address.name() + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o==null || getClass()!=o.getClass()){
            return false;
        }
        User user = (User) o;
        return getPhoneNumber().equals(user.getPhoneNumber()) && getAddress() == user.getAddress();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber(), getAddress());
    }
}
