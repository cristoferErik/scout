package com.checker.scout.controllers.siti_web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.services.WebSiteService;

@RequestMapping("/siti_web")
@Controller
public class SitiWebController {
    
    @Autowired
    private WebSiteService webSiteService;
    
    @GetMapping("")
    public String getWebSites(
        @RequestParam Map<String, String> params,
        Model model){
            return "pages/sitiweb";
        }
}
