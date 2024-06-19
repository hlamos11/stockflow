package com.wifi.app.repository;

import com.wifi.app.entity.LicenseMeraki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LicenseMerakiRepository extends JpaRepository<LicenseMeraki,Integer> {
}
