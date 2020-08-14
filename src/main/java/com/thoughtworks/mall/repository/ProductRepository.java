package com.thoughtworks.mall.repository;

import com.thoughtworks.mall.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    @Override
    List<Product> findAll();

}
