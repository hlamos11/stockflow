package com.wifi.app.service;



import com.wifi.app.entity.OrderLicensesPortal;
import com.wifi.app.objects.OrderLicensesPortalDTO;
import com.wifi.app.repository.OrderLicensesPortalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderLicensesPortalService {

    private final OrderLicensesPortalRepository orderLicensesPortalRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public OrderLicensesPortalService(OrderLicensesPortalRepository orderLicensesPortalRepository, ModelMapper modelMapper) {
        this.orderLicensesPortalRepository = orderLicensesPortalRepository;
        this.modelMapper = modelMapper;
    }

    public OrderLicensesPortal register(OrderLicensesPortalDTO orderLicensesPortalDTO){
        OrderLicensesPortal orderLicensesPortal = new OrderLicensesPortal();


        orderLicensesPortalDTO.setCountlicensesavailable(orderLicensesPortalDTO.getCountlicenses());
        orderLicensesPortalDTO.setCountlicensesexpired(0);
        orderLicensesPortalDTO.setCountlicensesused(0);

        modelMapper.map(orderLicensesPortalDTO, orderLicensesPortal);
        return save(orderLicensesPortal);
    }



    @Transactional
    public List<OrderLicensesPortal> getOrderLicensesPortalList() {
        return orderLicensesPortalRepository.findAll();
    }


    @Transactional
    public  OrderLicensesPortal save(OrderLicensesPortal orderLicensesMeraki){
        return orderLicensesPortalRepository.save(orderLicensesMeraki);
    }

    public boolean orderExist(String orderNumber){
        return findOrderLicensesPortalByOrdernumber(orderNumber).isPresent();
    }

    @Transactional
    private Optional<OrderLicensesPortal> findOrderLicensesPortalByOrdernumber(String orderNumber) {
        return orderLicensesPortalRepository.findOrderLicensesPortalByOrdernumber(orderNumber);
    }
}
