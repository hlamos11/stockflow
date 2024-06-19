package com.wifi.app.repository;

import com.wifi.app.entity.EstablishmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstablishmentDetailRepository extends JpaRepository<EstablishmentDetail, Integer> {

    public List<EstablishmentDetail> findEstablishmentDetailByModelap(String modelap);
    public List<EstablishmentDetail> findEstablishmentDetailByModelswitch(String modelswitch);

    public EstablishmentDetail findEstablishmentDetailByEstablishmentId(Integer id);
}
