package com.checker.scout.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.projections.IWebSite;
import com.checker.scout.repositories.ServizioWebSiteRepository;


@Service
public class ServizioWebSiteService {
    @Autowired
    private ServizioWebSiteRepository servizioWebSiteRepository;

    public Page<IWebSite> getAllWebSiteToUpdate(String nome,int days,Pageable pageable){
        return this.servizioWebSiteRepository.findAllWebSiteToUpdate(nome,days,pageable);
    }
}
