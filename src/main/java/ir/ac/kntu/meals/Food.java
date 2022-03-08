package ir.ac.kntu.meals;

import java.util.ArrayList;

public enum Food {
    //---------- PASTA & NOODLE -----------//
    PASTA_ALFREDO(30,1200,89500),
    PASTA_LOUISIANA(30,1125,99500),
    PASTA_FRANCHISE(30,1510,84500),
    VEGETABLE_NOODLE(20,179,119500),
    BEEF_NOODLE(20,198,170000),
    LASAGNA(30,135,110000),
    //-------------------------------------//

    //------------ SUSHI ------------------//
    SESAME_MEAT(30,375,130000),
    FRIED_RICE(30,215,70000),
    LEMON_SHRIMP(30,276,180000),
    //-------------------------------------//

    //------------ PIZZA ------------------//
    CHICKEN_MASALA_PIZZA(25,307,60000),
    MARGHERITA_PIZZA(25,1200,60000),
    BACON_PIZZA(25,276.4,62500),
    PEPPERONI_PIZZA(25,494,59000),
    MEAT_AND_MUSHROOM_PIZZA(25,469.3,77000),
    MUSHROOM_PIZZA(25,442,66000),
    VEGETABLE_PIZZA(25,379,64000),
    //-------------------------------------//

    //------------ SANDWICH -----------------//
    CHEESE_BURGER(20,303,56000),
    HOT_DOG(20,290,46000),
    CHICKEN_BURGER(20,283,47000),
    VEGETABLE_PANINI(20,536,59000),
    CHICKEN_PANINI(20,706,59000),
    //---------------------------------------//

    //------------ FRIED --------------------//
    CHICKEN_THREE_PIECES(30,1130,72000),
    CHICKEN_FIVE_PIECES(30,1883,106000),
    FRIED_FILLETS(30,460,69000),
    FRIED_WINGS(30,640,57000),
    //---------------------------------------//

    //----------- IRANIAN -------------------//
    KABAB_KOBIDEH(30,536,160000),
    JOJEH_KABAB(30,480,95000),
    SHISHLIK(30,760,205000),
    CHENJEH(30,700,145000),
    KABAB_BAKHTIARY(30,484,75000),
    ZERESHK_POLO(30,478,55000),
    MIRZAGHASEMI(35,290,33000);
    //----------------------------------------//



    private final int preparationTime; // in base minute
    private final double nutritionalValue;
    private final double basePrice;

    Food(int preparationTime, double nutritionalValue, double basePrice) {
        this.preparationTime = preparationTime;
        this.nutritionalValue = nutritionalValue;
        this.basePrice = basePrice;
    }

    public String  nameGenerator(){
        String [] name = this.name().split("_");
        String foodName = "";
        for (int i=0;i<name.length;i++){
            foodName += name[i]+" ";
        }
        return foodName.substring(0,foodName.length()-1);
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getNutritionalValue() {
        return nutritionalValue;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

}
