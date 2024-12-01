package com.checker.scout.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checker.scout.controllers.hosting.interfaces.HostingInt;
import com.checker.scout.entities.Hosting;
import com.checker.scout.entities.Utente;
import com.checker.scout.repositories.HostingRepository;
import com.checker.scout.repositories.UtenteRepository;



@Service
public class HostingService {

    @Autowired
    private HostingRepository hostingRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly=true)
    public List<Hosting> getAllHostingsByUtente(Long utenteId) {
        return this.hostingRepository.findAllHostingByUtente(utenteId);
    }

    @Transactional
    public Map<String,Object> saveHosting(HostingInt.HostingDao hostingDao) {
        Hosting hosting = new Hosting();
        hosting.setId(hostingDao.getId());
        hosting.setNome(hostingDao.getNome());
        hosting.setUrl(hostingDao.getUrl());
        hosting.sethUsername(hostingDao.gethUsername());
        hosting.sethPassword(hostingDao.gethPassword());
        Optional<Utente> optUtente = this.utenteRepository.findById(hostingDao.getUtenteId());
        Map<String,Object> response= new HashMap<>();

        if (optUtente.isPresent()) {
            hosting.setUtente(optUtente.get());
            if (hosting.getId() == null) {
                hostingRepository.save(hosting);
                response.put("status","success");
                response.put("message","Hosting salvato successivamente!");
                return response;
            } else {
                Optional<Hosting> hostingOpt = hostingRepository.findById(hosting.getId());
                if (hostingOpt.isPresent()) {
                    hostingOpt.get().setNome(hosting.getNome());
                    hostingOpt.get().setUrl(hosting.getUrl());
                    hostingOpt.get().sethUsername(hosting.gethUsername());
                    hostingOpt.get().sethPassword(hosting.gethPassword());
                    hostingRepository.save(hostingOpt.get());
                    response.put("status","success");
                    response.put("message","Hosting aggiornato successivamente!");
                    return response;
                } else {
                    response.put("status","not_found");
                    response.put("message","Il Hosting non essiste nella base di dati!");
                    return response;
                }
            }
        } else {
            response.put("status","not_found");
            response.put("message","L'utente non esiste per cio, non si puo aggiornare il suo hosting!");
            return response;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Hosting> findHostingById(Long id){
        return this.hostingRepository.findById(id);
    }

    public Map<String,Object> deleteHosting(Long id){
        Optional<Hosting> optHosting=this.hostingRepository.findById(id);
        Map<String,Object> response= new HashMap<>();
        if(optHosting.isPresent()){
            this.hostingRepository.deleteById(id);
            response.put("status","success");
            response.put("message","Il hosting è stato eliminato successivamente!");
        }else{
            response.put("status","not_found");
            response.put("message","Il hosting non è stato trovato, per cio non ha potuto essere eliminato!");
        }
        return response;
    }
}
