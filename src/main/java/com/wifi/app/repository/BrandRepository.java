package com.wifi.app.repository;

import com.wifi.app.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository <Brand, Integer> {
    Optional<Brand> findBrandByName(String name);
}
