package com.berre;

import com.berre.app.Menu;
import com.berre.util.HibernateUtility;

//productMS_DB
public class Main {
    public static void main(String[] args) {

        //HibernateUtility.getSessionFactory().openSession();
        Menu menu=new Menu();
        menu.menu();
    }
}