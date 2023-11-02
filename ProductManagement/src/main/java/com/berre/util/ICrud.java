package com.berre.util;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ICrud<T, ID> {

    T save(T entity);
    Iterable<T> saveAll(Collection<T> entities);
    void deleteById(ID id);
    void delete(T entity);
    T update(T entity);
    Optional<T> findById(ID id);
    boolean existsById(ID id);
    List<T> findAll();

    /**
     * Buna sonra bakacağız.Reflection kullanacağız.
     * @param entity
     * @return
     */
    List<T> findByEntity(T entity);

   <E> List<T> findByColumnNameAndValue(String columnName, E value);



}
