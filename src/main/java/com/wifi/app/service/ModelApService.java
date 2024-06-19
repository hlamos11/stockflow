package com.wifi.app.service;


import com.wifi.app.entity.ModelAp;
import com.wifi.app.objects.ModelApDTO;
import com.wifi.app.repository.ModelApRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ModelApService {

    private final ModelApRepository modelApRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelApService(ModelApRepository modelApRepository, ModelMapper modelMapper) {
        this.modelApRepository = modelApRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<ModelAp> getModelApList(){
        return modelApRepository.findAll();
    }

    @Transactional
    public Optional<ModelAp> findModelApByModelap(String modelap) {
        return modelApRepository.findModelApByModelap(modelap);
    }

    @Transactional
    public Optional<ModelAp> deleteModelApById(Integer id){
        return modelApRepository.deleteModelApById(id);
    }

    public boolean ModelApExist(String modelap) {
        return  findModelApByModelap(modelap).isPresent();
    }

    public ModelAp register(ModelApDTO modelApDTO){
        ModelAp modelAp = new ModelAp();
        modelMapper.map(modelApDTO, modelAp);
        return save(modelAp);
    }

    @Transactional
    public ModelAp save(ModelAp modelAp){
        return modelApRepository.save(modelAp);
    }


}
