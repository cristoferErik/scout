package com.checker.scout.controllers.hosting;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.entities.Hosting;
import com.checker.scout.entities.Utente;
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
    @PostMapping("/saveHosting")
    public String saveHosting(@RequestParam Map<String, String> params,Model model){
        String idHostingStr=params.get("idHosting");
        Long idHosting = (idHostingStr==null || idHostingStr.equals(""))? null:Long.valueOf(idHostingStr);
        
        String idUtenteStr=params.get("idUtente");
        Long idUtente = (idUtenteStr==null || idUtenteStr.equals(""))? null:Long.valueOf(idUtenteStr);
        
        String nomeHosting=params.get("nomeHosting");
        String urlHosting=params.get("urlHosting");
        String hUsername=params.get("hUsername");
        String hPassword=params.get("hPassword");
        Utente utente = new Utente();

        utente.setId(idUtente);

        Hosting hosting = new Hosting();
        hosting.setId(idHosting);
        hosting.setNome(nomeHosting);
        hosting.setUrl(urlHosting);
        hosting.sethUsername(hUsername);
        hosting.sethPassword(hPassword);
        hosting.setUtente(utente);

        hostingService.saveHosting(hosting);

        
        model.addAttribute("idUtente", idUtente);
        return "redirect:/hosting?idUtente=" + idUtente;
    }
}
