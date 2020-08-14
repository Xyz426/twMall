package com.thoughtworks.mall.controller;

import com.thoughtworks.mall.entity.Cart;
import com.thoughtworks.mall.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @GetMapping("/cart")
    public ResponseEntity getCart(){
        List<Cart> carts = cartRepository.findAll();

        return ResponseEntity.ok(carts);
    }
}
