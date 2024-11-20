package com.checker.scout.controllers.hosting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.entities.Hosting;
import com.checker.scout.services.HostingService;

@RequestMapping("/restHosting")
@RestController
public class HostingRestController {

    @Autowired
    private HostingService hostingService;

    @GetMapping("/hosting")
    public List<Hosting> getServizi(@RequestParam Long utenteId){
        return hostingService.getAllHostingsByUtente(utenteId);
    }
}
