package com.wifi.app.repository;


import com.wifi.app.entity.OrderLicensesPortal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderLicensesPortalRepository extends JpaRepository <OrderLicensesPortal, Integer> {

    public Optional<OrderLicensesPortal> findOrderLicensesPortalByOrdernumber(String orderNumber);
}
