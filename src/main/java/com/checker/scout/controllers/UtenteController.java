package com.checker.scout.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.entities.Utente;
import com.checker.scout.services.UtenteService;
import com.checker.scout.util.paginator.PageRender;



@RequestMapping("/utente")
@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    @GetMapping
    public String getUtenti(@RequestParam(defaultValue = "0") int page,
            @RequestParam Map<String, String> params,
            Model model) {
            String nome=params.get("nome");
            String url = "/";
            url+=(nome != null && !nome.isEmpty())?"?nome="+nome:"";
            
            Pageable pageRequest = PageRequest.of(page, 10);
            Page<Utente> pageUtente = this.utenteService.getAllUtenti(nome,pageRequest);
            PageRender<Utente> utentePageRender = new PageRender<>(url, pageUtente);
            utentePageRender.rangeOfPage();
            model.addAttribute("page", utentePageRender);
            model.addAttribute("listUtente",pageUtente);
            
        return "pages/utente";
    }
    @PostMapping("/save_utente")
    public String saveUtente(
        @RequestParam Map<String, String> params,
        Model model
        ){
            String idUtente=params.get("idUtente");
            System.out.println("here ->"+idUtente);
        return "redirect:/utente";
    }
}
