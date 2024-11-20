package com.checker.scout.controllers.servizio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.entities.Servizio;
import com.checker.scout.services.ServizioService;

@RequestMapping("/restServizio")
@RestController
public class ServizioRestController {
    @Autowired
    private ServizioService servizioService;

    @GetMapping("/servizi")
    public List<Servizio> getServizi(){
        return servizioService.getAllServizi();
    }
}
