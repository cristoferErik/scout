package com.checker.scout.controllers.home;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.services.ServizioWebSiteService;

@RequestMapping("/")
@Controller
public class HomeController {

    @Autowired
    private ServizioWebSiteService servizioWebSiteService;

    @GetMapping("/")
    public String home() {
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
