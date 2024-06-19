package com.wifi.app.service;

import com.wifi.app.entity.LicensePortal;
import com.wifi.app.objects.LicensePortalDTO;
import com.wifi.app.repository.LicensePortalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LicensePortalService {

    private final LicensePortalRepository licensePortalRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public LicensePortalService(LicensePortalRepository licensePortalRepository, ModelMapper modelMapper) {
        this.licensePortalRepository = licensePortalRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<LicensePortal> getLicensePortal (){
        return licensePortalRepository.findAll();
    }

    @Transactional
    public LicensePortal save(LicensePortal licensePortal){
        return licensePortalRepository.save(licensePortal);
    }

    public LicensePortal register(LicensePortalDTO licensePortalDTO) {
        licensePortalDTO.setUser("CAMBIAR");
        LicensePortal licensePortal = new LicensePortal();
        modelMapper.map(licensePortalDTO, licensePortal);
        return save(licensePortal);
    }
}
