package ir.ac.kntu.menus;

import ir.ac.kntu.stores.StoreType;
import ir.ac.kntu.meals.Accompaniment;
import ir.ac.kntu.meals.ColdDrinks;
import ir.ac.kntu.meals.Food;
import ir.ac.kntu.meals.HotDrink;

import java.util.ArrayList;

public class RestaurantMenu {
    private StoreType storeType;
    private ArrayList<Food> mainFoods = new ArrayList<>();
    private ArrayList<ColdDrinks> coldDrinks = new ArrayList<>();
    private ArrayList<HotDrink> hotDrinks = new ArrayList<>();
    private ArrayList<Accompaniment> accompaniments = new ArrayList<>();

    public RestaurantMenu(ArrayList<Food> mainFoods, ArrayList<ColdDrinks> coldDrinks, ArrayList<HotDrink> hotDrinks,
                          ArrayList<Accompaniment> accompaniments) {
        this.mainFoods = mainFoods;
        this.coldDrinks = coldDrinks;
        this.hotDrinks = hotDrinks;
        this.accompaniments = accompaniments;
    }

    public RestaurantMenu(){

    }

    public void menuPrinter(){
        Document menu = new Document(storeType,mainFoods,coldDrinks,hotDrinks,accompaniments);
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║"+printSpace(24)+"Main Foods"+printSpace(29)+"║");
        menu.foodPrinter();
        System.out.println("║"+printSpace(63)+"║"); // insert empty line
        System.out.println("║"+printSpace(23)+"Accompaniment"+printSpace(27)+"║");
        menu.accompanimentPrinter();
        System.out.println("║"+printSpace(63)+"║");
        System.out.println("║"+printSpace(24)+"Cold Drinks"+printSpace(28)+"║");
        menu.coldDrinkPrinter();
        System.out.println("║"+printSpace(63)+"║");
        System.out.println("║"+printSpace(24)+"Hot Drinks"+printSpace(29)+"║");
        menu.hotDrinkPrinter();
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }

    public String printSpace(int number){ // print space for above method
        String space = "";
        for (int i=0;i<number;i++){
            space += " ";
        }
        return space;
    }

    public void addToMainFoods(Food food){ // for add new food to restaurant menu
        if (!this.mainFoods.contains(food)){
            this.mainFoods.add(food);
        }
    }

    public void addToColdDrinks(ColdDrinks coldDrinks){ // for add new cold drink to restaurant menu
        if (!this.coldDrinks.contains(coldDrinks)){
            this.coldDrinks.add(coldDrinks);
        }
    }

    public void addToHotDrinks(HotDrink hotDrink){ // for add new hot drink to restaurant menu
        if (!this.hotDrinks.contains(hotDrink)){
            this.hotDrinks.add(hotDrink);
        }
    }

    public void addToAccompaniments(Accompaniment accompaniment){ // for add new accompaniment to restaurant menu
        if (!this.accompaniments.contains(accompaniment)){
            this.accompaniments.add(accompaniment);
        }
    }

    //----------- SET METHODS -----------//

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    //-----------------------------------//

    //----------- GET METHODS------------//

    public ArrayList<Accompaniment> getAccompaniments() {
        return accompaniments;
    }

    public ArrayList<ColdDrinks> getColdDrinks() {
        return coldDrinks;
    }

    public ArrayList<Food> getMainFoods() {
        return mainFoods;
    }

    public ArrayList<HotDrink> getHotDrinks() {
        return hotDrinks;
    }

    //-----------------------------------//

}
