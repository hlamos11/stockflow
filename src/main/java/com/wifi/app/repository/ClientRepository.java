package com.wifi.app.repository;


import com.wifi.app.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Clients, Integer>{
    public Optional<Clients> findClientByName(String name);
    public Optional<Clients> findClientById(Integer id);
    public Optional<Clients> deleteClientById(Integer id);

    public List<Clients> findClientByTypeClientId(Integer typeId);
}
