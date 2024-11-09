package com.checker.scout.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.Servizio;
import com.checker.scout.repositories.ServizioRepository;

@Service
public class ServizioService {
    
    @Autowired
    private ServizioRepository servizioRepository;

    public Page<Servizio> getAllServizi(String nome,Pageable pageable){
        return this.servizioRepository.findAllServizi(nome,pageable);
    }
}
