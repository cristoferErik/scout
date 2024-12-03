package com.checker.scout.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checker.scout.entities.Servizio;
import com.checker.scout.repositories.ServizioRepository;

@Service
public class ServizioService {
    
    @Autowired
    private ServizioRepository servizioRepository;

    @Transactional(readOnly=true)
    public List<Servizio> getAllServizi(){
        return this.servizioRepository.findAllServizi();
    }

    @Transactional
    public boolean saveServizio(Servizio servizio){
        
        LocalDateTime localDateTime= LocalDateTime.now();
        if(servizio.getId() == null){
            servizio.setDataAggiornamento(localDateTime);
            servizio.setDataCreazione(localDateTime);
            servizioRepository.save(servizio);
            return true;
        }else{
            Optional<Servizio> servizioOpt = servizioRepository.findById(servizio.getId());
            if(servizioOpt.isPresent()){
                servizioOpt.get().setNome(servizio.getNome());
                servizioOpt.get().setCosto(servizio.getCosto());
                servizioOpt.get().setDescrizione(servizio.getDescrizione());
                servizioOpt.get().setDataAggiornamento(localDateTime);
                servizioRepository.saveAndFlush(servizioOpt.get());
                return true;
            }else{
                return false;
            }
        }
    }
    @Transactional
    public boolean  deleteServizio(Long id){
        Optional<Servizio> servizioOpt = servizioRepository.findById(id);
        if(servizioOpt.isPresent()){
            servizioRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    @Transactional(readOnly=true)
    public Optional<Servizio> getServizioById(Long id){
        Optional<Servizio> servizioOpt = servizioRepository.findById(id);
        return servizioOpt;
    }

    public List<Servizio> getAllServiziByWebSite(Long id){
        return servizioRepository.findAllServiziByWebSite(id);
    }
}
