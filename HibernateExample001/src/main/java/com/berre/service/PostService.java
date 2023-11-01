package com.berre.service;

import com.berre.entity.Post;
import com.berre.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class PostService {

    public void createPost(){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Post post = Post.builder()
                .content("Harika bir gün")
                .date(new Date())
                .userId(1L)
                .build();
        Post post2 = Post.builder()
                .content("Süperrrr")
                .date(new Date())
                .userId(2L)
                .build();
        Post post3 = Post.builder()
                .content("JAva Day")
                .date(new Date())
                .userId(1L)
                .build();
        Post post4 = Post.builder()
                .content("Mükkemmel bir hafta oluyor")
                .date(new Date())
                .userId(1L)
                .build();

        session.save(post);
        session.save(post2);
        session.save(post3);
        session.save(post4);
        transaction.commit();
        session.close();
    }
}
