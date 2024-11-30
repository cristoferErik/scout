package com.checker.scout.controllers.hosting;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.hosting.interfaces.HostingInt;
import com.checker.scout.entities.Hosting;
import com.checker.scout.services.HostingService;

@RequestMapping("/restHosting")
@RestController
public class HostingRestController {

    @Autowired
    private HostingService hostingService;

    @GetMapping("/hosting")
    public List<Hosting> getServizi(@RequestParam Long id){
        return hostingService.getAllHostingsByUtente(id);
    }   
    
    @PostMapping("/saveHosting")
    public Map<String,Object> saveHosting(@RequestBody HostingInt.HostingDao hostingDao){
        System.out.println(hostingDao.getId());
        System.out.println(hostingDao.getUtenteId());
        Map<String,Object> response=hostingService.saveHosting(hostingDao);
        return response;
    }
}
