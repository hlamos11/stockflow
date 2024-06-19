package com.wifi.app.repository;

import com.wifi.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Integer> {

    public Optional<Product> findProductById(Integer id);
    public Optional<Product> findProductByName(String name);
}
