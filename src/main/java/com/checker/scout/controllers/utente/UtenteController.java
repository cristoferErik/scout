package com.checker.scout.controllers.utente;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping("/utente")
@Controller
public class UtenteController {

 
    @GetMapping
    public String getUtenti() {
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
