package ir.ac.kntu.menus;

import ir.ac.kntu.delivery.Address;
import ir.ac.kntu.delivery.Available;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliveryAppMenu;
import ir.ac.kntu.member.Admin;
import ir.ac.kntu.member.User;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.order.OrderSituation;
import ir.ac.kntu.stores.CoffeeShop;
import ir.ac.kntu.stores.Restaurant;
import ir.ac.kntu.stores.Store;

import java.util.Scanner;

public class AppMenu {
    private Scanner scanner;
    private Admin admin;
    private String storeFilterSetting;
    private String menuFilterSetting;
    private String storeType; //restaurant or coffeeShop
    private Store desiredStore;
    private Sorter sorter;
    private User user;

    public AppMenu(Scanner scanner,Admin admin){
        this.admin = admin;
        this.scanner = scanner;
        setSorter(new Sorter(admin));
    }

    public void runMenu(){
        if (giveAdminInfo()){
            storeFilterSetting();
            menuFilterSetting();
            mainMenu();
        }
    }

    public boolean giveAdminInfo(){
        System.out.println("Please enter your username :");
        String username = scanner.nextLine();
        System.out.println("Please enter your password :");
        String password = scanner.nextLine();
        return admin.adminLogIn(username,password);
    }

    public void userLogOrSign(){
        User newUser;
        System.out.println("Please enter user phone number :");
        String phoneNumber = scanner.nextLine();
        System.out.println("Please enter user address :");
        String address = scanner.nextLine().toUpperCase();
        newUser = isUserExist(phoneNumber,address);
        if (newUser==null){
            newUser = new User(phoneNumber, Address.valueOf(address));
            admin.getUsers().add(newUser);
            setUser(newUser);
        }
        setUser(newUser);
    }

    private User isUserExist(String phoneNumber,String address){
        for (User user : admin.getUsers()){
            if (user.equals( new User(phoneNumber,Address.valueOf(address)) )){
                return user;
            }
        }
        return null;
    }

    public void storeFilterSetting(){ //set setting of basic show
        System.out.println("On what basis do you want restaurants to be displayed?");
        System.out.println("You can change these settings later in the settings menu");
        System.out.println();
        System.out.println("1- Shows stores in descending order based on points");
        System.out.println("2- Shows stores in ascending order based on points");
        System.out.println("3- Shows stores in descending order based on number of comments");
        System.out.println("4- Shows stores in ascending order based on number of comments");
        System.out.println("5- Show me the shops around me");
        System.out.println("6- Show stores with the most variety of food");
        System.out.println("7- Show stores based on content of comments");
        System.out.println("8- Show stores based on store type");
        System.out.println("9- Show stores have better particular food");
        System.out.println();
        String filter = scanner.nextLine();
        setStoreFilterSetting( conditionChecker(1,9,filter) );

    }

    private String conditionChecker(int lowerBound,int upperBound,String choose){
        if (Integer.parseInt(choose) >= lowerBound && Integer.parseInt(choose)<=upperBound){
            return choose;
        }
        return "1";
    }

    public void switchStore(){
        setStoreType(whatStore());
        if (Integer.parseInt(getStoreFilterSetting())<=4){
            switchBasicStoreFilter(storeType);
        }
        switchStoreFilter(storeType);
    }

    private String whatStore(){
        System.out.println("What store are you looking for?");
        System.out.println("- Restaurant");
        System.out.println("- CoffeeShops");
        return scanner.nextLine();
    }

    public void switchBasicStoreFilter(String storeType){
        switch (this.storeFilterSetting){
            case "1":
                sorter.storeSort(-1,storeType,"score");
                break;
            case "2":
                sorter.storeSort(1,storeType,"score");
                break;
            case "3":
                sorter.storeSort(-1,storeType,"comment");
                break;
            case "4":
                sorter.storeSort(1,storeType,"comment");
                break;
            default:
                break;
        }
    }

    public void switchStoreFilter(String storeType){
        switch(this.storeFilterSetting){
            case "5":
                byAdd(storeType);
                break;
            case "6":
                sorter.moreVariety(whatMealImportant(),storeType);
                break;
            case "7":
                sorter.commentsContent(storeType);
                break;
            case "8":
                sorter.priceType(storeType,whichStoreType());
                break;
            case "9":
                System.out.println("Enter your desired food :");
                sorter.allBestFood(scanner.nextLine(),storeType);
                break;
            default:
                break;
        }
    }

    private void byAdd(String storeType){
        System.out.println("Please enter the name of your street : ");
        String add = scanner.nextLine().toUpperCase();
        if (!addException(add)){
            System.out.println("Sorry, the address you requested could not be found.");
            return;
        }
        sorter.aroundMeStore(Address.valueOf(add),storeType);
    }

    private boolean addException(String add){
        Address[] nameAdd = Address.values();
        for (Address address : nameAdd){
            if (address.name().equalsIgnoreCase(add)){
                return true;
            }
        }
        return false;
    }

