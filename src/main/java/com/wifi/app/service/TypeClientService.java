package com.wifi.app.service;


import com.wifi.app.entity.Typeclient;
import com.wifi.app.repository.TypeClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeClientService {

    private final TypeClientRepository typeClientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TypeClientService(TypeClientRepository typeClientRepository, ModelMapper modelMapper) {
        this.typeClientRepository = typeClientRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Typeclient> getListTypeClient(){
        return typeClientRepository.findAll();
    }
}
