package com.wifi.app.repository;

import com.wifi.app.entity.MobilGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratorRepository extends JpaRepository<MobilGenerator, Integer> {
}
