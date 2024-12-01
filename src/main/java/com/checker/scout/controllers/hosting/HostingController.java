package com.checker.scout.controllers.hosting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/hosting")
@Controller
public class HostingController {
    
    @GetMapping("/listHosting")
    public String getHostings(@RequestParam Long id,Model model)
    {   
        //System.out.println("here - " + id); 
        model.addAttribute("utenteId", id);
        return "pages/hosting";
    }
}
