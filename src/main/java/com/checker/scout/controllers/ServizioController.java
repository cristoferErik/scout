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

import com.checker.scout.entities.Servizio;
import com.checker.scout.services.ServizioService;
import com.checker.scout.util.paginator.PageRender;

@RequestMapping("/servizio")
@Controller
public class ServizioController {

    @Autowired
    private ServizioService servizioService;

    @GetMapping()
    public String getServizi( 
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Map<String, String> params,
            Model model)
        {
            String nome=params.get("nome");
            String url = "/";
            url+=(nome != null && !nome.isEmpty())?"?nome="+nome:"";
            
            Pageable pageRequest = PageRequest.of(page, 10);
            Page<Servizio> pageServizio = this.servizioService.getAllServizi(nome,pageRequest);
            PageRender<Servizio> servizioPageRender = new PageRender<>(url, pageServizio);
            servizioPageRender.rangeOfPage();
            model.addAttribute("page", servizioPageRender);
            model.addAttribute("listServizi",pageServizio);
            return "pages/servizi";
        }
}
