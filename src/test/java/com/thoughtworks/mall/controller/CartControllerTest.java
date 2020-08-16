package com.thoughtworks.mall.controller;

import com.thoughtworks.mall.entity.Cart;
import com.thoughtworks.mall.repository.CartRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CartRepository cartRepository;

    @BeforeEach
    void setUp(){
        cartRepository.deleteAll();
    }

    @Test
    public void shouldGetAllCartProducts() throws Exception {
        Cart cart = Cart.builder().amount(1).name("可乐").price(3).unit("瓶").build();
        cartRepository.save(cart);

        cart = Cart.builder().amount(1).name("雪碧").price(3).unit("瓶").build();
        cartRepository.save(cart);

        cart = Cart.builder().amount(1).name("芬达").price(3).unit("瓶").build();
        cartRepository.save(cart);

        mockMvc.perform(get("/cart"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name",is("可乐")))
                .andExpect(jsonPath("$[1].name",is("雪碧")))
                .andExpect(jsonPath("$[2].name",is("芬达")))
                .andExpect(status().isOk());
    }
}
