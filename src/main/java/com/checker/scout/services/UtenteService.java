package com.checker.scout.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.Utente;
import com.checker.scout.repositories.UtenteRepository;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public List<Utente> getAllUtenti(){
        return this.utenteRepository.findAllUtenti();
    }
}
