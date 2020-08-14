package com.thoughtworks.mall.controller;


import com.thoughtworks.mall.entity.Product;
import com.thoughtworks.mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public List<Product> home(){
        List<Product> products = productRepository.findAll();

        return products;
    }


    @PostMapping("/product")
    public void addProduct(@RequestBody Product product){

        productRepository.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") int id){
        productRepository.deleteById(id);
    }
}

