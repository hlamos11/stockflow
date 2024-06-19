package com.wifi.app.repository;

import com.wifi.app.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstablishmentsRepository extends JpaRepository <Establishment, Integer>{

    public Optional<Establishment> findEstablishmentById(Integer id);
    public List <Establishment> findEstablishmentByClient(Integer client_id);
    public List <Establishment> findEstablishmentByNameestablishment(String nameestablishment);
    public List <Establishment> findEstablishmentByType(String type);
    public List<Establishment> findEstablishmentByPurpple(Boolean type);
    public Establishment findEstablishmentByClientId(Integer id);

    //public List <Establishment> findAll();

    /*@Query("select count(e) from Establishment e WHERE e.idClient = ?1 and e.nameestablishment = ?2")
    int findAll(Integer id_client, String nameestablishment);*/


}
