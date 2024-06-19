package com.wifi.app.service;



import com.wifi.app.entity.ModelSwitch;
import com.wifi.app.objects.ModelSwitchDTO;
import com.wifi.app.repository.ModelSwitchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ModelSwitchService {

    private final ModelSwitchRepository modelSwitchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelSwitchService(ModelSwitchRepository modelSwitchRepository, ModelMapper modelMapper) {
        this.modelSwitchRepository = modelSwitchRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<ModelSwitch> getModelSwitchList() {return modelSwitchRepository.findAll();}

    @Transactional
    private Optional<ModelSwitch> findModelSwitchByModelswitch(String modelswitch) {
        return modelSwitchRepository.findModelSwitchByModelswitch(modelswitch);
    }

    @Transactional
    public Optional<ModelSwitch> deleteModelSwitchById(Integer id) {
        return modelSwitchRepository.deleteModelSwitchById(id);
    }

    public boolean ModelSwitchExist(String modelswitch) {
        return  findModelSwitchByModelswitch(modelswitch).isPresent();
    }


    public ModelSwitch register(ModelSwitchDTO modelSwitchDTO){
        ModelSwitch modelSwitch = new ModelSwitch();
        modelMapper.map(modelSwitchDTO, modelSwitch);
        return save(modelSwitch);
    }

    @Transactional
    public ModelSwitch save(ModelSwitch modelSwitch){
        return modelSwitchRepository.save(modelSwitch);
    }


}
