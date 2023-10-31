package com.berre;


import com.berre.service.PostService;
import com.berre.service.UserService;


public class Main {
    public static void main(String[] args) {

        //save user metodu yaz
        UserService userService=new UserService();
        userService.save();
        PostService postService=new PostService();
        postService.createPost();



    }

}