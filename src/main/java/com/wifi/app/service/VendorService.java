package com.wifi.app.service;

import com.wifi.app.entity.Vendor;
import com.wifi.app.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Transactional
    public List<Vendor> getFindVendorAll(){
        return vendorRepository.findAll();
    }
}
