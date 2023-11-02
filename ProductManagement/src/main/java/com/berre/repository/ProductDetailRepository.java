package com.berre.repository;

import com.berre.entity.ProductDetail;
import com.berre.util.MyRepositoryFactory;

public class ProductDetailRepository extends MyRepositoryFactory<ProductDetail,Long> {
    public ProductDetailRepository() {
        super(ProductDetail.class);
    }
}
