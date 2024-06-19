package com.wifi.app.service;

import com.wifi.app.entity.InventoryMaterial;
import com.wifi.app.repository.InventoryMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InventoryMaterialService {

    private final InventoryMaterialRepository inventoryMaterialRepository;

    @Autowired
    public InventoryMaterialService(InventoryMaterialRepository inventoryMaterialRepository) {
        this.inventoryMaterialRepository = inventoryMaterialRepository;
    }

    @Transactional
    public List<InventoryMaterial> getListInventoryMaterial (){
        return inventoryMaterialRepository.findAll();
    }

    @Transactional
    public  InventoryMaterial getInventoryMaterialById (Integer id){
        return inventoryMaterialRepository.getById(id);
    }

    @Transactional
    public  InventoryMaterial save (InventoryMaterial inventoryMaterial){
        return inventoryMaterialRepository.save(inventoryMaterial);
    }
}
