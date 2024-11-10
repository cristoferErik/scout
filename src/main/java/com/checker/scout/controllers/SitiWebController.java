package com.checker.scout.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.entities.WebSite;
import com.checker.scout.services.WebSiteService;
import com.checker.scout.util.paginator.PageRender;

@RequestMapping("/siti_web")
@Controller
public class SitiWebController {
    
    @Autowired
    private WebSiteService webSiteService;
    
    @GetMapping("")
    public String getWebSites(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam Map<String, String> params,
        Model model){
        String hostingId=params.get("idHosting");
        String nome=params.get("nome");
        String url = "/siti_web";
        url+=(nome != null && !nome.isEmpty())?"?nome="+nome:"";
        if(hostingId!=null && !hostingId.isEmpty()){
            Long id=Long.valueOf(hostingId);
            url+=url.contains("?")?"&idHosting="+id:"?idHosting="+id;
            Pageable pageRequest = PageRequest.of(page, 10);
            Page<WebSite> pageWebSite = this.webSiteService.getAllWebSites(nome, id, pageRequest);
            PageRender<WebSite> servizioPageRender = new PageRender<>(url, pageWebSite);
            servizioPageRender.rangeOfPage();
            model.addAttribute("page", servizioPageRender);
            model.addAttribute("listWebSites", pageWebSite);
            model.addAttribute("nome",nome);
            model.addAttribute("idHosting",id);
            return "pages/sitiweb";
        }
        return "redirect:/utente";
        
    }
}
