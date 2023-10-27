package com.berre.repository;

import java.util.List;

public interface ICrud <T>{

   void saveAll(List<T> t);

   void saveAll(T t);

   void update(T t, int id);

   List<T> findAll();

   //void hangiTedHangiAravbayiSatiyo(); dealerRepository de yaz



}
