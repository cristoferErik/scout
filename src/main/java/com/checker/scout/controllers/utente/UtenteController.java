package com.checker.scout.controllers.utente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/utente")
@Controller
public class UtenteController {


    @GetMapping
    public String getUtenti() {
        return "pages/utente";
    }
}
