package com.wifi.app.service;

import com.wifi.app.entity.Destination;
import com.wifi.app.objects.DestinationDTO;
import com.wifi.app.repository.DestinationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public DestinationService(DestinationRepository destinationRepository, ModelMapper modelMapper) {
        this.destinationRepository = destinationRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Destination> getFindDestinationAll (){
        return destinationRepository.findAll();
    }

    public Destination register(DestinationDTO destinationDTO) {

        Destination destination = new Destination();

        modelMapper.map(destinationDTO,destination );

        return save(destination);

    }

    @Transactional
    public Destination save(Destination destination){
        return destinationRepository.save(destination);
    }

    @Transactional
    public Optional<Destination> findDestinationByName(String name){
        return destinationRepository.findDestinationByName(name);
    }

    public boolean destinationExists(String name) {
        return findDestinationByName(name).isPresent();
    }
}
