package com.checker.scout.controllers.utente;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.entities.Utente;
import com.checker.scout.services.UtenteService;



@RequestMapping("/utente")
@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getUtenti() {
        return "pages/utente";
    }

    @PostMapping("/saveUtente")
    public String saveUtente(
        @RequestParam Map<String, String> params,
        Model model
        ){
            String idUtenteStr=params.get("idUtente");
            Long idUtente = (idUtenteStr==null || idUtenteStr.equals(""))? null:Long.valueOf(idUtenteStr);
            String nomeUtente=params.get("nomeUtente");
            String cognome=params.get("cognome");
            String indirizzo= params.get("indirizzo");
            String telefono=params.get("telefono");
            String email = params.get("email");
            Utente utente=new Utente();
            utente.setId(idUtente);
            utente.setNome(nomeUtente);
            utente.setCognome(cognome);
            utente.setIndirizzo(indirizzo);
            utente.setTelefono(telefono);
            utente.setEmail(email);
            utenteService.saveUtente(utente);
        return "redirect:/utente";
    }
}
