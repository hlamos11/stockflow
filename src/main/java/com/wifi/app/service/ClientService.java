package com.wifi.app.service;


import com.wifi.app.entity.Clients;
import com.wifi.app.objects.ClientDTO;
import com.wifi.app.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Clients> getClientList() {
        return clientRepository.findAll();
    }

    @Transactional
    public Optional<Clients> findClientByName(String name) {
        return clientRepository.findClientByName(name);
    }

    @Transactional
    public Optional<Clients> findClientById(Integer id) {
        return clientRepository.findClientById(id);
    }

    @Transactional
    public List<Clients> findClientByTypeClientId(Integer typeId){
        return clientRepository.findClientByTypeClientId(typeId);
    }

    public boolean clientExists(String name){
        return  findClientByName(name).isPresent();
    }

    @Transactional
    public Clients save(Clients clients){
        return clientRepository.save(clients);
    }

    public Clients register(ClientDTO clientDTO){
        Clients clients = new Clients();
        modelMapper.map(clientDTO, clients);
        return save(clients);
    }

    @Transactional
    public Optional<Clients> deleteClientById(Integer id) {
        return clientRepository.deleteClientById(id);
    }
}
