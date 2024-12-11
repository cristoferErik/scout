package com.checker.scout.controllers.website;

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

import com.checker.scout.controllers.website.interfaces.WebSiteInt;
import com.checker.scout.entities.WebSite;
import com.checker.scout.services.WebSiteService;
import com.checker.scout.util.paginator.PageRender;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/restWebSite")
@RestController
public class WebSiteRestController {

    @Autowired
    private WebSiteService webSiteService;


    @GetMapping("/webSitesByhosting")
    public ResponseEntity<?> getServizi(
        @RequestParam (value= "page",defaultValue="0") Integer page,
        @RequestParam (value="size",defaultValue="10") Integer size,
        HttpSession session
        ) {
        Map<String,Object> response= new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<WebSite> webSitePage= webSiteService.findAllWebSite(session, pageable);
        PageRender pageRender = new PageRender(page, webSitePage.getTotalPages(), size);
        List<Integer> listNumbers= pageRender.getPageNumbers();
        
        Map<String,Object> pageLinks = pageRender.generatePageLink("/webSitesByhosting/webSitesByhosting",listNumbers);

        response.put("status","success");
        response.put("body",webSitePage.getContent());
        response.put("pageLinks", pageLinks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/saveWebSite")
    public Map<String, Object> saveWebSite(@RequestBody WebSiteInt.WebSiteDTO webSiteDTO,HttpSession session) {
        return webSiteService.saveWebSite(webSiteDTO,session);
    }

    @GetMapping("/webSite/{id}")
    public Map<String, Object> getWebSiteById(@PathVariable Long id) {
        Optional<WebSite> optWebSite = this.webSiteService.getWebSiteById(id);
        Map<String, Object> response = new HashMap<>();
        if (optWebSite.isPresent()) {
            response.put("status", "success");
            response.put("body", optWebSite.get());
        } else {
            response.put("status", "not_found");
            response.put("message", "Il web site non si é trovato nella base di dati");
        }
        return response;
    }

    @DeleteMapping("/webSite/{id}")
    public Map<String, Object> deleteWebSite(@PathVariable Long id) {
        return this.webSiteService.deleteWebSite(id);
    }

    @PostMapping("/saveWebSiteIndex")
    public Map<String, Object> saveWebSiteIndex(@RequestBody WebSiteInt.WebSiteDTO webSiteDTO) {
        return webSiteService.saveWebSiteIndex(webSiteDTO);
    }
}
