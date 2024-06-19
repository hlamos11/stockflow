package com.wifi.app.service;

import com.wifi.app.entity.Store;
import com.wifi.app.objects.StoreDTO;
import com.wifi.app.repository.StoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public StoreService(StoreRepository storeRepository, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Store> getStoreList(){
        return storeRepository.findAll();
    }

    @Transactional
    public List<Store> findStoreByEnabled(Boolean enabled){
        return storeRepository.findStoreByEnabled(enabled);
    }

    public Store register(StoreDTO storeDTO){
        Store store = new Store();
        modelMapper.map(storeDTO, store);
        return  save(store);
    }

    @Transactional
    public Store save (Store store){
        return storeRepository.save(store);
    }

    @Transactional
    public Store findStoreById(Integer id){
        return storeRepository.findStoreById(id);
    }

    @Transactional
    public Optional<Store> findStoreByName(String name){
        return storeRepository.findStoreByName(name);
    }

    public boolean storeExists(String name) {
        return findStoreByName(name).isPresent();
    }
}
