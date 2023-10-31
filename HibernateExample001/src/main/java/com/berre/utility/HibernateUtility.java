package com.berre.utility;

import com.berre.entity.Post;
import com.berre.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

    private final static SessionFactory SESSION_FACTORY=sessionFactoryHibernate();

    private static SessionFactory sessionFactoryHibernate() {


        try{
            Configuration configuration=new Configuration();
            configuration.addAnnotatedClass(User.class); //mapping yerine bu da kullanılabilir
            configuration.addAnnotatedClass(Post.class);
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
