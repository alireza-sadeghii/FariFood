package ir.ac.kntu.stores;

public enum StoreType {
    ECONOMY(-0.05),NORMAL(0),LUXURY(0.1); // economy -5%
                                                                                               // luxury +10%

    private final double expensivePercentage;

    StoreType(double expensivePercentage){
        this.expensivePercentage = expensivePercentage;
    }

    public double getExpensivePercentage() {
        return expensivePercentage;
    }
}
