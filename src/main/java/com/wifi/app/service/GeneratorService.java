package com.wifi.app.service;

import com.wifi.app.entity.MobilGenerator;
import com.wifi.app.repository.GeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GeneratorService {


    private final GeneratorRepository generatorRepository;

    @Autowired
    public GeneratorService(GeneratorRepository generatorRepository) {
        this.generatorRepository = generatorRepository;
    }

    @Transactional
    public List<MobilGenerator> getListMobilGenerator (){
        return generatorRepository.findAll();
    }
}
