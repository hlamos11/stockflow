package com.wifi.app.service;



import com.wifi.app.entity.TypeBusiness;
import com.wifi.app.objects.TypeBusinessDTO;
import com.wifi.app.repository.TypeBusinessRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypeBusinessService {

    private final TypeBusinessRepository typeBusinessRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TypeBusinessService(TypeBusinessRepository typeBusinessRepository, ModelMapper modelMapper) {
        this.typeBusinessRepository = typeBusinessRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<TypeBusiness> getTypeBusinessList() {
        return typeBusinessRepository.findAll();
    }

    @Transactional
    public Optional<TypeBusiness> findTypeBusinessById(Integer id) {
        return typeBusinessRepository.findTypeBusinessById(id);
    }

    @Transactional
    public Optional<TypeBusiness> findTypeBusinessByName(String name) {
        return typeBusinessRepository.findTypeBusinessByName(name);
    }

    @Transactional
    public Optional<TypeBusiness> deleteTypeBusinessById(Integer id) {
        return typeBusinessRepository.deleteTypeBusinessById(id);
    }

//    @Transactional
//    public List<TypeBusiness> listTypeBusinessByName(String name){
//        return  typeBusinessRepository.findypeBusinessByName(name);
//    }

    public boolean TypeBusinessExist(String name) {
        return findTypeBusinessByName(name).isPresent();
    }


    public TypeBusiness register(TypeBusinessDTO typeBusinessDTO){
        TypeBusiness typeBusiness = new TypeBusiness();
        modelMapper.map(typeBusinessDTO, typeBusiness);
        return save(typeBusiness);
    }

    @Transactional
    public TypeBusiness save(TypeBusiness typeBusiness){
        return typeBusinessRepository.save(typeBusiness);
    }
}


