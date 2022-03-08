package ir.ac.kntu.menus;

public enum Score {
    ZERO(-0.5),ONE(-0.4),TWO(-0.3),THREE(-0.2),FOUR(-0.1),
    FIVE(0),SIX(0.1),SEVEN(0.2),EIGHT(0.3),NINE(0.4),
    TEN(0.5);

    private final double scoreChanger;

    Score(double scoreChanger){
        this.scoreChanger = scoreChanger;
    }

    public double getScoreChanger() {
        return scoreChanger;
    }
}
