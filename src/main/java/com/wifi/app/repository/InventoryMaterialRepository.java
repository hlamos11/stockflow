package com.wifi.app.repository;

import com.wifi.app.entity.InventoryMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMaterialRepository extends JpaRepository<InventoryMaterial, Integer> {
}
