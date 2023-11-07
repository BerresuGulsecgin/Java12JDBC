package com.berre.controller;

import com.berre.entity.Product;
import com.berre.entity.ProductDetail;
import com.berre.service.ProductDetailService;
import com.berre.service.ProductService;
import com.berre.util.BAUtils;

import java.util.Optional;



public class ProductDetailController {

    private final ProductService productService;
    private final ProductDetailService productDetailService;

    public ProductDetailController() {
        this.productService = new ProductService();
        this.productDetailService=new ProductDetailService();
    }

    public void makeCommentAndScore() {
        long id= BAUtils.readInt("yorum yapmak istediğiniz ürün id sini giriniz");
        Optional<Product> optionalProduct=productService.findById(id);
        if (optionalProduct.isPresent()){
            String yorum=BAUtils.readString("yazmak istediğiniz yorum");
            double puan=BAUtils.readDouble("puan giriniz");
            ProductDetail productDetail= ProductDetail.builder()
                    .comment(yorum)
                    .score(puan)
                    .product(optionalProduct.get())
                    .build();

            //optionalProduct.get().getProductDetails().add(productDetail);

            productDetailService.save(productDetail);
            //productService.update(optionalProduct.get());

        }else {
            System.out.println("ürün bulunamadı");
        }
    }

    public void showComment(){
        Optional<Product> product=productService.findById(1L);
        product.get().getProductDetails().forEach(x-> System.out.println(x.getComment()+" "+x.getScore()));
    }

}
