package com.wifi.app.service;


import com.wifi.app.entity.Clients;
import com.wifi.app.entity.OrderLicensesMeraki;
import com.wifi.app.objects.ClientDTO;
import com.wifi.app.objects.OrderLicensesMerakiDTO;
import com.wifi.app.repository.OrderLicensesMerakiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderLicensesMerakiService {

    private final OrderLicensesMerakiRepository orderLicensesMerakiRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public OrderLicensesMerakiService(OrderLicensesMerakiRepository orderLicensesMerakiRepository, ModelMapper modelMapper) {
        this.orderLicensesMerakiRepository = orderLicensesMerakiRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<OrderLicensesMeraki> getOrderLicensesMerakiList() {
        return orderLicensesMerakiRepository.findAll();
    }

    @Transactional
    public  OrderLicensesMeraki save(OrderLicensesMeraki orderLicensesMeraki){
        return orderLicensesMerakiRepository.save(orderLicensesMeraki);
    }

    public OrderLicensesMeraki register(OrderLicensesMerakiDTO orderLicensesMerakiDTO){
        OrderLicensesMeraki orderLicensesMeraki = new OrderLicensesMeraki();


        orderLicensesMerakiDTO.setCountlicensesavailable(orderLicensesMerakiDTO.getCountlicenses());
        orderLicensesMerakiDTO.setCountlicensesexpired(0);
        orderLicensesMerakiDTO.setCountlicensesused(0);

        modelMapper.map(orderLicensesMerakiDTO, orderLicensesMeraki);
        return save(orderLicensesMeraki);
    }

    public boolean orderExist(String orderNumber){
        return findOrderLicensesMerakiByOrdernumber(orderNumber).isPresent();
    }

    @Transactional
    private Optional<OrderLicensesMeraki> findOrderLicensesMerakiByOrdernumber(String orderNumber) {
        return orderLicensesMerakiRepository.findOrderLicensesMerakiByOrdernumber(orderNumber);
    }

}

