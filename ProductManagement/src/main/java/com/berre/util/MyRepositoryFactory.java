package com.berre.util;


import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Getter

public class MyRepositoryFactory<T,ID> implements ICrud<T, ID>{

    private Session session;
    private Transaction transaction;

    private CriteriaBuilder criteriaBuilder;

Class<T> clazz;

    public MyRepositoryFactory(Class<T> clazz) {
        this.clazz=clazz;
    }

    protected void openSession() {
        session= HibernateUtility.getSessionFactory().openSession();
        transaction=session.beginTransaction();
        criteriaBuilder= session.getCriteriaBuilder();
    }

    protected void closeSession() {
        transaction.commit();
        session.close();
    }

    @Override
    public T save(T entity) {
        openSession();
        session.save(entity); //jpa persist
        closeSession();

        return entity;
    }

    @Override
    public Iterable<T> saveAll(Collection<T> entities) {
        openSession();
        entities.forEach(entity->session.save(entity));
        closeSession();
        return entities;
    }

    @Override
    public void deleteById(ID id) {
        openSession();
        session.delete(session.get(clazz, (Serializable) id));
        closeSession();

    }

    @Override
    public void delete(T entity) {
        openSession();
        session.delete(entity); //jpa da remove
        closeSession();

    }

    @Override
    public T update(T entity) {
        openSession();
        //session.update(entity);
        T guncellenen = (T)session.merge(entity); //sırf geri dönüş tipini alabilmek için bunu kullandık.JPA metodu
        closeSession();
        return guncellenen;
    }

    @Override
    public Optional<T> findById(ID id) {
        openSession();
        Optional<T> obj=Optional.ofNullable(session.get(clazz, (Serializable) id)); //jpa da find()
        closeSession();
        return obj;
    }

    @Override
    public boolean existsById(ID id) {

        return findById(id).isPresent();
    }

    @Override
    public List<T> findAll() {
        openSession();
        //session.createNativeQuery() bu metod içine direkt SQL yazılabilir
        List<T> resultList = session.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
        closeSession();
        return resultList;
    }



    @Override
     public <E> List<T> findByColumnNameAndValue(String columnName, E value) {
        openSession();
        List<T> resultList = session.createQuery("from " + clazz.getSimpleName() + " where " + columnName + " = :xyz", clazz)
                .setParameter("xyz", value).getResultList();
        closeSession();
        return resultList;
    }

    /**
     * Burada yapılmak istene(findByEntity) : Bir sınıf içindeki alanların metod tarafından okunulması
     * ve içlerindeki her bir alanın içindeki değerlerin kontrol edilerek
     * null olmayanları sorguya dahil etmesidir.
     * Böylece esnek sorgulama sistemi kazandırmaya çalışacağız
     * Bu işlemin adına REVERSE ENGINEERING (tersine mühendislik) denir
     */
    @Override
    public List<T> findByEntity(T entity) {
        openSession();
        criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery= criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        //sorguda kullanacağımız kreiterleri tutacağımız liste
        List<Predicate> predicates=new ArrayList<>();
        //sınıf içindki tüm filedleri dizi olarak döner
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            field.setAccessible(true); //fieldı erişime açmak için
            try {
                Object value = field.get(entity);
                if (value!=null && !field.getName().equals("id")){
                    if(field.getType().isAssignableFrom(String.class)){
                        predicates.add(criteriaBuilder.like(root.get(field.getName()), (String) value));
                    }
                    if (field.getType().isAssignableFrom(Number.class)){
                        predicates.add(criteriaBuilder.equal(root.get(field.getName()),value));
                    }


                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        List<T> resultList = session.createQuery(criteriaQuery).getResultList();

        closeSession();
        return resultList;
    }

}
