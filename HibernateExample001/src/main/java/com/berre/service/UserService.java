package com.berre.service;

import com.berre.entity.Address;
import com.berre.entity.Information;
import com.berre.entity.User;
import com.berre.entity.enums.EAddressType;
import com.berre.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    public void save(){
        Session session= HibernateUtility.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Information information=Information.builder()
                .name("mehmet")
                .surname("kaya")
                .middleName("ali")
                .build();
        List<String> interests= Arrays.asList("Book","Comics");
        Map<EAddressType, Address> adres1=new HashMap<>();
        adres1.put(EAddressType.HOME,Address.builder().country("Türkiye").city("Ankara").street("Nazilli").build());
        User user=User.builder()
                .password("123456")
                .username("memo")
                .information(information)
                .interests(interests)
                .addresses(adres1)
                .build();
        session.save(user);
        transaction.commit();
        session.close();

    }
}
