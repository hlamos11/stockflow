package com.wifi.app.service;

import com.wifi.app.entity.Material;
import com.wifi.app.objects.MaterialDTO;
import com.wifi.app.repository.MaterialRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final ModelMapper modelMapper;

    private static final Logger log = LoggerFactory.getLogger(MaterialService.class);

    @Autowired
    public MaterialService(MaterialRepository materialRepository, ModelMapper modelMapper) {
        this.materialRepository = materialRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Material> getMaterialList(){
        return materialRepository.findAll();
    }

    @Transactional
    public Optional<Material> findMaterialBySn(String sn){
       return materialRepository.findMaterialBySn(sn);
    }

    @Transactional
    public List<Material> findMaterialByInventoryMaterialId(Integer id){
        return materialRepository.findMaterialByInventoryMaterialId( id);
    }

    @Transactional
    public Integer getMaterialCountByEnabledAndInventoryMaterialId (Boolean enabled, Integer Id){
        return materialRepository.countMaterialByEnabledAndInventoryMaterialId(enabled,Id);
    }

    @Transactional
    public Integer getMaterialCountByInventoryMaterialId (Integer Id){
        return materialRepository.countMaterialByInventoryMaterialId(Id);
    }

    @Transactional
    public  Material findMaterialById (Integer Id){
        return materialRepository.findMaterialById(Id);
    }

    @Transactional
    public Integer getMaterialCountByStoreIdAndInventoryMaterialId(Integer storeId, Integer Id){
        return materialRepository.countMaterialByStoreIdAndInventoryMaterialId(storeId, Id);
    }

    @Transactional
    public Integer getMaterialCountByStoreId(Integer storeId){
        return materialRepository.countByStoreId(storeId);
    }


    @Transactional
    public List<Material> findMaterialByBrandId (Integer id){
        return  materialRepository.findMaterialByBrandId(id);
    }

    @Transactional
    public Material save(Material material){
        return materialRepository.save(material);
    }

    public boolean materialExists(String sn) {
        return findMaterialBySn(sn).isPresent();
    }

    public Material register(MaterialDTO materialDTO) {
        Material material = new Material();

        modelMapper.map(materialDTO, material);

        return save(material);
    }

    @Transactional
    public Optional <Material> deleteMaterialById(Integer id){
        return materialRepository.deleteMaterialById(id);
    }
}
