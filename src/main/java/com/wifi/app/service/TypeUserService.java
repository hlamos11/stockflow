package com.wifi.app.service;


import com.wifi.app.entity.TypeUser;
import com.wifi.app.repository.TipeUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeUserService {

    private final TipeUserRepository tipeUserRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TypeUserService(TipeUserRepository tipeUserRepository, ModelMapper modelMapper) {
        this.tipeUserRepository = tipeUserRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<TypeUser> getTypeUserList(){
        return tipeUserRepository.findAll();
    }
}