    private String whatMealImportant(){
        System.out.println("Which meal is more important for diversity?");
        System.out.println("- Food \uD83C\uDF54");
        System.out.println("- Drink \uD83C\uDF79");
        System.out.println("- Accompaniment \uD83E\uDDC1");
        return scanner.nextLine();
    }

    private String whichStoreType(){
        System.out.println("Please enter what kind of store you are looking for : ");
        System.out.println("1- Economy");
        System.out.println("2- Normal");
        System.out.println("3- Luxury");
        return scanner.nextLine();
    }

    public Store chooseStore(){
        System.out.println("Please enter name of desired store :");
        String storeName = scanner.nextLine();
        if (storeName.length()!=0) {
            for (Store store : getDesiredStore()) {
                if (store.getName().equalsIgnoreCase(storeName)) {
                    setDesiredStore(store);
                    return store;
                }
            }
        }
        return null;
    }

    public Store[] getDesiredStore(){
        if (this.storeType.equalsIgnoreCase("restaurant")){
            return admin.getRestaurants().toArray(new Restaurant[0]);
        }
        return admin.getCoffeeShops().toArray(new CoffeeShop[0]);
    }

    public void menuFilterSetting(){ // set setting of menu show
        System.out.println("On what basis do you want menus to be displayed?");
        System.out.println();
        System.out.println("1- Shows foods in descending order based on foods score");
        System.out.println("2- Shows foods in ascending order based on foods score");
        System.out.println("3- Shows foods in descending order based on foods nutrition");
        System.out.println("4- Shows foods in ascending order based on foods nutrition");
        System.out.println("5- Default show");
        System.out.println();
        String filter = scanner.nextLine();
        setMenuFilterSetting(conditionChecker(1,5,filter));
    }

    private void seeStoreMenu(){
        switch(this.menuFilterSetting){
            case "1":
                sorter.foodSortScore(-1,this.desiredStore);
                break;
            case "2":
                sorter.foodSortScore(1,this.desiredStore);
                break;
            case "3":
                sorter.foodByNutritional(-1,this.desiredStore);
                break;
            case"4":
                sorter.foodByNutritional(1,this.desiredStore);
                break;
            default:
                desiredStore.getMenu().menuPrinter();
                break;
        }
    }

    private void makeOrder(){
        System.out.println("Enter the name of the food you want.");
        System.out.println("You can finish your order by writing \"End\"");
        System.out.println("And by writing the word \"order\", see the contents of your order");
        Order newOrder = new Order(desiredStore, OrderSituation.PROCESSING,user);
        String orderName = "start";
        while (!orderName.matches("[Ee][Nn][Dd]")){
            orderName = scanner.nextLine().toUpperCase();
            if (orderName.equalsIgnoreCase("order")){
                newOrder.currentPrint();
            }else{
                sorter.whatFood(orderName,newOrder);
            }
        }
        admin.getOrders().add(newOrder);
        user.getOrdersHistory().add(newOrder);
        newOrder.situationChanger("PROCESSING");
        Delivery available = desiredStore.isDelivererAvailableForStore();
        if (available!=null){
            newOrder.setDeliverer(available);
            available.changeAvailable(Available.INUSE);
            newOrder.situationChanger("SENDING");
        }
        newOrder.orderPrint();
    }

    public void orderMenu(){
        System.out.println("1- Make order");
        System.out.println("2- See all orders and change order situation");
        System.out.println("3- See all orders with particular situation");
        System.out.println("4- Submit comments for delivered orders");
        System.out.println("5- Exit order menu");
        String input = scanner.nextLine();
        input = conditionChecker(1,5,input);
        orderMenuSwitch(input);
    }

    private boolean doYouLikeReorder(){
        System.out.println("Do you like reorder last order?");
        System.out.println("- Yes");
        System.out.println("- No");
        String ask = scanner.nextLine();
        if (ask.equalsIgnoreCase("yes")){
            this.user.reOrderLastOrder();
            admin.getOrders().add(this.user.getOrdersHistory().get(this.user.getOrdersHistory().size()-1));
            return true;
        }
        return false;
    }

    public void orderMenuSwitch(String input){
        switch (input){
            case "1":
                userLogOrSign();
                if (this.user.getOrdersHistory().size()!=0 && doYouLikeReorder()){
                    break;
                }
                switchStore();
                Store desired = chooseStore();
                if (desired==null){
                    break;
                }
                setDesiredStore(this.desiredStore);
                seeStoreMenu();
                makeOrder();
                break;
            case "2":
                sorter.allOrders();
                needChangeSituation();
                break;
            case "3":
                System.out.println("Please enter your desired situation : ");
                sorter.orderState(scanner.nextLine());
                break;
            case "4":
                scoreToOrder();
                break;
            default:
                break;
        }
        mainMenu();
    }

