package com.checker.scout.controllers.website;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.website.interfaces.WebSiteInt;
import com.checker.scout.entities.WebSite;
import com.checker.scout.services.WebSiteService;

@RequestMapping("/restWebSite")
@RestController
public class WebSiteRestController {

    @Autowired
    private WebSiteService webSiteService;

    @GetMapping("/webSites")
    public List<WebSite> getServizi(@RequestParam Long idHosting){
        return webSiteService.findAllWebSite(idHosting);
    }

    @PostMapping("/saveWebSite")
    public Map<String,Object> saveWebSite(@RequestBody WebSiteInt.WebSiteDao webSiteDao){
        return webSiteService.saveWebSite(webSiteDao);
    }

    @GetMapping("/webSite/{id}")
    public Map<String,Object> getWebSiteById(@RequestParam Long id){
        Optional<WebSite> optWebSite=webSiteService.getWebSiteById(id);
        Map<String,Object> response= new HashMap<>();
        if(optWebSite.isPresent()){
            response.put("status","success");
            response.put("body",optWebSite.get());
        }else{
            response.put("status", "not_found");
            response.put("message","Il web site non si é trovato nella base di dati");
        }
        return response;
    }

    @DeleteMapping("/webSite/{id}")
    public Map<String,Object> deleteWebSite(@RequestParam Long id){
        return this.webSiteService.deleteWebSite(id);
    }
}
