package com.wifi.app.repository;

import com.wifi.app.entity.MobilGeneratorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobilGeneratorDetailRepository extends JpaRepository<MobilGeneratorDetail, Integer > {

    Boolean existsByMobilGeneratorId (Integer id);

    @Query(value = "SELECT * FROM mobil_generator_detail WHERE id = (SELECT MAX(id) FROM mobil_generator_detail WHERE mobil_generator_id = :id)", nativeQuery = true)
    MobilGeneratorDetail findMaxRegister(@Param("id") Integer mobilGeneratorId);
}
