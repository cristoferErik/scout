package com.checker.scout.controllers.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.entities.projections.IWebSite;
import com.checker.scout.services.ServizioWebSiteService;

@RestController
@RequestMapping("/restHome")
public class HomeRestController {

    @Autowired
    private ServizioWebSiteService servizioWebSiteService;

    @GetMapping("/webSiteToUpdate")
    public List<IWebSite> getAllWebSiteToUpdate(){
        return servizioWebSiteService.getAllWebSiteToUpdate();
    }
}
