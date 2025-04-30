package com.wifi.app.service;

import com.wifi.app.entity.MobilGeneratorDetail;
import com.wifi.app.objects.GeneratorDetailDTO;
import com.wifi.app.repository.MobilGeneratorDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MobilGeneratorDetailService {

    private final MobilGeneratorDetailRepository mobilGeneratorDetailRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MobilGeneratorDetailService(MobilGeneratorDetailRepository mobilGeneratorDetailRepository, ModelMapper modelMapper) {
        this.mobilGeneratorDetailRepository = mobilGeneratorDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public MobilGeneratorDetail save(MobilGeneratorDetail mobilGeneratorDetail){
        return mobilGeneratorDetailRepository.save(mobilGeneratorDetail);
    }

    public MobilGeneratorDetail register(GeneratorDetailDTO generatorDetailDTO){
        MobilGeneratorDetail mobilGeneratorDetail = new MobilGeneratorDetail();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.map(generatorDetailDTO, mobilGeneratorDetail);
        return save(mobilGeneratorDetail);
    }

    public Boolean existByGeneratorId(Integer id){
        return mobilGeneratorDetailRepository.existsByMobilGeneratorId(id);
    }

    /*
    @Transactional
    public Integer getMaxRegister(int id){
        return mobilGeneratorDetailRepository.findMaxRegister(id)
                .map(MobilGeneratorDetail::getHourMeter)
                .orElse(0);
    }

     */

    @Transactional
    public MobilGeneratorDetail getMaxRegister(int id){
        return mobilGeneratorDetailRepository.findMaxRegister(id);
    }

}
