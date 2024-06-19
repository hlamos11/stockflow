package com.wifi.app.repository;

import com.wifi.app.entity.OrderLicensesMeraki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderLicensesMerakiRepository extends JpaRepository<OrderLicensesMeraki, Integer> {

    public Optional<OrderLicensesMeraki> findOrderLicensesMerakiByOrdernumber(String orderNumber);


}
