package ir.ac.kntu.menus;

import ir.ac.kntu.delivery.Address;
import ir.ac.kntu.member.Admin;
import ir.ac.kntu.member.User;
import ir.ac.kntu.order.Order;

import java.util.Scanner;

public class UserAppMenu {
    private Admin admin;
    private Scanner scanner;

    public UserAppMenu(Admin admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void showMenu(){
        System.out.println("1- See user order History");
        System.out.println("2- See user feedBack history");
        System.out.println("3- See user lovely food");
        System.out.println("4- Create new user");
        System.out.println("5- Exit");
        String choose = scanner.nextLine();
        switchMenu(choose);
    }

    private void switchMenu(String choose){
        switch (choose){
            case "1":
                watchOrders();
                break;
            case "2":
                watchFeedBack();
                break;
            case "3":
                lovelyFood();
                break;
            case "4":
                createNewUser();
                break;
            case "5":
                return;
            default:
                break;
        }
    }

    private void lovelyFood(){
        User newUser = whatUser();
        if (newUser==null){
            System.out.println("No custom has been created yet");
            return;
        }
        newUser.seeLovelyFood();
    }

    private void createNewUser(){
        System.out.println("Enter user phone number :");
        String phone = scanner.nextLine();
        System.out.println("Enter user address :");
        String add = scanner.nextLine().toUpperCase().replace(' ','_');
        User newUser = new User(phone, Address.valueOf(add));
    }

    private void watchFeedBack(){
        User find = whatUser();
        if (find==null){
            return;
        }
        find.seeFeedBackHistory();
    }

    private void watchOrders(){
        User find = whatUser();
        if (find==null) {
            System.out.println("There is no user with this profile");
            return;
        }
        for (Order order : find.getOrdersHistory()){
            order.currentPrint();
        }
    }

    private User whatUser(){
        System.out.println("Enter user phone number :");
        String phone = scanner.nextLine();
        System.out.println("Enter user address :");
        String add = scanner.nextLine().toUpperCase();
        User newUser = new User(phone,Address.valueOf(add));
        for (User user : admin.getUsers()){
            if (user.equals(newUser)){
                return user;
            }
        }
        return null;
    }


    //------------ GET METHODS ----------//


    public Admin getAdmin() {
        return admin;
    }

    //-----------------------------------//


}
