package ir.ac.kntu.meals;

public enum Accompaniment {
    //----------- SALAD -------------//
    CAESAR_SALAD(10,94,65000),
    GREEK_SALAD(10,106,60000),
    HAWAIIAN_SALAD(10,150,59000),
    COLESLAW(10,152,20000),
    SHIRAZI_SALAD(5,81,11000),
    ARMENIAN_SALAD(10,96,48000),
    SPINACH_SALAD(10,310,80000),
    //-------------------------------//

    //---------- DESSERT ------------//
    CHOCOLATE_BROWNIE(7,466,52000),
    FRUIT_TART(6,170,34000),
    CHEESECAKE(4,321,20000),
    SHOLEHZARD(3,250,27000),
    TIRAMISU(5,600,28000),
    CHOCOLATE_MOUSSE(4,450,26000),
    BANANA_PUDDING(4,101,14000),
    ICE_CREAM(10,207,35000),
    JELLY(2,62,20000),
    //-------------------------------//

    //-- APPETIZER & Accompaniment --//
    SOUP(15,62,22000),
    PICKLE(5,11,22000),
    YOGURT(5,96,24000),
    FRENCH_FRIES(15,312,33000),
    FRIED_MUSHROOMS(15,440,30000),
    GARLIC_BREAD(15,350,40000),
    OLIVE(5,115,30000);
    //-------------------------------//

    private final int preparationTime; // in base minute
    private final double nutritionalValue;
    private final double basePrice;

    Accompaniment(int preparationTime, double nutritionalValue, double basePrice) {
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
