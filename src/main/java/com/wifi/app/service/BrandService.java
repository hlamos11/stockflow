package com.wifi.app.service;

import com.wifi.app.entity.Brand;
import com.wifi.app.objects.BrandDTO;
import com.wifi.app.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {


    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandService(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Brand> getBrandListAll(){
        return brandRepository.findAll();
    }

    @Transactional
    public Optional<Brand> findBrandByName(String name){
        return brandRepository.findBrandByName(name);
    }

    @Transactional
    public Brand save (Brand brand){
        return brandRepository.save(brand);
    }

    public boolean storeExists(String name) {
        return findBrandByName(name).isPresent();
    }

    public Brand register(BrandDTO brandDTO) {
        Brand brand = new Brand();
        modelMapper.map(brandDTO,brand);
        return save(brand);
    }
}
