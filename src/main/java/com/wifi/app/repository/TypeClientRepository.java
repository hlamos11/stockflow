package com.wifi.app.repository;

import com.wifi.app.entity.Typeclient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeClientRepository extends JpaRepository<Typeclient, Integer> {
}
