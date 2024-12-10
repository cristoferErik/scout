package com.checker.scout.controllers.utente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.utente.interfaces.UtenteInt;
import com.checker.scout.entities.Utente;
import com.checker.scout.services.UtenteService;
import com.checker.scout.util.paginator.PageRender;


@RestController
@RequestMapping("/restUtente")
public class UtenteRestController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/utenti")
    public ResponseEntity<?> getUtenti(
        @RequestParam (value= "page",defaultValue="0") Integer page,
        @RequestParam (value="size",defaultValue="10") Integer size
        ){
        Map<String,Object> response= new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<Utente> utentePage=utenteService.findAllUtenti(pageable);
        
        PageRender pageRender= new PageRender(page, utentePage.getTotalPages(),size);
        
        List<Integer> listNumbers= pageRender.getPageNumbers();
        
        Map<String,Object> pageLinks = pageRender.generatePageLink("/restHome/webSiteToUpdate",listNumbers);

        response.put("status","success");
        response.put("body",utentePage.getContent());
        response.put("pageLinks", pageLinks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/utente/{id}")
    public ResponseEntity<?> getUtenteById(@PathVariable Long id){
        Optional<Utente> optUtente=this.utenteService.getUtenteById(id);
        Map<String, Object> response = new HashMap<>();
        if(optUtente.isPresent()){
            response.put("status","success");
            response.put("data",optUtente.get());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            response.put("status","not_found");
            response.put("message","Utente non trovato!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveUtente(@RequestBody UtenteInt.utenteDao utenteDao){
        Utente utente=new Utente();
        utente.setId(utenteDao.getId());
        utente.setNome(utenteDao.getNome());
        utente.setCognome(utenteDao.getCognome());
        utente.setEmail(utenteDao.getEmail());
        utente.setIndirizzo(utenteDao.getIndirizzo());
        utente.setTelefono(utenteDao.getTelefono());
        this.utenteService.saveUtente(utente);
        Map<String, Object> response = new HashMap<>();
        response.put("message","Utente salvato correttamente!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/utente/{id}")
    public Map<String,Object> deleteUtente(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        
        boolean flag=this.utenteService.deleteUtente(id);
        if(flag){
            response.put("status","success");
            response.put("message","Utente eliminato correttamente!");
        }else{
            response.put("status","not_found");
            response.put("message","L'utente non essiste!");
        }
        return response;
    }
}
