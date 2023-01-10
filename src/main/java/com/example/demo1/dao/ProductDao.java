package com.example.demo1.dao;
import com.example.demo1.Product;

import java.util.List;

public interface ProductDao {
    void save(Product p);
    List<Product> findAll();

    void update(Product p);

}
