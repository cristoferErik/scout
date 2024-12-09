package com.checker.scout.controllers.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
