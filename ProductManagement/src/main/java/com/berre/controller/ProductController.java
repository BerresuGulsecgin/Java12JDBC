package com.berre.controller;

import com.berre.entity.Category;
import com.berre.entity.Customer;
import com.berre.entity.Product;
import com.berre.service.CategoryService;
import com.berre.service.ProductService;
import com.berre.util.BAUtils;

import java.util.Optional;

public class ProductController {

    private final ProductService productService;
    private final CategoryController categoryController;

    public ProductController() {
        this.productService=new ProductService();
        this.categoryController=new CategoryController();
    }

    public void save() {
        String productName= BAUtils.readString("Product name");
        double productPrice=BAUtils.readDouble("Product price");
        int productStock=BAUtils.readInt("Product stock");
        Long categoryId= (long) BAUtils.readInt("Category id");

        Optional<Category> optionalCategory=categoryController.findById(categoryId);

        Product product=Product.builder()
                .name(productName)
                .price(productPrice)
                .stock(productStock)
                .category(optionalCategory.get())
                .build();

        productService.save(product);

    }

    public void buyProduct(Customer customer) {
        long id=BAUtils.readInt(" almak istediğiniz ürün id sini giriniz");
        int amonut=BAUtils.readInt("kaç adet alacaksınız");
        Optional<Product> optionalProduct = productService.buyProduct(id, amonut);
        optionalProduct.get().getCustomers().add(customer);
        optionalProduct.get().setStock(optionalProduct.get().getStock()-amonut);
        productService.update(optionalProduct.get());
    }
}
