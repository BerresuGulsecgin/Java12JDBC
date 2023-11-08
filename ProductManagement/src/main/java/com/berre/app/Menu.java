package com.berre.app;

import com.berre.controller.CategoryController;
import com.berre.controller.CustomerController;
import com.berre.controller.ProductController;
import com.berre.controller.ProductDetailController;
import com.berre.entity.Customer;
import com.berre.util.BAUtils;

import java.util.HashMap;

public class Menu {

    private final CustomerController customerController;
    private final CategoryController categoryController;
    private final ProductController productController;

    private final ProductDetailController productDetailController;

    public Menu() {
        this.customerController=new CustomerController();
        this.categoryController=new CategoryController();
        this.productController=new ProductController();
        this.productDetailController=new ProductDetailController();
    }

    public void menu(){
        HashMap<Integer, String> menuItems=new HashMap<>();
        menuItems.put(1,"Admin");
        menuItems.put(2, "Customer");

        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1 :
                adminMenu();
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
                customerManagerMenu(customerController.login());
                break;

        }
    }

    private void customerManagerMenu(Customer customer) {
        HashMap<Integer, String> menuItems=new HashMap<>();
        System.out.println(customer.getIdentity());
        menuItems.put(1,"satın al");
        menuItems.put(2,"ürüne yorum ve puan ver");
        menuItems.put(3,"stoğu bitmek üzere olan ürünleri listele(10adet ve aşağısı)");
        menuItems.put(4,"ürüne göre yorumları göster");
        menuItems.put(5,"Hesabımı yükselt");


        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1 :
                productController.buyProduct(customer);
                break;
            case 2 :
                productDetailController.makeCommentAndScore();
                break;
            case 3 :
                productController.getProductsLessThanTen();
                break;
            case 4 :
                productDetailController.showComment();
                break;
            case 5 :
                customerController.upgradeAccount(customer);
                break;

        }
    }

    private void adminMenu() {
        HashMap<Integer, String> menuItems=new HashMap<>();

        menuItems.put(1,"Product ekle");
        menuItems.put(2,"Category ekle");
        menuItems.put(3,"Tüm productları listelesin ekle");


        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1 :
                productController.save();
                break;
            case 2 :
                categoryController.save();
                break;
            case 3 :
                productController.findAll();
                break;

        }
    }
}
