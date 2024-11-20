package com.checker.scout.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.WebSite;
import com.checker.scout.repositories.SitiWebRepository;

@Service
public class WebSiteService {
    @Autowired
    private SitiWebRepository sitiWebRepository;
    
    public Page<WebSite> getAllWebSites(String nome,Long hostingId,Pageable pageable){
        return sitiWebRepository.findAllWebSite(nome,hostingId,pageable);
    }
}
