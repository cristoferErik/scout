package com.checker.scout.controllers.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.services.WebSiteService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/website")
@Controller
public class WebSiteController {
    
    @Autowired
    private WebSiteService webSiteService;
    
    @GetMapping("")
    public String getWebSites(@RequestParam Long hostingId, HttpSession session){
            session.setAttribute("hostingId", hostingId);
            return "pages/sitiweb";
        }
}
