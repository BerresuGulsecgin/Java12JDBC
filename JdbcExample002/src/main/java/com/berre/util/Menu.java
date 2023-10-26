package com.berre.util;

import com.berre.controller.PersonController;
import com.berre.entity.Person;
import com.berre.repository.PersonRepository;

import java.util.Scanner;

public class Menu {
    static Scanner scanner=new Scanner(System.in);
    private final PersonController personController;
    public Menu(){
        this.personController=new PersonController();
    }
    public void menu(){

        while (true){
            System.out.println("---------DataBase İşlemleri---------");
            System.out.println("1- Data base veri ekle");
            System.out.println("2 --> Data base tum verileri goruntuleme");
            System.out.println("3 --> Data base tum verileri silme");
            System.out.println("4 --> Data base mail guncelleme");
            System.out.println("5 --> Data basede id ile veri arama");
            System.out.println("6 --> Data basete id ile veri silme");

            int secim=scanner.nextInt();
            scanner.nextLine();
            int id;
            switch (secim){
                case 1:
                    System.out.println("Adınız ?");
                    String firsName=scanner.nextLine();
                    System.out.println("Soyadınız ?");
                    String lastName=scanner.nextLine();
                    System.out.println("Email adresiniz ?");
                    String email=scanner.nextLine();
                    Person person=new Person(firsName,lastName,email);
                    personController.register(person);
                    break;
                case 2:
                    personController.findAll();
                    break;
                case 3:
                    personController.deleteAll();
                    break;
                case 4:
                    personController.findAll();
                    System.out.println("mailini güncellemek istediğiniz kullanıcının id sini giriniz");
                    id=scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Yeni mail adresini giriniz");
                    String mail=scanner.nextLine();
                    Person person1 = personController.findById(id);
                    person1.setEmail(mail);
                    personController.update(person1);
                    break;
                case 5 :
                    System.out.println("aramak istediğiniz kullanıcının id sini giriniz");
                    id=scanner.nextInt();
                    personController.findById(id);
                    break;
                case 6 :
                    personController.findAll();
                    System.out.println("Silmek istediğiniz kullanıcının id sini giriniz");
                    id=scanner.nextInt();
                    Person person2=personController.findById(id);
                    personController.deletePersonById(person2);
                    break;



            }
        }
    }
}
