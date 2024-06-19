package com.wifi.app.service;


import com.wifi.app.entity.Establishment;
import com.wifi.app.objects.EstablishmentsDTO;
import com.wifi.app.repository.EstablishmentsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentService {

    private final EstablishmentsRepository establishmentsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EstablishmentService(EstablishmentsRepository establishmentsRepository, ModelMapper modelMapper) {
        this.establishmentsRepository = establishmentsRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List <Establishment> getEstablishmentsList(){
        return establishmentsRepository.findAll();
    }

    @Transactional
    public Optional<Establishment> findEstablishmentById(Integer id){
        return  establishmentsRepository.findEstablishmentById(id);
    }

    @Transactional
    public Establishment findEstablishmentByClientId(Integer id){
        return  establishmentsRepository.findEstablishmentByClientId(id);
    }

    @Transactional
    public  List <Establishment> findEstablishmentByClient(Integer client_id){
        return  establishmentsRepository.findEstablishmentByClient(client_id);
    }

    @Transactional
    public List <Establishment> findEstablishmentByNameEstablishment(String NameEstablishment){
        return establishmentsRepository.findEstablishmentByNameestablishment( NameEstablishment);
    }


    @Transactional
    public List <Establishment> findEstablishmentByType(String type){
        return establishmentsRepository.findEstablishmentByType(type);
    }

    @Transactional
    public Establishment save(Establishment establishments){
        return establishmentsRepository.save(establishments);
    }

    public boolean establishmentExistById(Integer id){
        return findEstablishmentById(id).isPresent();

    }

    public boolean establishmentExistName( String name){
        return findEstablishmentByNameEstablishment(name).isEmpty();
    }

    public Establishment register(EstablishmentsDTO establishmentsDTO){
        Establishment establishments = new Establishment();

        modelMapper.map(establishmentsDTO, establishments);
        return save(establishments);
    }
}
