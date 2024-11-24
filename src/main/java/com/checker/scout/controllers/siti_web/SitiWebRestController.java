package com.checker.scout.controllers.siti_web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.entities.WebSite;
import com.checker.scout.repositories.SitiWebRepository;

@RequestMapping("/restSitiWeb")
@RestController
public class SitiWebRestController {

    @Autowired
    private SitiWebRepository sitiWebRepository;
    @GetMapping("/hosting")
    public List<WebSite> getServizi(@RequestParam Long idHosting){
        return sitiWebRepository.findAllWebSite(idHosting);
    }
}
