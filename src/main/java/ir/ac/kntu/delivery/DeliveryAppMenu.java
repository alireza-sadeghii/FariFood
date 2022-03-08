package ir.ac.kntu.delivery;

import ir.ac.kntu.member.Admin;
import ir.ac.kntu.order.Order;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryAppMenu {
    private Admin admin;
    private Scanner scanner;

    public DeliveryAppMenu(Admin admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void showMenu(){
        System.out.println("1- Watch deliverers information");
        System.out.println("2- Make new deliverer");
        System.out.println("3- Change deliverer information");
        System.out.println("4- Exit");
        String choose = scanner.nextLine();
        menuSwitch(choose);
    }

    private void menuSwitch(String choose){
        switch (choose){
            case "1":
                showDeliverer();
                break;
            case"2":
                makeDeliverer();
                break;
            case"3":
                changeInfo();
                break;
            case"4":
                return;
            default:
                break;
        }
    }

    private void changeInfo(){
        Delivery forChange = findDeliverer();
        System.out.println("Enter new content to change any of the fields");
        System.out.println("If you do not intend to change the field, leave it blank");
        System.out.println("Enter new vehicle :");
        forChange.changeVehicle(scanner.nextLine());
        System.out.println("Enter new salary type :");
        forChange.changeSalaryType(scanner.nextLine());
        System.out.println("Enter new salary :");
        forChange.changeSalary(scanner.nextLine());
        System.out.println("Enter new number of day attendance:");
        String num = scanner.nextLine();
        if (num.length()!=0) {
            forChange.changeDays(giveDays(Integer.parseInt(num)));
        }
    }

    private Delivery findDeliverer(){
        Delivery find = new Delivery();
        System.out.println("Enter vehicle type :");
        find.setVehicle(Vehicle.valueOf(scanner.nextLine().toUpperCase()));
        System.out.println("Enter salary type :");
        find.setSalaryType(DeliverySalaryType.valueOf(scanner.nextLine().toUpperCase().replace(' ','_')));
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (int i=0;i<admin.getDeliverers().size();i++){
            if (find.equals(admin.getDeliverers().get(i))){
                System.out.println((i+1)+"- "+admin.getDeliverers().get(i).toString());
            }
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Enter number of desired deliverer :");
        int id = Integer.parseInt(scanner.nextLine());
        return admin.getDeliverers().get(id-1);
    }

    private void makeDeliverer(){
        Delivery newDelivery = new Delivery();
        System.out.println("Enter vehicle type :");
        newDelivery.setVehicle(Vehicle.valueOf(scanner.nextLine().toUpperCase()));
        System.out.println("Enter type of salary :");
        newDelivery.setSalaryType(DeliverySalaryType.valueOf(scanner.nextLine().toUpperCase().replace(' ',
                '_')));
        System.out.println("Enter amount of salary :");
        newDelivery.setSalary(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter number of attendance days :");
        int numDay = Integer.parseInt(scanner.nextLine());
        newDelivery.setAttendance(giveDays(numDay));
        admin.getDeliverers().add(newDelivery);
    }

    private ArrayList<Day> giveDays(int numDay){
        ArrayList<Day> all = new ArrayList<>();
        System.out.println("Enter days :");
        for (int i=0;i<numDay;i++){
            all.add(Day.valueOf(scanner.nextLine().toUpperCase()));
        }
        return all;
    }

    private void showDeliverer(){
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (int i=0;i< admin.getDeliverers().size();i++){
            System.out.println((i+1)+"- "+admin.getDeliverers().get(i).toString());
            System.out.println();
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println("Enter number of deliverer that like to see order history or don't like, don't type anything");
        String ask = scanner.nextLine();
        if (ask.length()!=0){
            ordersPrint(admin.getDeliverers().get(Integer.parseInt(ask)-1));
        }
    }

    private void ordersPrint(Delivery delivery){
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        for (Order order : delivery.getOrderDelivered()){
            System.out.println("order " + order.getStore().getName() + " from "+order.getStore().getAddress()+ " to "+
                    order.getUser().getAddress());
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }



    // ------------ GET METHODS ----------//

    public Admin getAdmin() {
        return admin;
    }

    //-----------------------------------//
}
