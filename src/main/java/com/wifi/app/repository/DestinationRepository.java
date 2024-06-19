package com.wifi.app.repository;

import com.wifi.app.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    Optional<Destination> findDestinationByName(String name);
}
