package com.checker.scout.services;

import java.util.HashMap;
import java.util.Map;
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
import com.checker.scout.repositories.HostingRepository;
import com.checker.scout.repositories.WebSiteRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class WebSiteService {

    @Autowired
    private WebSiteRepository webSiteRepository;

    @Autowired
    private HostingRepository hostingRepository;

    public Page<WebSite> findAllWebSite(HttpSession session,Pageable pageable) {
        Long hostingId = (Long) session.getAttribute("hostingId");
        return webSiteRepository.findAllWebSite(hostingId,pageable);
    }

    @Transactional
    public Map<String, Object> saveWebSite(WebSiteInt.WebSiteDao webSiteDao, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            WebSite webSite = new WebSite();
            webSite.setId(webSiteDao.getWebSiteId());
            webSite.setNome(webSiteDao.getNome());
            webSite.setUrl(webSiteDao.getUrl());
            webSite.setDescrizione(webSiteDao.getDescrizione());
            webSite.setDataAggiornamento(webSiteDao.getDataAggiornamento());
            webSite.setDataBackup(webSiteDao.getDataBackup());

            Long hostingId = (Long) session.getAttribute("hostingId");
            boolean hostingFlag = hostingRepository.existsById(hostingId);
            if (!hostingFlag) {
                response.put("status", "not_found");
                response.put("message", "Non si può salvare il sito web, per che il hosting non essiste!");
                return response;
            } else {
                Hosting hosting = new Hosting();
                hosting.setId(hostingId);
                webSite.setHosting(hosting);
                this.webSiteRepository.save(webSite);
                response.put("status", "success");
                response.put("message", "Il sito web si è salvato con successo");
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

    public void webSiteMessage(HomeInt.webSiteMessage webSiteMessage) {
        System.out.println(webSiteMessage.getDescrizione());
    }
}
