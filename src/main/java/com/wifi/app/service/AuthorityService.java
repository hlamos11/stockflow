package com.wifi.app.service;


import com.wifi.app.entity.Authority;
import com.wifi.app.objects.AuthorityDTO;
import com.wifi.app.repository.AuthorityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthorityService {

    private final ModelMapper modelMapper;
    private final AuthorityRepository authorityRepository;


    public AuthorityService(ModelMapper modelMapper, AuthorityRepository authorityRepository) {
        this.modelMapper = modelMapper;
        this.authorityRepository = authorityRepository;
    }


    @Transactional
    public Authority save(Authority authority){
        return authorityRepository.save(authority);
    }

    public Authority register(AuthorityDTO authorityDTO){

        Authority authority = new Authority();
        modelMapper.map(authorityDTO, authority);
        return save(authority);
    }
}
