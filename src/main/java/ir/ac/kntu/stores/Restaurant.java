package ir.ac.kntu.stores;

import ir.ac.kntu.menus.RestaurantMenu;
import ir.ac.kntu.delivery.Address;

import java.time.LocalTime;

public class Restaurant extends Store {
    public Restaurant(String name, Address address, String phoneNumber, StoreType storeType, int delivererNum,
                      Situation situation, LocalTime startWorkTime, LocalTime endWorkTime, RestaurantMenu menu) {

        super(name,address, phoneNumber, storeType, delivererNum, situation, startWorkTime, endWorkTime,menu);
    }

    public Restaurant(){

    }
}
