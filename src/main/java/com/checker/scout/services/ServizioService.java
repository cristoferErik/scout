package com.checker.scout.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.Servizio;
import com.checker.scout.repositories.ServizioRepository;

@Service
public class ServizioService {
    
    @Autowired
    private ServizioRepository servizioRepository;

    public List<Servizio> getAllServizi(){
        return this.servizioRepository.findAllServizi();
    }
}
