package com.wifi.app.repository;

import com.wifi.app.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository <Store, Integer> {

    public Store findStoreById (Integer id);
    public Optional<Store> findStoreByName (String name);

    public List<Store> findStoreByEnabled (Boolean enabled);

    List<Store> findByIdNotIn(Collection<Integer> Id);

}
