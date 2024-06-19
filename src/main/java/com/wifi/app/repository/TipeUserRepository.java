package com.wifi.app.repository;


import com.wifi.app.entity.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipeUserRepository extends JpaRepository <TypeUser, Integer> {

    public Optional <TypeUser> findTypeUserById(Integer id);
}
