package com.checker.scout.controllers.utente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.entities.Utente;
import com.checker.scout.services.UtenteService;


@RestController
@RequestMapping("/restUtente")
public class UtenteRestController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/utenti")
    public List<Utente> getUtenti(){
         List<Utente> pageUtente = this.utenteService.getAllUtenti();
         return pageUtente;
    }

}
