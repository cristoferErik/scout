package com.checker.scout.controllers.hosting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.services.HostingService;

@RequestMapping("/hosting")
@Controller
public class HostingController {
    
    @Autowired
    private HostingService hostingService;

    @GetMapping("")
    public String getHostings(@RequestParam Long idUtente,Model model)
    {   
        System.out.println("here - " + idUtente); 
        model.addAttribute("idUtente", idUtente);
        return "pages/hosting";
    }
}
