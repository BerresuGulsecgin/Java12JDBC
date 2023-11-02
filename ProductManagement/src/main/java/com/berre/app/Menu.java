package com.berre.app;

import com.berre.controller.CustomerController;
import com.berre.util.BAUtils;

import java.util.HashMap;

public class Menu {

    private final CustomerController customerController;

    public Menu() {
        this.customerController=new CustomerController();
    }

    public void menu(){
        HashMap<Integer, String> menuItems=new HashMap<>();
        menuItems.put(1,"Admin");
        menuItems.put(2, "Customer");

        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1 :
                //adminMenu();
                break;
            case 2 :
                customerMenu();
                break;

        }
    }

    private void customerMenu() {
        HashMap<Integer, String> menuItems=new HashMap<>();
        menuItems.put(1,"Kayıt ol");
        menuItems.put(2, "Giriş yap");

        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1 :
                customerController.register();
                break;
            case 2 :
                System.out.println("giriş yap");
                break;

        }
    }
}
