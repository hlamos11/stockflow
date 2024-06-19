package com.wifi.app.service;


import com.wifi.app.entity.Responsible;
import com.wifi.app.objects.ResponsibleMaterialDTO;
import com.wifi.app.repository.ResponsibleMaterialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsibleMaterialService {

    private final ResponsibleMaterialRepository responsibleMaterialRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ResponsibleMaterialService(ResponsibleMaterialRepository responsibleMaterialRepository, ModelMapper modelMapper) {
        this.responsibleMaterialRepository = responsibleMaterialRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Responsible> getResponsibleList (){
        return responsibleMaterialRepository.findAll();
    }

    @Transactional
    public Optional<Responsible> findResponsibleMaterialByEmail(String email){
        return responsibleMaterialRepository.findResponsibleMaterialByEmail(email);
    }

    public boolean getResponsibleExist(String email){
        return findResponsibleMaterialByEmail(email).isPresent();
    }

    @Transactional
    public Responsible save(Responsible responsible){
        return responsibleMaterialRepository.save(responsible);
    }

    public Responsible register(ResponsibleMaterialDTO responsibleMaterialDTO){
        Responsible responsible = new Responsible();
        modelMapper.map(responsibleMaterialDTO, responsible);
        return save(responsible);
    }

}
