package com.berre.repository;

import com.berre.entity.Product;
import com.berre.util.MyRepositoryFactory;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.PublicKey;
import java.util.List;

public class ProductRepository extends MyRepositoryFactory<Product,Long> {
    public ProductRepository() {
        super(Product.class);
    }

    public List<Product> listProductWhereStockLessThenTen(){
        openSession();
        CriteriaQuery<Product> criteriaQuery=getCriteriaBuilder().createQuery(Product.class);
        Root<Product> root=criteriaQuery.from(Product.class);
        criteriaQuery.select(root).where(getCriteriaBuilder().lessThanOrEqualTo(root.get("stock"),10));
        List<Product> result=getSession().createQuery(criteriaQuery).getResultList();
        closeSession();
        return result;
    }
}
