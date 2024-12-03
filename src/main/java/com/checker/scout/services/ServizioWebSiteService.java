package com.checker.scout.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.projections.IWebSite;
import com.checker.scout.repositories.ServizioWebSiteRepository;


@Service
public class ServizioWebSiteService {
    @Autowired
    private ServizioWebSiteRepository servizioWebSiteRepository;

    public List<IWebSite.WebSiteP> getAllWebSiteToUpdate(){
        return this.servizioWebSiteRepository.findAllWebSiteToUpdate();
    }
}
