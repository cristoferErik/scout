package com.checker.scout.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checker.scout.controllers.home.interfaces.HomeInt;
import com.checker.scout.controllers.website.interfaces.WebSiteInt;
import com.checker.scout.entities.Hosting;
import com.checker.scout.entities.WebSite;
import com.checker.scout.repositories.WebSiteRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class WebSiteService {

    @Autowired
    private WebSiteRepository webSiteRepository;

    @Autowired
    private EmailService emailService;

    public Page<WebSite> findAllWebSite(HttpSession session,Pageable pageable) {
        Long hostingId = (Long) session.getAttribute("hostingId");
        return webSiteRepository.findAllWebSite(hostingId,pageable);
    }

    @Transactional
    public Map<String, Object> saveWebSite(WebSiteInt.WebSiteDTO webSiteDTO, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            WebSite webSite = new WebSite();
            Long hostingId = (Long) session.getAttribute("hostingId");
            if(webSiteDTO.getWebSiteId()!=null){
                Optional<WebSite> webSiteOpt = webSiteRepository.findById(webSiteDTO.getWebSiteId());
                if(webSiteOpt.isPresent()){
                    webSite=webSiteOpt.get();
                    //Nel caso il webSite essista ma il hosting è diverso
                    //quindi non si realizara il aggiornamento
                    if(Objects.equals(webSite.getHosting().getId(), hostingId)){
                        webSite.setNome(webSiteDTO.getNome());
                        webSite.setUrl(webSiteDTO.getUrl());
                        webSite.setDataAggiornamento(webSiteDTO.getDataAggiornamento());
                        webSite.setDataBackup(webSiteDTO.getDataBackup());
                        webSite.setDescrizione(webSiteDTO.getDescrizione());
                        webSiteRepository.save(webSite);
                        response.put("status", "success");
                        response.put("message", "Il web site è stato aggiornato con successo!");
                        return response;
                    }else{
                        response.put("status", "not_found");
                        response.put("message", "Non si può salvare il sito web, per che il hosting non essiste oppure no hai accesso a questa risorsa!");
                        return response;
                    }
                }
            }else{
                        webSite.setId(webSiteDTO.getWebSiteId());
                        webSite.setNome(webSiteDTO.getNome());
                        webSite.setUrl(webSiteDTO.getUrl());
                        webSite.setDataAggiornamento(webSiteDTO.getDataAggiornamento());
                        webSite.setDataBackup(webSiteDTO.getDataBackup());
                        webSite.setDescrizione(webSiteDTO.getDescrizione());
                        Hosting hosting = new Hosting();
                        hosting.setId(hostingId);
                        webSite.setHosting(hosting);
                        webSiteRepository.save(webSite);

                        response.put("status", "success");
                        response.put("message", "Il web site è stato salvato con successo!");
                        return response;
            }
        } catch (Exception e) {
            response.put("status","bad_request");
            response.put("message",e.getMessage());
        }
        return response;
    }

    public Optional<WebSite> getWebSiteById(Long id) {
        Optional<WebSite> optWebSite = this.webSiteRepository.findWebSiteById(id);
        return optWebSite;
    }

    public Map<String, Object> deleteWebSite(Long id) {
        Optional<WebSite> optWebSite = this.webSiteRepository.findWebSiteById(id);
        Map<String, Object> response = new HashMap<>();
        if (optWebSite.isPresent()) {
            this.webSiteRepository.deleteById(id);
            response.put("status", "success");
            response.put("message", "WebSite eliminato successivamente!");
        } else {
            response.put("status", "not_found");
            response.put("message", "WebSite non ha potuto essere eliminato per che non essiste!");
        }
        return response;
    }

    public Page<WebSite> findAllWebSites(Pageable pageable) {
        return this.webSiteRepository.findAll(pageable);
    }

    public Map<String,Object> webSiteMessage(HomeInt.webSiteMessage webSiteMessage) {
        Map<String,Object> response= new HashMap<>();
        Optional<String>emailOPT=this.webSiteRepository.findEmailUtenteById(webSiteMessage.getWebSiteId());
        if(emailOPT.isPresent()){
            String email=emailOPT.get();
            emailService.sendEmail(webSiteMessage,email);
            response.put("status","success");
            response.put("message","L'email è stato inviato con successo!");
        }else{
            response.put("status","success");
            response.put("message","L'email non è potutto essere inviato!");
        }
        return response;
    }

    @Transactional
    public Map<String, Object> saveWebSiteIndex(WebSiteInt.WebSiteDTO webSiteDTO){
        Map<String, Object> response = new HashMap<>();
        try{
            
            Optional<WebSite> webSiteOPT=webSiteRepository.findById(webSiteDTO.getWebSiteId());
            WebSite webSite= webSiteOPT.get();
            webSite.setNome(webSiteDTO.getNome());
            webSite.setDescrizione(webSiteDTO.getDescrizione());
            webSite.setDataBackup(webSiteDTO.getDataBackup());
            webSite.setDataAggiornamento(webSiteDTO.getDataAggiornamento());
            webSite.setUrl(webSiteDTO.getUrl());
            
            webSiteRepository.save(webSite);
            response.put("status", "success");
            response.put("message", "Il web site è stato aggiornato con successo!");
        }catch(Exception e){
            response.put("status", "bad_request");
            response.put("message", "Il web site non è potuto essere salvato!");
            System.err.println("error "+e.getMessage());
        }
        return response;
    }
}
