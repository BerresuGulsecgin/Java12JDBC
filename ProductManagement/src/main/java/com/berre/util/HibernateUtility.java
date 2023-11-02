package com.berre.util;


import com.berre.entity.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

    private final static SessionFactory SESSION_FACTORY=sessionFactoryHibernate();

    private static SessionFactory sessionFactoryHibernate() {


        try{
            Configuration configuration=new Configuration();
            configuration.addAnnotatedClass(Admin.class); //mapping yerine bu da kullanılabilir
            configuration.addAnnotatedClass(Customer.class); //mapping yerine bu da kullanılabilir
            configuration.addAnnotatedClass(Category.class); //mapping yerine bu da kullanılabilir
            configuration.addAnnotatedClass(Product.class); //mapping yerine bu da kullanılabilir
            configuration.addAnnotatedClass(ProductDetail.class); //mapping yerine bu da kullanılabilir

            SessionFactory sessionFactory=configuration.configure().buildSessionFactory();
            return sessionFactory;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }
}
