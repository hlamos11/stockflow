package com.wifi.app.repository;

import com.wifi.app.entity.TypeBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeBusinessRepository extends JpaRepository<TypeBusiness, Integer> {

    public Optional<TypeBusiness> findTypeBusinessById(Integer id);
    public Optional<TypeBusiness> findTypeBusinessByName(String name);
    public Optional<TypeBusiness> deleteTypeBusinessById(Integer id);
}
