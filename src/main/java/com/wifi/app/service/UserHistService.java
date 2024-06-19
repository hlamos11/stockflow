package com.wifi.app.service;


import com.wifi.app.entity.User;
import com.wifi.app.entity.UserHist;
import com.wifi.app.repository.UserHistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserHistService {

    private final ModelMapper modelMapper;
    private final UserHistRepository userHistRepository;


    @Autowired
    public UserHistService(ModelMapper modelMapper, UserHistRepository userHistRepository) {
        this.modelMapper = modelMapper;
        this.userHistRepository = userHistRepository;
    }

    @Transactional
    public List<UserHist> getUserHistList(String username){
        return userHistRepository.findUserHistByUsername(username);
    }

    @Transactional
    public UserHist save(UserHist userHist){
        return userHistRepository.save(userHist);
    }
}
