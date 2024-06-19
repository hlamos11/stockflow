package com.wifi.app.repository;

import com.wifi.app.entity.ModelAp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelApRepository extends JpaRepository <ModelAp, Integer> {

    public Optional<ModelAp> findModelApById(Integer id);
    public Optional<ModelAp> findModelApByModelap(String modelap);
    public Optional<ModelAp> deleteModelApById(Integer id);

}
