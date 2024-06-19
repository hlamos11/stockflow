package com.wifi.app.service;

import com.wifi.app.entity.Capitalizations;
import com.wifi.app.objects.CapitalizationDTO;
import com.wifi.app.repository.CapitalizationsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CapitalizationsService {

    private final ModelMapper modelMapper;
    private final CapitalizationsRepository capitalizationsRepository;

    @Autowired
    public CapitalizationsService(ModelMapper modelMapper, CapitalizationsRepository capitalizationsRepository) {
        this.modelMapper = modelMapper;
        this.capitalizationsRepository = capitalizationsRepository;
    }

    @Transactional
    public List<Capitalizations> getCapitalizationsList(){
        return capitalizationsRepository.findAll();
    }

    @Transactional
    public Capitalizations save(Capitalizations capitalizations){
        return capitalizationsRepository.save(capitalizations);
    }

    public Capitalizations register(CapitalizationDTO capitalizationDTO){
        Capitalizations capitalizations = new Capitalizations();

        modelMapper.map(capitalizationDTO, capitalizations);
        return save(capitalizations);
    }


    public boolean clientExists(String orden) {
        return  findCapitalizationsByOrden(orden).isPresent();
    }

    @Transactional
    public Optional<Capitalizations> findCapitalizationsByOrden(String name) {
        return capitalizationsRepository.findCapitalizationsByOrden(name);
    }
}
