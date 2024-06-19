package com.wifi.app.repository;

import com.wifi.app.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
   Optional<Material> findMaterialBySn(String sn);

   Material findMaterialById(Integer id);
   List<Material> findMaterialByInventoryMaterialId(Integer id);

   List<Material> findMaterialByBrandId(Integer id);
   Integer countMaterialByEnabledAndInventoryMaterialId(Boolean enabled, Integer id);

   Optional <Material> deleteMaterialById (Integer Id);
}
