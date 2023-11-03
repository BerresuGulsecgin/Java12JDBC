package com.berre.service;

import com.berre.entity.Product;
import com.berre.repository.ProductRepository;

import java.util.Optional;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository=new ProductRepository();
    }

    public void save(Product product) {
        productRepository.save(product);
    }


    public Optional<Product> buyProduct(long id, int amount) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            if (optionalProduct.get().getStock()>=amount){
                return optionalProduct;

            }else {
                System.out.println("stock yetersiz");
            }

        }else {
            System.out.println("product bulunamdı");
        }
        return Optional.empty();

    }

    public void update(Product product) {
        productRepository.update(product);
    }
}
