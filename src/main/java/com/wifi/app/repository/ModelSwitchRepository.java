package com.wifi.app.repository;


import com.wifi.app.entity.ModelSwitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelSwitchRepository extends JpaRepository <ModelSwitch, Integer> {

    public Optional<ModelSwitch> findModelSwitchById(Integer id);
    public Optional<ModelSwitch> findModelSwitchByModelswitch(String name);
    public Optional<ModelSwitch> deleteModelSwitchById(Integer id);
}
