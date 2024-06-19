package com.wifi.app.repository;

import com.wifi.app.entity.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsibleMaterialRepository extends JpaRepository <Responsible, Integer> {

    public Optional <Responsible> findResponsibleMaterialByEmail(String email);
}
