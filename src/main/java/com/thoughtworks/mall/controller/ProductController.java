package com.thoughtworks.mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mall.entity.Cart;
import com.thoughtworks.mall.entity.Product;
import com.thoughtworks.mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public ResponseEntity home(){
        List<Product> products = productRepository.findAll();

        return ResponseEntity.ok(products);
    }


    @PostMapping("/product")
    public void addProduct(@RequestBody String name,@RequestBody Integer price,@RequestBody String unit,@RequestBody String imageUrl){
        Product product = Product.builder().price(price).name(name).unit(unit).url(imageUrl).build();
        productRepository.save(product);

    }
}