    private void scoreToOrder(){
        Score[] score = Score.values();
        Order[] all = sorter.orderState("delivered");
        if (all.length==0){
            return;
        }
        System.out.println("Enter number of order :");
        String idOrder = scanner.nextLine();
        System.out.println("Please enter your rating for the desired store : [0-10]");
        String storeRate = scanner.nextLine();
        System.out.println("Please enter your comment about store , you canYou can leave it blank");
        String comment = scanner.nextLine();
        this.user.getFeedBackHistory().add(all[Integer.parseInt(idOrder)-1].getStore().getName()+"/=/"+comment
                +"/=/"+storeRate);
        all[Integer.parseInt(idOrder)-1].getStore().getComments().add("/=/"+comment
                +"/=/"+storeRate);
        System.out.println("Please enter your rating restaurant deliverer : [0-10]");
        String deliverRate = scanner.nextLine();
        if (all[Integer.parseInt(idOrder)-1].getDeliverer()!=null) {
            all[Integer.parseInt(idOrder) - 1].getDeliverer().deliveryChangeScore(score[Integer.parseInt(deliverRate)]);
        }
        all[Integer.parseInt(idOrder)-1].getStore().changeScore(score[Integer.parseInt(storeRate)]);
        needToRateFoods(all[Integer.parseInt(idOrder)-1].getStore(),all[Integer.parseInt(idOrder)-1]);
    }

    private void needToRateFoods(Store store,Order order){
        System.out.println("Do you like to rating any foods of your order ?");
        System.out.println("- Yes");
        System.out.println("- No");
        String ask = scanner.nextLine();
        if (ask.matches("[yY][eE][sS]")){
            giveFoodRate(store,order);
        }

    }

    private void giveFoodRate(Store store,Order order){ //set it for give for each food
        order.orderPrint();
        System.out.println("First enter food name :");
        System.out.println("or if dont like to rate enter \"end\"");
        String name = scanner.nextLine().toUpperCase().replace(' ','_');

        while(!name.matches("[eE][nN][dD]")){
            System.out.println("Enter your rating : [0-10]");
            String rate = scanner.nextLine();
            System.out.println("Enter your comment about food , if you wish, you can leave this section blank");
            String comment = scanner.nextLine();
            Score[] score = Score.values();
            order.getStore().getPoints().scoreTo(name, score[Integer.parseInt(rate)]);
            if (comment.length() != 0) {
                order.getStore().getPoints().commentTo(name, order.getUser().getPhoneNumber()+"/=/"
                        +comment+"/=/"+rate);
            }
            System.out.println("enter food name :");
            name = scanner.nextLine().toUpperCase().replace(' ','_');
        }
    }

    private void needChangeSituation(){
        System.out.println("Do you like to change any order situation ?");
        System.out.println("- Yes");
        System.out.println("- No");
        String ask = scanner.nextLine();
        if (ask.matches("[yY][Ee][sS]")){
            changeSituationOrder();
        }
    }

    private void changeSituationOrder(){
        System.out.println("Please enter number of order :");
        String id = scanner.nextLine();
        System.out.println("Please enter desired situation ");
        String state = scanner.nextLine();
        admin.getOrders().get(Integer.parseInt(id)-1).situationChanger(state);
    }

    public void mainMenu(){
        System.out.println("1- Orders menu");
        System.out.println("2- Setting menu");
        System.out.println("3- Delivery menu");
        System.out.println("4- Store menu");
        System.out.println("5- User menu");
        System.out.println("6- Exit");
        String choose = scanner.nextLine();
        switchMainMenu(conditionChecker(1,6,choose));
    }

    public void switchMainMenu(String choose){
        switch (choose){
            case "1":
                orderMenu();
                break;
            case "2":
                showSettingMenu();
                break;
            case "3":
                DeliveryAppMenu deliveryMenu = new DeliveryAppMenu(admin,scanner);
                deliveryMenu.showMenu();
                setAdmin(deliveryMenu.getAdmin());
                mainMenu();
                break;
            case "4":
                StoreAppMenu storeMenu = new StoreAppMenu(admin,scanner);
                storeMenu.showMenu();
                setAdmin(storeMenu.getAdmin());
                mainMenu();
                break;
            case "5":
                UserAppMenu userMenu = new UserAppMenu(admin,scanner);
                userMenu.showMenu();
                setAdmin(userMenu.getAdmin());
                mainMenu();
            case "6":
                return;
            default:
                break;
        }
    }

    private void showSettingMenu(){
        System.out.println("1- Change stores show");
        System.out.println("2- Change menus show");
        System.out.println("3- Exit");
        switch (scanner.nextLine()){
            case "1":
                storeFilterSetting();
                break;
            case "2":
                menuFilterSetting();
                break;
            case "3":
                break;
            default:
                break;
        }
        mainMenu();
    }

    public void setDesiredStore(Store desiredStore) {
        this.desiredStore = desiredStore;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setStoreFilterSetting(String filterSetting) {
        this.storeFilterSetting= filterSetting;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public void setMenuFilterSetting(String menuFilterSetting) {
        this.menuFilterSetting = menuFilterSetting;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStoreFilterSetting() {
        return this.storeFilterSetting;
    }
}
