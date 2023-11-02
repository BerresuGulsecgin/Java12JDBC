package com.berre.repository;

import com.berre.entity.Product;
import com.berre.util.MyRepositoryFactory;

public class ProductRepository extends MyRepositoryFactory<Product,Long> {
    public ProductRepository() {
        super(Product.class);
    }
}
