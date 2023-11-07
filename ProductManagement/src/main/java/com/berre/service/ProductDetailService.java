package com.berre.service;

import com.berre.entity.ProductDetail;
import com.berre.repository.ProductDetailRepository;

public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    public ProductDetailService() {
        this.productDetailRepository=new ProductDetailRepository();
    }

    public void save(ProductDetail productDetail) {
        productDetailRepository.save(productDetail);
    }
}
