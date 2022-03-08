package ir.ac.kntu.delivery;

import ir.ac.kntu.menus.Score;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.stores.Store;

import java.util.ArrayList;
import java.util.Objects;

public class Delivery {
    private Vehicle vehicle;
    private DeliverySalaryType salaryType;
    private ArrayList<Order> orderDelivered = new ArrayList<Order>();
    private Available available;
    private double score = 5;
    private ArrayList<Store> employers = new ArrayList<Store>();
    private int salary;
    private ArrayList<Day> attendance = new ArrayList<>();

    public Delivery(Vehicle vehicle, DeliverySalaryType salaryType,ArrayList<Store> employers,
                    ArrayList<Day> attendance) {

        this.vehicle = vehicle;
        this.salaryType = salaryType;
        this.employers = employers;
        this.salary = (int)salaryType.getSalary();
        this.attendance = attendance;
    }

    public Delivery(){

    }

    public void changeVehicle(String vehicle){
        if (vehicle.length()!=0){
            setVehicle(Vehicle.valueOf(vehicle.toUpperCase()));
        }
    }

    public void changeSalaryType(String type){
        if (type.length()!=0){
            setSalaryType(DeliverySalaryType.valueOf(type.toUpperCase().replace(' ','_')));
        }
    }

    public void changeSalary(String salary){
        setSalary(Integer.parseInt(salary));
    }

    public void changeDays(ArrayList<Day> newDays){
        setAttendance(newDays);
    }

    public void deliveryChangeScore(Score score){
        if (this.score+score.getScoreChanger() > 0) {
            setScore(this.score + score.getScoreChanger());
        }
    }

    public void changeAvailable(Available available){
        setAvailable(available);
    }

    public boolean isAvailable(){
        if (this.available.equals(Available.FREE)){
            return true;
        }
        return false;
    }

    public void addEmployers(Store store){
        this.employers.add(store);
    }

    //---------- SET METHODS ------------//

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setAttendance(ArrayList<Day> attendance) {
        this.attendance = attendance;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSalaryType(DeliverySalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public void setScore(double score) {
        this.score = score;
    }

    //-----------------------------------//

    //---------- GET METHODS ------------//

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ArrayList<Order> getOrderDelivered() {
        return orderDelivered;
    }

    public DeliverySalaryType getSalaryType() {
        return salaryType;
    }

    //-----------------------------------//


    @Override
    public String toString() {
        return "vehicle = " + vehicle +
                ", salaryType = " + salaryType +
                ", score = " + score +
                ", employers = " + employers +
                ", salary = " + salary +
                ", attendance = " + attendance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o==null || getClass()!=o.getClass()) {
            return false;
        }
        Delivery delivery = (Delivery) o;
        return getVehicle() == delivery.getVehicle() && getSalaryType() == delivery.getSalaryType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVehicle(), getSalaryType());
    }
}
