package com.checker.scout.controllers.servizio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.servizio.interfaces.ServizioInt;
import com.checker.scout.entities.Servizio;
import com.checker.scout.services.ServizioService;

@RequestMapping("/restServizio")
@RestController
public class ServizioRestController {

    @Autowired
    private ServizioService servizioService;


    @GetMapping("/servizi")
    public List<Servizio> getServizi() {
        return servizioService.getAllServizi();
    }

    @PostMapping("/saveServizio")
    public Map<String, Object> saveServizio(@RequestBody ServizioInt.ServizioDao servizioDao) {
        Servizio servizio = new Servizio();
        servizio.setId(servizioDao.getId());
        servizio.setNome(servizioDao.getNome());
        servizio.setDescrizione(servizioDao.getDescrizione());
        servizio.setCosto(servizioDao.getCosto());
        boolean flag = this.servizioService.saveServizio(servizio);
        Map<String, Object> response = new HashMap<>();

        if (flag) {
            response.put("status", "success");
            response.put("message", "Utente salvato correttamente!");
        } else {
            response.put("status", "error");
            response.put("message", "L'utente non si ha potuto salvare!");
        }
        return response;
    }

    @GetMapping("/servizio/{id}")
    public Map<String, Object> findServizioById(@PathVariable Long id) {
        Optional<Servizio> optServizio = this.servizioService.getServizioById(id);
        Map<String, Object> response = new HashMap<>();
        if (optServizio.isPresent()) {
            response.put("status", "success");
            response.put("body", optServizio.get());
        } else {
            response.put("status", "not_found");
            response.put("message", "Il servizio non essiste!");
        }
        return response;
    }

    @DeleteMapping("/servizio/{id}")
    public Map<String, Object> deleteServizioById(@PathVariable Long id) {
        boolean flag = this.servizioService.deleteServizio(id);
        Map<String, Object> response = new HashMap<>();
        if (flag) {
            response.put("status", "success");
            response.put("message", "L'utente é stato eliminato successivamente!");
        } else {
            response.put("status", "not_found");
            response.put("message", "L'utente che vuoi eliminare non essiste!");
        }
        return response;
    }

    @PostMapping("/serviziForWebSite")
    public Map<String,Object> saveServizioForWebSite(@RequestBody ServizioInt.ServizioForWebSite servizioForWebSite){
        Map<String,Object> response = new HashMap<>();
        if(servizioForWebSite.getServizioId()==null){
            response.put("status","bad_request");
            response.put("message","L'id del Servizio non puo essere vuoto!");
        }else if(servizioForWebSite.getWebSiteId()==null){
            response.put("status","bad_request");
            response.put("message","L'id del WebSite é vuoto prova ricaricare la pagina!");
        }else if(servizioForWebSite.getDateIni()==null){
            response.put("status","bad_request");
            response.put("message","La data iniziale é richiesta!");
        }else if(servizioForWebSite.getDateFine()==null){
            response.put("status","bad_request");
            response.put("message","La data finale é richiesta!");
        }else if(servizioForWebSite.getPeriodo()==null){
            response.put("status","bad_request");
            response.put("message","Il periodo é richiesto in giorni!");
        }
        else{
            response=this.servizioService.saveServizioForWebSite(servizioForWebSite);
        }
        return response;
    }
}
