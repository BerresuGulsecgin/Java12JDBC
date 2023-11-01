package com.berre.service;

import com.berre.entity.Address;
import com.berre.entity.Information;
import com.berre.entity.User;
import com.berre.entity.enums.EAddressType;
import com.berre.utility.HibernateUtility;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class UserService {

    private  Session session;
    private Transaction transaction;
    private  CriteriaBuilder criteriaBuilder;
    public UserService() {
        session= HibernateUtility.getSessionFactory().openSession();
        transaction=session.beginTransaction();
        criteriaBuilder=session.getCriteriaBuilder();
    }

    public void save2(){
       ;
        Information information=Information.builder()
                .name("mehmet")
                .surname("kaya")
                .middleName("ali")
                .build();
        List<String> interests= Arrays.asList("Book","Comics");
        Map<EAddressType, Address> adres1=new HashMap<>();
        adres1.put(EAddressType.HOME,Address.builder().country("Amerika").city("LA").street("Nazilli").build());
        User user=User.builder()
                .password("123456")
                .username("memo")
                .information(information)
                .interests(interests)
                .addresses(adres1)
                .build();



        Information information2=Information.builder()
                .name("hakan")
                .surname("kış")
                .middleName("osman")
                .build();
        List<String> interests2= Arrays.asList("PC","Book");
        Map<EAddressType, Address> adres2=new HashMap<>();
        adres2.put(EAddressType.HOME,Address.builder().country("Türkiye").city("Ankara").street("Nazilli").build());
        User user2=User.builder()
                .password("123456")
                .username("memo")
                .information(information2)
                .interests(interests2)
                .addresses(adres2)
                .build();


        Information information3=Information.builder()
                .name("hakan")
                .surname("kış")
                .middleName("osman")
                .build();
        List<String> interests3= Arrays.asList("Softwre","Book");
        Map<EAddressType, Address> adres3=new HashMap<>();
        adres3.put(EAddressType.HOME,Address.builder().country("Türkiye").city("Ankara").street("Nazilli").build());
        User user3=User.builder()
                .password("123456")
                .username("memo")
                .information(information3)
                .interests(interests3)
                .addresses(adres3)
                .build();
        session.save(user2);
        transaction.commit();
        session.close();

    }

    public void save(){

        Information information = Information.builder()
                .surname("kaya")
                .middleName("ali")
                .name("mehmet")
                .build();

        List<String> interests = Arrays.asList("Book", "Comics");

        Map<EAddressType, Address> adres1 = new HashMap<>();
        adres1.put(EAddressType.HOME, Address.builder()
                .country("Türkiye")
                .city("Ankara")
                .street("Nazilli")
                .build());

        User user = User.builder()
                .password("123456")
                .username("memo")
                .information(information)
                .interests(interests)
                .addresses(adres1)
                .build();
        //------------------
        Information information2 = Information.builder()
                .surname("kış")
                .middleName("osman")
                .name("hakan")
                .build();

        List<String> interests2 = Arrays.asList("PC", "Book");

        Map<EAddressType, Address> adres2 = new HashMap<>();
        adres2.put(EAddressType.HOME, Address.builder()
                .country("Amerika")
                .city("LA")
                .street("XStreet")
                .build());

        User user2 = User.builder()
                .password("123456")
                .username("ali")
                .information(information2)
                .interests(interests2)
                .addresses(adres2)
                .build();
        //------------------
        Information information3 = Information.builder()
                .surname("yaz")
                .middleName("mahmut")
                .name("serkan")
                .build();

        List<String> interests3 = Arrays.asList("Software", "Dergi");

        Map<EAddressType, Address> adres3 = new HashMap<>();
        adres3.put(EAddressType.HOME, Address.builder()
                .country("İspanya")
                .city("Madrid")
                .street("YStreet")
                .build());

        User user3 = User.builder()
                .password("123456")
                .username("serko")
                .information(information3)
                .interests(interests3)
                .addresses(adres3)
                .build();
        session.save(user);
        session.save(user2);
        session.save(user3);

        transaction.commit();
        session.close();
    }

    public List<User> findAllHQL(){


        List<User> resultList = session.createQuery("from " + User.class.getSimpleName(), User.class).getResultList();

        transaction.commit();
        session.close();
        return resultList;

    }
    public List<User> findAll(){

        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();

        CriteriaQuery<User> userCriteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=userCriteriaQuery.from(User.class);

        userCriteriaQuery.select(root); // select * from
        List<User> userList=session.createQuery(userCriteriaQuery).getResultList();
        for (User user : userList){
            Hibernate.initialize(user.getAddresses());
            Hibernate.initialize(user.getInterests());
        }


        transaction.commit();
        session.close();
        return userList;

    }
    public List<User> findAllNative() {

        String sql = "select * from tbl_user";
        List<User> userList = session.createNativeQuery(sql, User.class).getResultList();

        transaction.commit();
        session.close();
        return userList;
    }


    public List<Information> FindAllInformation(){
        CriteriaQuery<Information> userCriteriaQuery=criteriaBuilder.createQuery(Information.class);
        Root<User> root=userCriteriaQuery.from(User.class);

        userCriteriaQuery.select(root.get("information"));
        List<Information> informationList=session.createQuery(userCriteriaQuery).getResultList();
        transaction.commit();
        session.close();
        return informationList;

    }
    public List<String> FindAllInformationName(){
        CriteriaQuery<String> userCriteriaQuery=criteriaBuilder.createQuery(String.class);
        Root<User> root=userCriteriaQuery.from(User.class);

        userCriteriaQuery.select(root.get("information").get("name"));
        List<String> informationList=session.createQuery(userCriteriaQuery).getResultList();
        transaction.commit();
        session.close();
        return informationList;
    }
    public User FindById(Long id){

        CriteriaQuery<User> userCriteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"),id));
        User user=session.createQuery(userCriteriaQuery).getSingleResult();

        return user;

    }

    public User findByUsername(String username){
        CriteriaQuery<User> userCriteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"),username));
        User user=session.createQuery(userCriteriaQuery).getSingleResult();

        return user;
    }
    public List<User> ilebaslayan(String harf){

        CriteriaQuery<User> userCriteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(root).where(criteriaBuilder.like(root.get("information").get("name"),harf+"%"));
        List<User> userList=session.createQuery(userCriteriaQuery).getResultList();

        return userList;
    }

}
