package ir.ac.kntu.menus;

import ir.ac.kntu.stores.StoreType;
import ir.ac.kntu.delivery.Address;
import ir.ac.kntu.meals.Accompaniment;
import ir.ac.kntu.meals.ColdDrinks;
import ir.ac.kntu.meals.Food;
import ir.ac.kntu.meals.HotDrink;
import ir.ac.kntu.member.Admin;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.order.OrderSituation;
import ir.ac.kntu.stores.CoffeeShop;
import ir.ac.kntu.stores.Restaurant;
import ir.ac.kntu.stores.Store;

import java.time.LocalTime;
import java.util.*;

public class Sorter {
    private Admin admin;
    private Document print;


    public Sorter(Admin admin){
        setAdmin(admin);
        setPrint();
    }

    public void storeSort(double condition,String type,String base){

        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        Store[] allInSort;
        if (base.equals("score")) {
            allInSort = sortingScoreBase(condition,type);
            for (Store store : allInSort){ //printing with score
                //if (isOpen(store)) {
                // todo : for With less data, it is better for this condition to convert to comment
                System.out.println(print.withSpaceGenerator(store.getName(), Double.toString(store.getScore()),
                            '-'));
                //}
            }
        } else{
            allInSort = sortingCommentBse(condition,type);
            for (Store store : allInSort){ //printing with comments number
                //if (isOpen(store)) {
                    //todo : for With less data, it is better for this condition to convert to comment
                String num = Integer.toString(store.getComments().size());
                System.out.println(print.withSpaceGenerator(store.getName(), num, '-'));
                //}
            }
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    private Store[] setStoreType(String type){ // determine type of store
        Store[] allInSort;
        if (type.equals("restaurant")) {
            allInSort = admin.getRestaurants().toArray(new Restaurant[0]);
        } else{
            allInSort = admin.getCoffeeShops().toArray(new CoffeeShop[0]);
        }
        return allInSort;
    }

    //---------- STORE BY SCORE ---------//

    public Store[] sortingScoreBase(double condition,String type){ // sort an array with bubble sort
        // Strategy 1  : based on restaurant score : Ascending
        // Strategy 2  : based on restaurant score : Descending
        // Strategy 10 : based on coffeeShop score : Descending
        // Strategy 11 : based on coffeeShop score : Descending

        Store[] allInSort = setStoreType(type);

        for (int i=0;i<allInSort.length-1;i++){
            for (int j=i+1;j<allInSort.length;j++){
                if (condition * allInSort[i].getScore() > condition * allInSort[j].getScore()){
                    Store temp = allInSort[i];
                    allInSort[i] = allInSort[j];
                    allInSort[j] = temp;
                }
            }
        }
        return allInSort;
    }

    //-----------------------------------//

    //--------- STORE BY COMMENT --------//

    public Store[] sortingCommentBse(double condition,String type){
        // Strategy 3  : based on restaurant comment : Ascending
        // Strategy 4  : based on restaurant comment : Descending
        // Strategy 12 : based on coffeeShop comment : Descending
        // Strategy 13 : based on coffeeShop comment : Descending

        Store[] allInSort = setStoreType(type);

        for (int i=0;i<allInSort.length-1;i++){
            for (int j=i+1;j< allInSort.length;j++){
                if (condition * allInSort[i].getComments().size() > condition * allInSort[j].getComments().size()){
                    Store temp = allInSort[i];
                    allInSort[i] = allInSort[j];
                    allInSort[j] = temp;
                }
            }
        }
        return allInSort;
    }

    //-----------------------------------//

    //----------- STORE BY ADD ----------//

    public void aroundMeStore(Address address, String type){
        // strategy 5 : based on stores around client
        Store[] allInSort = setStoreType(type);
        allInSort = sortDistance(allInSort);

        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (Store store : allInSort){
            int distance = Math.abs(store.getAddress().getDistance() - address.getDistance());
            if ( distance <= 5 /*&& isOpen(store)*/){
                //todo : for With less data, it is better for this condition to convert to comment
                System.out.println(print.withSpaceGenerator(store.getName()," in " + store.getAddress().name()+
                        " with distance " + distance,'-'));
            }
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    public Store[] sortDistance(Store[] allInSort){ // sort restaurant base on distance
        for (int i=0;i<allInSort.length-1;i++){
            for (int j=i+1;j<allInSort.length;j++){
                int distance1 = allInSort[i].getAddress().getDistance();
                int distance2 = allInSort[j].getAddress().getDistance();
                if (distance1 > distance2){
                    Store temp = allInSort[i];
                    allInSort[i] = allInSort[j];
                    allInSort[j] = temp;
                }
            }
        }
        return allInSort;
    }

    //-----------------------------------//

    //------------ FOOD BY SCORE --------//

    public void foodSortScore(double condition,Store store){
        //Ascending condition is 1
        // Strategy 8 : based on food score : Ascending
        // Strategy 9 : based on food score : Descending

        Food[] allFood = store.getMenu().getMainFoods().toArray(new Food[0]);
        for (int i=0;i<allFood.length-1;i++){
            for (int j=i+1;j<allFood.length;j++){
                double foodI = store.getPoints().scoreOf(allFood[i].nameGenerator());
                if ( condition * foodI > condition * store.getPoints().scoreOf(allFood[j].nameGenerator())){
                    Food temp = allFood[i];
                    allFood[i] = allFood[j];
                    allFood[j] = temp;
                }
            }
        }

        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (Food food : allFood){
            double price = food.getBasePrice()*(1+store.getStoreType().getExpensivePercentage());
            double score = store.getPoints().scoreOf(food.nameGenerator());
            System.out.println(print.withSpaceGenerator(food.nameGenerator(),
                    Double.toString(score)+" | "+Double.toString(price),'-'));
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    //-----------------------------------//

    //--------- GIVE ALL BEST ----------//

    public void allBestFood(String name,String type) { // get all restaurant and coffeeShops that have this food
        Store[] all = setStoreType(type);
        for (int i=0;i< all.length-1;i++){
            for (int j=i+1;j< all.length;j++){
                if (all[i].getPoints().scoreOf(name) < all[j].getPoints().scoreOf(name)){
                    Store temp = all[i];
                    all[i] = all[j];
                    all[j] = temp;
                }
            }
        }
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (Store store : all){
            System.out.println(print.withSpaceGenerator(store.getName(),
                    Double.toString(store.getPoints().scoreOf(name)),'-'));
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    //-----------------------------------//

    //------- FOOD BY NUTRITIONAL -------//

    public void foodByNutritional(double condition,Store store){
        // Strategy 6 : Based on Food nutritional : Ascending
        // Strategy 7 : Based on Food nutritional : Descending
        Food[] allFood = store.getMenu().getMainFoods().toArray(new Food[0]);
        for (int i=0;i<allFood.length-1;i++){
            for (int j=i+1;j<allFood.length;j++){
                double foodI = allFood[i].getNutritionalValue();
                if (condition * foodI > condition * allFood[j].getNutritionalValue()){
                    Food temp = allFood[i];
                    allFood[i] = allFood[j];
                    allFood[j] = temp;
                }
            }
        }
        ColdDrinks[] all = coldNutritional(store,condition);

        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (Food food : allFood){
            double price = food.getBasePrice()*(1+store.getStoreType().getExpensivePercentage());
            System.out.println(print.withSpaceGenerator(food.nameGenerator(),
                    Double.toString(food.getNutritionalValue())+"|"+Double.toString(price),'-'));
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    private ColdDrinks[] coldNutritional(Store store,double condition){
        ColdDrinks[] allFood = store.getMenu().getColdDrinks().toArray(new ColdDrinks[0]);
        for (int i=0;i<allFood.length-1;i++){
            for (int j=i+1;j<allFood.length;j++){
                double foodI = allFood[i].getNutritionalValue();
                if (condition * foodI > condition * allFood[j].getNutritionalValue()){
                    ColdDrinks temp = allFood[i];
                    allFood[i] = allFood[j];
                    allFood[j] = temp;
                }
            }
        }
        return allFood;
    }

    //-----------------------------------//

    //--------- ORDER BY STATE ----------//

    public Order[] orderState(String state){
        // Strategy 14 : Based on Order state
        state = state.toUpperCase();
        ArrayList<Order> allInSort = admin.getOrders();
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (int i=0;i<allInSort.size();i++){
            if (allInSort.get(i).getOrderSituation().equals(OrderSituation.valueOf(state))){
                System.out.println(print.withSpaceGenerator(Integer.toString(i+1) + "order " +
                                allInSort.get(i).getUser().toString(),
                        allInSort.get(i).getOrderSituation().name(),'-'));
            }
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        return allInSort.toArray(new Order[0]);
    }

    //-----------------------------------//

    //------- ALL ORDER FOR ADMIN -------//

    public void allOrders(){
        Order[] all = admin.getOrders().toArray(new Order[0]);
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (int i=0;i<all.length;i++){
            System.out.println(print.withSpaceGenerator(Integer.toString(i+1)+"- "+
                    all[i].getUser().getPhoneNumber(),all[i].getOrderSituation().name(),'-'));
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    //-----------------------------------//

    //----- STORE WITH MORE VARIETY -----//

    public void moreVariety(String meal,String type){
        // Strategy 15 : Based on Restaurant variety : Ascending
        // Strategy 16 : Based on CoffeeShop variety : Ascending
        Store[] allInSort = setStoreType(type);
        for (int i=0;i< allInSort.length-1;i++){
            for (int j=i+1;j<allInSort.length;j++){
                int numI = Integer.parseInt(mealVariety(meal,allInSort[i]));
                int numJ = Integer.parseInt(mealVariety(meal,allInSort[j]));
                if (numI < numJ) {
                    Store temp = allInSort[i];
                    allInSort[i] = allInSort[j];
                    allInSort[j] = temp;
                }
            }
        }
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (Store store : allInSort){
            String variety = mealVariety(meal,store);
            if (!variety.equals("0") /*&& isOpen(store)*/) {
                //todo : for With less data, it is better for this condition to convert to comment
                System.out.println(print.withSpaceGenerator(store.getName(), variety, '-'));
            }
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    public String mealVariety(String meal,Store store){
        switch (meal) {
            case "food":
                return Integer.toString(store.getMenu().getMainFoods().size());
            case "drink":
                int variety = store.getMenu().getColdDrinks().size();
                return Integer.toString(store.getMenu().getHotDrinks().size() + variety);
            case "accompaniment":
                return Integer.toString(store.getMenu().getAccompaniments().size());
            default:
                return "0";
        }
    }

    //-----------------------------------//

    //-------- STORE BY TYPE ------------//

    public void priceType(String type,String priceType){
        // Strategy 17 : Based on Restaurant type
        // Strategy 18 : Based on CoffeeShops type
        Store[] allInSort = setStoreType(type);
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (Store store : allInSort){
            if ( store.getStoreType().equals( StoreType.valueOf(priceType.toUpperCase())) && isOpen(store) ){
                System.out.println(print.withSpaceGenerator(store.getName(),store.getAddress().name(),'-'));
            }
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    //-----------------------------------//

    //----- STORE BY COMMENT CONTENT ----//

    public void commentsContent(String type){
        // Strategy 19 : Based on Restaurant Comment contents
        // Strategy 20 : Based on CoffeeShop Comment contents
        Store[] allInSort = setStoreType(type);
        Map<Store,Double> content = scoreComment(allInSort);

        for (int i=0;i<allInSort.length-1;i++){
            for (int j=i+1;j<allInSort.length;j++){
                if ( content.get(allInSort[i])<content.get(allInSort[j]) ){
                    Store temp = allInSort[i];
                    allInSort[i] = allInSort[j];
                    allInSort[j] = temp;
                }
            }
        }
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (Store store : allInSort){
            //if (isOpen(store)) {
            //todo : for With less data, it is better for this condition to convert to comment
            String num = String.format(Locale.ENGLISH, "%.2f", content.get(store));
            System.out.println(print.withSpaceGenerator(store.getName(), num, '-'));
            //}
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    private Map<Store,Double> scoreComment(Store[] allInSort){
        Map<Store,Double> scores = new HashMap<Store, Double>();
        for (Store store : allInSort){
            scores.put(store,whatInComment(store));
        }
        return scores;
    }

    private double whatInComment(Store store){
        double scoreComment = 0;
        for (String comment : store.getComments()){
            scoreComment += goodComment(comment) + badComment(comment);
        }
        return (scoreComment/store.getComments().size());
    }

    private double goodComment(String comment){
        comment = comment.toLowerCase();
        double point = 0;
        point += goodChange(comment,"good","not good");
        point += goodChange(comment,"delicious","not delicious");
        point += goodChange(comment,"fantastic","not fantastic");
        point += goodChange(comment,"tasty","not tasty");
        point += goodChange(comment,"yummy","not yummy");
        return point;
    }

    private double badComment(String comment){
        comment = comment.toLowerCase();
        double point = 0;
        point += badChange(comment,"bad");
        point += badChange(comment,"stale");
        point += badChange(comment,"not tasty");
        point += badChange(comment,"inedible");
        point += badChange(comment,"not good");
        point += badChange(comment,"not yummy");
        return point;
    }

    private double goodChange(String comment,String good,String bad){
        if(comment.contains(good) && !comment.contains(bad)){
            return 1;
        }
        return 0;
    }

    private double badChange(String comment,String bad){
        if (comment.contains(bad)){
            return -1;
        }
        return 0;
    }

    //-----------------------------------//

    //---------- TIME CHECK -------------//

    private boolean isOpen(Store store){
        return store.getStartWorkTime().isBefore(LocalTime.now()) && LocalTime.now().isBefore(store.getEndWorkTime());
    }

    //-----------------------------------//

    //-------- GET DESIRED CHOOSE -------//

    public void whatFood(String name,Order order){
        for (Food food : order.getStore().getMenu().getMainFoods()){
            if (food.nameGenerator().equalsIgnoreCase(name)){
                order.addFood(food);
                return;
            }
        }
        for (Accompaniment accompaniment : order.getStore().getMenu().getAccompaniments()){
            if (accompaniment.nameGenerator().equalsIgnoreCase(name)){
                order.addAccompaniment(accompaniment);
                return;
            }
        }
        whatDrink(name,order);
    }

    public void whatDrink(String name,Order order){
        for (ColdDrinks coldDrink : ColdDrinks.values()){
            if (coldDrink.nameGenerator().equalsIgnoreCase(name)){
                order.addColdDrink(coldDrink);
                return;
            }
        }
        for (HotDrink hotDrink : order.getStore().getMenu().getHotDrinks()){
            if (hotDrink.nameGenerator().equalsIgnoreCase(name)){
                order.addHotDrink(hotDrink);
                return;
            }
        }
    }

    //-----------------------------------//

    //------------ SET METHODS ----------//

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setPrint() {
        print = new Document();
    }

    //-----------------------------------//

}
