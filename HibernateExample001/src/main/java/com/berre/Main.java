package com.berre;


import com.berre.service.PostService;
import com.berre.service.UserService;


public class Main {
    public static void main(String[] args) {

        //save user metodu yaz
        UserService userService=new UserService();
        //userService.save();
        PostService postService=new PostService();
        //postService.createPost();

        //userService.findAllHQL().forEach(user -> System.out.println(user));
        //userService.findAll().forEach(System.out::println);
        //userService.FindAllInformationName().forEach(System.out::println);

        //userService.FindAllInformation().forEach(inf -> System.out.println(inf));

        //System.out.println(userService.FindById(2L));
        //System.out.println(userService.findByUsername("memo"));
        System.out.println(userService.ilebaslayan("m"));




    }

}