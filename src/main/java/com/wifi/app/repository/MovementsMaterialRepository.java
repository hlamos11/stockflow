package com.wifi.app.repository;

import com.wifi.app.entity.MovementsMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovementsMaterialRepository extends JpaRepository<MovementsMaterial, Integer> {
        public List<MovementsMaterial> findMovementsMaterialByMaterialIdOrderByCreatedAt(Integer id);
        MovementsMaterial findMovementsMaterialByMaterialIdAndStep(Integer Id, Integer Step);
}
