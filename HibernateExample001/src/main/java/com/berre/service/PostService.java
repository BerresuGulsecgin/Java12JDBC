package com.berre.service;

import com.berre.entity.Post;
import com.berre.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class PostService {

    public void createPost(){
        Session session= HibernateUtility.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();

        Post post=Post.builder()
                .content("harika gun")
                .date(new Date())
                .userId(1L)
                .build();

        session.save(post);
        transaction.commit();
        session.close();
    }
}
