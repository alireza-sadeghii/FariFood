package ir.ac.kntu.meals;

public enum ColdDrinks {

    //------------------- glace --------------//
    CAFE_GLACE(5, 217,28000),
    CHOCOLATE_GLACE(5,283,28000),
    CARAMEL_GLACE(5,211,27000),
    //----------------------------------------//

    //------------------ iced ----------------//
    ICED_LATTE(5, 130,30000 ),
    ICED_MOCHA(5, 354, 30000),
    ICED_TEA(10,2.4,20000),
    //----------------------------------------//

    //------------------ shake ---------------//
    CHOCOLATE_MILKSHAKE(6, 541,32000 ),
    COFFEE_SHAKE(6, 530,30000 ),
    VANILLA_MILKSHAKE(6, 351,28000 ),
    STRAWBERRY_MILKSHAKE(6, 425,29000 ),
    NUTELLA_MILKSHAKE(6, 820,29000 ),
    //----------------------------------------//

    //------------------ juice ---------------//
    LEMONADE(5, 99, 29000),
    MOJITO(5, 205,32000 ),
    KHAK_E_SHIR(5, 70, 25000),
    APPLE_JUICE(5, 114, 30000),
    CARROT_JUICE(5, 94, 30000),
    MELON_JUICE(5, 81,30000 ),
    WATERMELON_JUICE(5, 80, 25000),
    ORANGE_JUICE(5, 112, 35000),
    //-----------------------------------------//

    //--------------- smoothie ----------------//
    BERRY_SMOOTHIE(8, 250,29000 ),
    CHERRY_SMOOTHIE(8, 456,28000),
    PINACOLADA(7, 500,31000),
    //-----------------------------------------//

    //----------------- detox -----------------//
    GRAPEFRUIT_DETOX(0, 138,35000 ),
    APPLE_DETOX(0, 30, 27000),
    //-----------------------------------------//

    //----------------- frappe ----------------//
    CHOCOLATE_FRAPPE(10, 232,31000 ),
    COFFEE_FRAPPE(10, 387,31000 ),
    //-----------------------------------------//

    //----------------- drink -----------------//
    FANTA(0, 137,6000),
    COKA(0, 140,6000),
    SODA(0, 0, 6000);
    //-----------------------------------------//

    private final int preparationTime; // in base minute
    private final double nutritionalValue;
    private final double basePrice;


    ColdDrinks(int preparationTime, double nutritionalValue, double basePrice) {
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
