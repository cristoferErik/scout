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

import com.checker.scout.entities.Hosting;
import com.checker.scout.services.HostingService;
import com.checker.scout.util.paginator.PageRender;

@RequestMapping("/hosting")
@Controller
public class HostingController {
    
    @Autowired
    private HostingService hostingService;

    @GetMapping("")
    public String getHostings(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam Map<String, String> params,
        Model model
        )
    {
        String nome=params.get("nome");
        String id=params.get("idUtente");
        String url = "/";
        url+=(nome != null && !nome.isEmpty())?"?nome="+nome:"";
        Long idUtente;
        if(id!=null && !id.isEmpty()){
            idUtente=Long.valueOf(id);
            Pageable pageRequest = PageRequest.of(page, 10);
            Page<Hosting> pageHosting = hostingService.getAllHostingsByUtente(nome, idUtente, pageRequest);
            PageRender<Hosting> hostingPageRender = new PageRender<>(url, pageHosting);
            hostingPageRender.rangeOfPage();
            model.addAttribute("page", hostingPageRender);
            model.addAttribute("listHosting", pageHosting);
            model.addAttribute("nome",nome);
            model.addAttribute("idUtente",idUtente);
            return "pages/hosting";
        }
        
        return "redirect:/utente";
    }
}
