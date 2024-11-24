package com.checker.scout.controllers.servizio;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.entities.Servizio;
import com.checker.scout.services.ServizioService;


@RequestMapping("/servizio")
@Controller
public class ServizioController {

    @Autowired
    private ServizioService servizioService;
    
    @GetMapping()
    public String getServizi()
    {
        return "pages/servizi";
    }

    
    @PostMapping("/saveServizio")
    public String saveServizi(@RequestParam Map<String, String> params,Model model){
        String idServizioStr=params.get("idServizio");
        Long idServizio = (idServizioStr==null || idServizioStr.equals(""))? null:Long.valueOf(idServizioStr);
        String nome =params.get("nome");
        String descrizione =params.get("descrizione");
        Float costo =Float.valueOf(params.get("costo"));
        Servizio servizio= new Servizio();
        servizio.setId(idServizio);
        servizio.setNome(nome);
        servizio.setDescrizzione(descrizione);
        servizio.setCosto(costo);
        servizioService.saveServizio(servizio);
        return "redirect:/servizio";
    }

    @PostMapping("/deleteServizio")
    public String deleteServizio(@RequestParam Map<String, String> params,
                                Model model){
        String idServizioStr=params.get("idServizio");
        Long idServizio = (idServizioStr==null || idServizioStr.equals(""))? null:Long.valueOf(idServizioStr);
        servizioService.deleteServizio(idServizio);
        return "redirect:/servizio";
    }
}
