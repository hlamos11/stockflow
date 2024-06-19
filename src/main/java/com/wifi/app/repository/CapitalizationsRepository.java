package com.wifi.app.repository;

import com.wifi.app.entity.Capitalizations;
import com.wifi.app.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CapitalizationsRepository extends JpaRepository<Capitalizations, Integer> {

    public Optional<Capitalizations> findCapitalizationsByOrden(String orden);
}
