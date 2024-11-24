package com.checker.scout.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.WebSite;
import com.checker.scout.repositories.SitiWebRepository;

@Service
public class WebSiteService {
    @Autowired
    private SitiWebRepository sitiWebRepository;
    
     public List<WebSite> findAllWebSite(Long idHosting){
        return sitiWebRepository.findAllWebSite(idHosting);
    }
}
