package com.checker.scout.controllers.hosting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/hosting")
@Controller
public class HostingController {
    
    @GetMapping("/listHosting")
    public String getHostings(@RequestParam Long utenteId,HttpSession session)
    {   
        //System.out.println("here - " + id); 
        session.setAttribute("utenteId", utenteId);
        return "pages/hosting";
    }
}
