package ir.ac.kntu.delivery;

public enum DeliverySalaryType {
    BY_ORDER(12000),BY_HOUR(40000);

    private final double salary;

    DeliverySalaryType(double salary){
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

}
