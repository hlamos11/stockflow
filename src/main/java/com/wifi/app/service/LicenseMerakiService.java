package com.wifi.app.service;

import com.wifi.app.entity.LicenseMeraki;
import com.wifi.app.repository.LicenseMerakiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LicenseMerakiService {

    private final LicenseMerakiRepository licenseMerakiRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LicenseMerakiService(LicenseMerakiRepository licenseMerakiRepository, ModelMapper modelMapper) {
        this.licenseMerakiRepository = licenseMerakiRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<LicenseMeraki> findLicensesMerakiAll(){
        return licenseMerakiRepository.findAll();
    }
}
