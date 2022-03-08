package ir.ac.kntu.stores;

import ir.ac.kntu.menus.RestaurantMenu;
import ir.ac.kntu.menus.Score;
import ir.ac.kntu.meals.Accompaniment;
import ir.ac.kntu.meals.ColdDrinks;
import ir.ac.kntu.meals.Food;
import ir.ac.kntu.meals.HotDrink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Points {
    private Store store;

    private Map<Food,Double> foodPoints = new HashMap<Food,Double>();
    private Map<Food,ArrayList<String>> foodComments = new HashMap<Food,ArrayList<String>>();

    private Map<ColdDrinks,Double> coldPoints = new HashMap<ColdDrinks,Double>();
    private Map<ColdDrinks,ArrayList<String>> coldComments = new HashMap<ColdDrinks,ArrayList<String>>();

    private Map<HotDrink,Double> hotPoints = new HashMap<HotDrink,Double>();
    private Map<HotDrink,ArrayList<String>> hotComments = new HashMap<HotDrink,ArrayList<String>>();

    private Map<Accompaniment,Double> accompanimentPoints = new HashMap<Accompaniment,Double>();
    private Map<Accompaniment,ArrayList<String>> accompanimentComments = new HashMap<Accompaniment,ArrayList<String>>();


    public Points(Store store) { // Constructor
        this.store = store;
        RestaurantMenu menu = store.getMenu();

        setAccompanimentPoints(menu.getAccompaniments());
        setAccompanimentComments(menu.getAccompaniments());

        setFoodPoints(menu.getMainFoods());
        setFoodComments(menu.getMainFoods());

        setColdPoints(menu.getColdDrinks());
        setColdComments(menu.getColdDrinks());

        setHotPoints(menu.getHotDrinks());
        setHotComments(menu.getHotDrinks());
    }

    private boolean identifyFood(String name){
        for (Food food : Food.values()){
            if (food.name().equals(name)){
                return true;
            }
        }
        return false;
    }

    private boolean identifyCold(String name){
        for (ColdDrinks coldDrink : ColdDrinks.values()){
            if (coldDrink.name().equals(name)){
                return true;
            }
        }
        return false;
    }

    private boolean identifyHot(String name){
        for (HotDrink hotDrink : HotDrink.values()){
            if (hotDrink.name().equals(name)){
                return true;
            }
        }
        return false;
    }

    private boolean identifyAccompaniment(String name){
        for (Accompaniment accompaniment : Accompaniment.values()){
            if (accompaniment.name().equals(name)){
                return  true;
            }
        }
        return false;
    }

    //-------- SCORE METHODS ------------//

    public void scoreTo(String name, Score score){ // give score to foods
        name = name.replace(' ','_').toUpperCase();
        if (!isExist(name)) {
            return;
        }
        if (identifyFood(name)){
            foodPoints.put(Food.valueOf(name),foodPoints.get(Food.valueOf(name))+score.getScoreChanger() );
        } else if(identifyCold(name)){
            coldPoints.put(ColdDrinks.valueOf(name),coldPoints.get(ColdDrinks.valueOf(name))+ score.getScoreChanger() );
        } else if (identifyHot(name)){
            hotPoints.put(HotDrink.valueOf(name),hotPoints.get(HotDrink.valueOf(name)) + score.getScoreChanger() );
        } else if (identifyAccompaniment(name)){
            accompanimentPoints.put(Accompaniment.valueOf(name),accompanimentPoints.get(Accompaniment.valueOf(name))
                    + score.getScoreChanger());
        }
    }

    public double scoreOf(String name){ // see score of foods
        name = name.toUpperCase().replace(' ','_');
        if (!isExist(name)){
            return 0;
        }
        if (identifyFood(name)){
            return foodPoints.get(Food.valueOf(name));
        } else if (identifyCold(name)){
            return coldPoints.get(ColdDrinks.valueOf(name));
        } else if (identifyHot(name)){
            return hotPoints.get(HotDrink.valueOf(name));
        } else if (identifyAccompaniment(name)){
            return accompanimentPoints.get(Accompaniment.valueOf(name));
        }
        return 0.0;
    }

    //-----------------------------------//

    public boolean isExist(String name){
        if (identifyFood(name)){
            return foodComments.containsKey(Food.valueOf(name)) && foodPoints.containsKey(Food.valueOf(name));
        }
        if (identifyCold(name)){
            return coldComments.containsKey(ColdDrinks.valueOf(name))&&coldPoints.containsKey(ColdDrinks.valueOf(name));
        }
        if (identifyHot(name)){
            return hotComments.containsKey(HotDrink.valueOf(name)) && hotPoints.containsKey(HotDrink.valueOf(name));
        }
        return accompanimentComments.containsKey(Accompaniment.valueOf(name)) &&
                accompanimentPoints.containsKey(Accompaniment.valueOf(name));
    }

    //-------- COMMENTS METHODS ---------//

    public void commentTo(String name,String comment){
        name = name.toUpperCase().replace(' ','_');
        arrayOfFood(name).add(comment);
    }

    public void commentOf(String name){
        name = name.toUpperCase().replace(' ','_');
        ArrayList<String> comments = arrayOfFood(name);
        commentPrint(comments);
    }

    public ArrayList<String> arrayOfFood(String name){
        if(identifyFood(name)){
            return foodComments.get(Food.valueOf(name));
        } else if (identifyCold(name)){
            return coldComments.get(ColdDrinks.valueOf(name));
        } else if (identifyHot(name)){
            return hotComments.get(HotDrink.valueOf(name));
        } else if (identifyAccompaniment(name)){
            return accompanimentComments.get(Accompaniment.valueOf(name));
        }
        return null;
    }

    public void commentPrint(ArrayList<String> comments){
        System.out.println();
        for (String comment : comments){
            String[] opinion = comment.split("/=/");
            System.out.println(numSpoiler(opinion[0]).trim() + "say :");
            System.out.println(opinion[1].trim());
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
    }

    public String numSpoiler(String number){ // destroyed three number of user phone number
        String spoil = number.substring(0,6);
        spoil += "***";
        spoil += number.substring(6);
        return spoil;
    }

    //-----------------------------------//


    //------------ SET METHODS ----------//

    public void setStore(Store store) {
        this.store = store;
    }

    public void setAccompanimentPoints(ArrayList<Accompaniment> accompaniments) {
        for (Accompaniment accompaniment : accompaniments){
            this.accompanimentPoints.put(accompaniment,5.0);
        }
    }

    public void setColdPoints(ArrayList<ColdDrinks> coldDrinks) {
        for (ColdDrinks coldDrink : coldDrinks){
            this.coldPoints.put(coldDrink,5.0);
        }
    }

    public void setFoodPoints(ArrayList<Food> foods) {
        for (Food food : foods){
            this.foodPoints.put(food,5.0);
        }
    }

    public void setHotPoints(ArrayList<HotDrink> hotDrinks) {
        for (HotDrink hotDrink : hotDrinks){
            this.hotPoints.put(hotDrink,5.0);
        }
    }

    public void setHotComments(ArrayList<HotDrink> hotDrinks) {
        for (HotDrink hotDrink : hotDrinks){
            this.hotComments.put(hotDrink,new ArrayList<>());
        }
    }

    public void setFoodComments(ArrayList<Food> foods) {
        for (Food food : foods){
            this.foodComments.put(food,new ArrayList<>());
        }
    }

    public void setColdComments(ArrayList<ColdDrinks> coldDrinks) {
        for (ColdDrinks coldDrink : coldDrinks){
            this.coldComments.put(coldDrink,new ArrayList<>());
        }
    }

    public void setAccompanimentComments(ArrayList<Accompaniment> accompaniments) {
        for (Accompaniment accompaniment : accompaniments){
            this.accompanimentComments.put(accompaniment,new ArrayList<>());
        }
    }

    //-----------------------------------//

    //------------ GET METHODS ----------//

    public Store getStore() {
        return store;
    }

    //-----------------------------------//

}
