package com.checker.scout.controllers.servizio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/servizio")
@Controller
public class ServizioController {



    @GetMapping()
    public String getServizi()
    {
        return "pages/servizi";
    }
}
