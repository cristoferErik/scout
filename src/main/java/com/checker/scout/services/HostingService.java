package com.checker.scout.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checker.scout.controllers.hosting.interfaces.HostingInt;
import com.checker.scout.entities.Hosting;
import com.checker.scout.entities.Utente;
import com.checker.scout.repositories.HostingRepository;
import com.checker.scout.repositories.UtenteRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class HostingService {

    @Autowired
    private HostingRepository hostingRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly = true)
    public Page<Hosting> getAllHostingsByUtente(HttpSession session,Pageable pageable) {
        Long utenteId = (Long) session.getAttribute("utenteId");
        return this.hostingRepository.findAllHostingByUtente(utenteId,pageable);
    }

    @Transactional
    public Map<String, Object> saveHosting(HostingInt.HostingDao hostingDao, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            Hosting hosting = new Hosting();
            hosting.setId(hostingDao.getId());
            hosting.setNome(hostingDao.getNome());
            hosting.setNetsonUrl(hostingDao.getNetsonUrl());
            hosting.setUrl(hostingDao.getUrl());
            hosting.sethUsername(hostingDao.gethUsername());
            hosting.sethPassword(hostingDao.gethPassword());
            Long utenteId = (Long) session.getAttribute("utenteId");
            Utente utente = new Utente();
            utente.setId(utenteId);
            hosting.setUtente(utente);
            hostingRepository.save(hosting);
            response.put("status", "success");
            response.put("message", "Hosting salvato successivamente!");
        } catch (Exception e) {
            response.put("status", "bad_request");
            response.put("message","C'è stato un errore, non si è potuto salvare il hosting!");
            System.err.println(e.getMessage());
        }
        return response;

    }

    @Transactional(readOnly = true)
    public Optional<Hosting> findHostingById(Long id) {
        return this.hostingRepository.findById(id);
    }

    public Map<String, Object> deleteHosting(Long id) {
        Optional<Hosting> optHosting = this.hostingRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (optHosting.isPresent()) {
            this.hostingRepository.deleteById(id);
            response.put("status", "success");
            response.put("message", "Il hosting è stato eliminato successivamente!");
        } else {
            response.put("status", "not_found");
            response.put("message", "Il hosting non è stato trovato, per cio non ha potuto essere eliminato!");
        }
        return response;
    }
}
