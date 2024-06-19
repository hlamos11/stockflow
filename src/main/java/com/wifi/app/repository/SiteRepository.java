package com.wifi.app.repository;

import com.wifi.app.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository <Site, Integer>{

    public Site findSiteById (Integer id);
}
