package com.wifi.app.service;

import com.wifi.app.entity.EstablishmentDetail;
import com.wifi.app.repository.EstablishmentDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class EstablishmentDetailService {

    private final EstablishmentDetailRepository establishmentDetailRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EstablishmentDetailService(EstablishmentDetailRepository establishmentDetailRepository, ModelMapper modelMapper) {
        this.establishmentDetailRepository = establishmentDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<EstablishmentDetail> getEstablishmentsList(){
        return establishmentDetailRepository.findAll();
    }


    @Transactional
    public EstablishmentDetail findEstablishmentDetailByEstablishmentId(Integer id){

        return establishmentDetailRepository.findEstablishmentDetailByEstablishmentId(id);
    }

    @Transactional
    public List<EstablishmentDetail> findEstablishmentDetailByModelap(String modelap){
        return  establishmentDetailRepository.findEstablishmentDetailByModelap(modelap);
    }

    @Transactional
    public List<EstablishmentDetail> findEstablishmentDetailByModelswitch(String modelap){
        return  establishmentDetailRepository.findEstablishmentDetailByModelswitch(modelap);
    }
}
