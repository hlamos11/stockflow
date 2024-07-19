package com.wifi.app.service;

import com.wifi.app.entity.MovementsMaterial;
import com.wifi.app.objects.MovementDTO;
import com.wifi.app.repository.MovementsMaterialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovementsMaterialService {

    private final MovementsMaterialRepository movementsMaterialRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MovementsMaterialService(MovementsMaterialRepository movementsMaterialRepository, ModelMapper modelMapper) {
        this.movementsMaterialRepository = movementsMaterialRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<MovementsMaterial> getMovementsMaterial(){
        return movementsMaterialRepository.findAll();
    }

    @Transactional
    public MovementsMaterial findMovementsMaterialByMaterialIdAndStep(Integer Id, Integer Step){
        return movementsMaterialRepository.findMovementsMaterialByMaterialIdAndStep(Id, Step);
    }

    public boolean validateTypeMovement(String typeMovement) {

        return  false;
    }

    @Transactional
    public List<MovementsMaterial> findMovementsMaterialByMaterialIdOrderByCreatedAt(Integer id){
        return movementsMaterialRepository.findMovementsMaterialByMaterialIdOrderByCreatedAt(id);
    }

    @Transactional
    public MovementsMaterial save(MovementsMaterial movementsMaterial){
        return movementsMaterialRepository.save(movementsMaterial);
    }

    public MovementsMaterial register(MovementDTO movementDTO) {

        MovementsMaterial movementsMaterial = new MovementsMaterial();
        modelMapper.map(movementDTO, movementsMaterial);

        //movementsMaterial.setUser("CAMBIAR");

        return save(movementsMaterial);
    }
}
