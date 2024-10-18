package com.wifi.app.repository;

import com.wifi.app.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository <Province, Integer> {

    public Optional<Province> findProvinceById(Integer id);
    public Optional<Province> findProvinceByName(String name);
}
