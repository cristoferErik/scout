package com.checker.scout.controllers.hosting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.hosting.interfaces.HostingInt;
import com.checker.scout.entities.Hosting;
import com.checker.scout.services.HostingService;
import com.checker.scout.util.paginator.PageRender;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/restHosting")
@RestController
public class HostingRestController {

    @Autowired
    private HostingService hostingService;

    @GetMapping("/hostingByUtente")
    public ResponseEntity<?> getServizi(
        @RequestParam (value= "page",defaultValue="0") Integer page,
        @RequestParam (value="size",defaultValue="10") Integer size,
        HttpSession session
    ){
        Map<String,Object> response= new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<Hosting> utentePage=hostingService.getAllHostingsByUtente(session,pageable);
         PageRender pageRender= new PageRender(page, utentePage.getTotalPages(),size);
        
        List<Integer> listNumbers= pageRender.getPageNumbers();
        
        Map<String,Object> pageLinks = pageRender.generatePageLink("/restHosting/hostingByUtente",listNumbers);
        response.put("status","success");
        response.put("body",utentePage.getContent());
        response.put("pageLinks", pageLinks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
