package com.checker.scout.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.entities.projections.IWebSite;
import com.checker.scout.services.ServizioWebSiteService;
import com.checker.scout.util.paginator.PageRender;

@RequestMapping("/")
@Controller
public class HomeController {

    @Autowired
    private ServizioWebSiteService servizioWebSiteService;

    @GetMapping("/")
    public String home(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Map<String, String> params,
            Model model
    ) {
            String nome=params.get("nome");
            String d=params.get("days");
        
            String url = "/";
            url+=(nome != null && !nome.isEmpty())?"?nome="+nome:"";
            //Questo se non ce nessun giorno, si aggiunge uno per default!
            int days=(d!=null && !d.isEmpty())?Integer.parseInt(d):10;
            //Se il valore e menore di 10,ti tornera 10, in caso contrario sarà lo stesso giorno
            days=days<10?10:days;
            url+=url.contains("?")?"&days="+d:"?days="+days;

            System.out.println(url);
            Pageable pageRequest = PageRequest.of(page, 10);
            Page<IWebSite> pageWebSite = this.servizioWebSiteService.getAllWebSiteToUpdate(nome,days,pageRequest);
            PageRender<IWebSite> webSitePageRender = new PageRender<>(url, pageWebSite);

            webSitePageRender.rangeOfPage();
            model.addAttribute("page", webSitePageRender);
            model.addAttribute("listWebSite", pageWebSite);
            model.addAttribute("nome",nome);
            model.addAttribute("days",days);
            return "index";
    }
    //Salvare aggiornamento di sitoWeb
    @PostMapping("/saveAggSw")
    public String saveAggSitoWeb(@RequestParam Map<String, String> params){
        String idSitoWeb=params.get("idSitoWeb");
        String date=params.get("date");
        String nomeWebSite=params.get("nomeWebSite");
        System.out.println(idSitoWeb + " " + date+ " "+nomeWebSite);
        return "redirect:/";
    }
}
