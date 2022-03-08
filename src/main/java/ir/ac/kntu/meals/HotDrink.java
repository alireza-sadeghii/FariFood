package ir.ac.kntu.meals;

public enum HotDrink {

    //-------- COFFEE --------------//
    LATTE(5,100,20000),
    HOT_CHOCOLATE(6,194,23000),
    MOCHA(5,129,22000),
    COFFEE(5,0,12000),
    TURKISH_COFFEE(5,18,18000),
    CAPPUCCINO(7,74,21000),
    CARAMEL_MACCHIATO(7,140,20000),
    WHITE_CHOCOLATE(5,916,24000),
    ESPRESSO(4,9,14000),
    NESCAFE(3,90,18000),
    //-------------------------------//

    //-------- DRINK ----------------//
    HOT_MILK(6,146,13000),
    HONEY_MILK(6,185.8,18000),
    //-------------------------------//

    //-------- HERBAL TEA -----------//
    ORANGE_BLOSSOM_TEA(10,90,22000),
    SOUR_TEA(10,70,21000),
    BORAGE_TEA(10,21,21000),
    LEMON_TEA(10,5,23000),

    //-------- TEA ------------------//
    BLACK_TEA(7,2,17000),
    MASALA_TEA(9,105,25000),
    GREEN_TEA(9,2.45,21000);
    //-------------------------------//

    private final int preparationTime; // in base minute
    private final double nutritionalValue;
    private final double basePrice;

    HotDrink(int preparationTime, double nutritionalValue, double basePrice) {
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
