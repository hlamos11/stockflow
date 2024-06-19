package com.wifi.app.repository;

import com.wifi.app.entity.LicensePortal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicensePortalRepository extends JpaRepository<LicensePortal, Integer> {
}
