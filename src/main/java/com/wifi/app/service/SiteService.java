package com.wifi.app.service;


import com.wifi.app.entity.Site;
import com.wifi.app.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Transactional
    public List<Site> getSiteList (){
        return siteRepository.findAll();
    }

    @Transactional
    public Site findSiteById (Integer id){
        return siteRepository.findSiteById(id);
    }
}
