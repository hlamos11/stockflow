package com.wifi.app.service;

import com.wifi.app.entity.Province;
import com.wifi.app.repository.ProvinceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository, ModelMapper modelMapper) {
        this.provinceRepository = provinceRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Province> getProvinceList() {
        return provinceRepository.findAll();
    }
}
