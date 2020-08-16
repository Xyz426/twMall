package com.thoughtworks.mall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mall.entity.Product;
import com.thoughtworks.mall.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
        productRepository.deleteAll();
    }

    @Test
    public void shouldAddProduct() throws Exception {
        Product product = Product.builder().url("xxx.com").unit("瓶").name("苏打水").price(1).build();

        String requestJson = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());

        List<Product> all = productRepository.findAll();
        assertEquals(1,all.size());

        assertEquals("苏打水",all.get(0).getName());

    }

    @Test
    public void shouldDeleteProductById() throws Exception {
        Product product = Product.builder().url("xxx.com").unit("瓶").name("苏打水").price(1).build();
        Product save = productRepository.save(product);

        mockMvc.perform(delete("/delete/{id}",save.getId()))
                .andExpect(status().isOk());

        assertEquals(0,productRepository.findAll().size());
    }
}
