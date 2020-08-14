package com.thoughtworks.mall.repository;

import com.thoughtworks.mall.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart,Integer> {
    @Override
    List<Cart> findAll();
}
