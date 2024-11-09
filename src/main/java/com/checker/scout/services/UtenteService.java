package com.checker.scout.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.Utente;
import com.checker.scout.repositories.UtenteRepository;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Page<Utente> getAllUtenti(String nome,Pageable pageable){
        return this.utenteRepository.findAllUtenti(nome, pageable);
    }
}
