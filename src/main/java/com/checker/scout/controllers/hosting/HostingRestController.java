package com.checker.scout.controllers.hosting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.hosting.interfaces.HostingInt;
import com.checker.scout.entities.Hosting;
import com.checker.scout.services.HostingService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/restHosting")
@RestController
public class HostingRestController {

    @Autowired
    private HostingService hostingService;

    @GetMapping("/hostingByUtente")
    public List<Hosting> getServizi(HttpSession session){
        return hostingService.getAllHostingsByUtente(session);
    }   
    
    @PostMapping("/saveHosting")
    public Map<String,Object> saveHosting(@RequestBody HostingInt.HostingDao hostingDao,HttpSession session){
        System.out.println(hostingDao.getId());
        System.out.println(hostingDao.getNetsonUrl());
        Map<String,Object> response=hostingService.saveHosting(hostingDao,session);
        return response;
    }

    @GetMapping("/hosting/{id}")
    public Map<String,Object> findHosting(@PathVariable Long id){
        Optional<Hosting> optHosting = this.hostingService.findHostingById(id);
        Map<String,Object> response= new HashMap<>();
        if(optHosting.isPresent()){
            response.put("status","success");
            response.put("body",optHosting.get());
        }else{
            response.put("status","not_found");
            response.put("message","L'utente che stai cercando non essiste!");
        }
        return response;
    }

    @DeleteMapping("/hosting/{id}")
    public Map<String,Object> deleteHosting(@PathVariable Long id){
        return this.hostingService.deleteHosting(id);
    }
    
}
