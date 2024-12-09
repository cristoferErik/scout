package com.checker.scout.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checker.scout.controllers.home.interfaces.HomeInt;
import com.checker.scout.entities.projections.IWebSite;
import com.checker.scout.repositories.ServizioWebSiteRepository;


@Service
public class ServizioWebSiteService {
    @Autowired
    private ServizioWebSiteRepository servizioWebSiteRepository;

    @Autowired
    private EmailService emailService;

    @Transactional(readOnly=true)
    public Page<IWebSite.WebSiteP> getAllWebSiteToUpdate(Pageable pageable){
        return this.servizioWebSiteRepository.findAllWebSiteToUpdate(pageable);
    }

    public Map<String,Object> saveWebSiteToUpdate(HomeInt.webSiteUpdatedDTO webSiteUpdatedDTO){
        Map<String,Object> response = new HashMap<>();
        try {
            emailService.sendEmail();
            response.put("success","success");
            response.put("message","Il messagio é stato inviato con successo!");
        } catch (Exception e) {
            response.put("status","bad_request");
            response.put("message", e.getMessage());
        }
        return response;
    }
}
