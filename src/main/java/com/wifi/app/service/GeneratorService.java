package com.wifi.app.service;

import com.wifi.app.entity.MobilGenerator;
import com.wifi.app.entity.MobilGeneratorDetail;
import com.wifi.app.objects.GeneratorDTO;
import com.wifi.app.objects.GeneratorDetailDTO;
import com.wifi.app.repository.GeneratorRepository;
import com.wifi.app.repository.ProvinceRepository;
import com.wifi.app.repository.SiteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GeneratorService {


    private final GeneratorRepository generatorRepository;
    private final SiteRepository siteRepository;
    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GeneratorService(GeneratorRepository generatorRepository, SiteRepository siteRepository, ProvinceRepository provinceRepository, ModelMapper modelMapper) {
        this.generatorRepository = generatorRepository;
        this.siteRepository = siteRepository;
        this.provinceRepository = provinceRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<MobilGenerator> getListMobilGenerator (){
        return generatorRepository.findAll(Sort.by(Sort.Direction.DESC, "currentLevel"));
    }


    public MobilGenerator register(GeneratorDTO generatorDTO){
        MobilGenerator mobilGenerator = new MobilGenerator();

        mobilGenerator.setUser(generatorDTO.getUser());
        mobilGenerator.setTankLevel(generatorDTO.getTankLevel());
        mobilGenerator.setRefill(generatorDTO.getRefill());
        mobilGenerator.setCurrentLevel(generatorDTO.getCurrentLevel());
        mobilGenerator.setSite(siteRepository.findSiteById(generatorDTO.getSiteId()));
        mobilGenerator.setProvince(provinceRepository.findProvinceById(generatorDTO.getProvinceId()));
        //modelMapper.map(generatorDTO, mobilGenerator);
        return save(mobilGenerator);
    }

    @Transactional
    public MobilGenerator save(MobilGenerator mobilGenerator) {
        return generatorRepository.save(mobilGenerator);
    }

    @Transactional
    public MobilGenerator findMobilGeneratorById(Integer id){
        return generatorRepository.findMobilGeneratorById(id);
    }
}
