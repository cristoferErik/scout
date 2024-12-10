package com.checker.scout.controllers.home;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.home.interfaces.HomeInt.webSiteMessage;
import com.checker.scout.entities.WebSite;
import com.checker.scout.services.WebSiteService;
import com.checker.scout.util.paginator.PageRender;

@RestController
public class HomeRestController {

    @Autowired
    private WebSiteService webSiteService;

    @GetMapping("/web_sites")
    public ResponseEntity<?> getAllWebSiteToUpdate(
        @RequestParam (value= "page",defaultValue="0") Integer page,
        @RequestParam (value="size",defaultValue="10") Integer size){
        Map<String,Object> response= new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<WebSite> webSitePage=webSiteService.findAllWebSites(pageable);
        
        PageRender pageRender= new PageRender(page, webSitePage.getTotalPages(),size);
        
        List<Integer> listNumbers= pageRender.getPageNumbers();
        
        Map<String,Object> pageLinks = pageRender.generatePageLink("/restHome/webSiteToUpdate",listNumbers);

        response.put("status","success");
        response.put("body",webSitePage.getContent());
        response.put("pageLinks", pageLinks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody webSiteMessage webSiteMessage){
        Map<String,Object> response= new HashMap<>();
        response.put("status","success");
        response.put("message","Messagio inviato con successo!");
        webSiteService.webSiteMessage(webSiteMessage);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
