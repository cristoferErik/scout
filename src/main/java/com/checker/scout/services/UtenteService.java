package com.checker.scout.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checker.scout.entities.Utente;
import com.checker.scout.entities.projections.IUtente;
import com.checker.scout.repositories.UtenteRepository;



@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly=true)
    public List<IUtente> getAllUtenti(){
        return this.utenteRepository.findAllUtenti();
    }

    @Transactional
    public boolean  saveUtente(Utente utente){
        if(utente.getId()==null){
            utenteRepository.save(utente);
            return true;
        }else{
            Optional<Utente> utenteOpt = utenteRepository.findById(utente.getId());
            if(utenteOpt.isPresent()){
                utenteOpt.get().setNome(utente.getNome());
                utenteOpt.get().setCognome(utente.getCognome());
                utenteOpt.get().setEmail(utente.getEmail());
                utenteOpt.get().setIndirizzo(utente.getIndirizzo());
                utenteOpt.get().setTelefono(utente.getTelefono());
                utenteRepository.save(utenteOpt.get());
                return true;
            }else{
                return false;
            }
        }
        
    }

    @Transactional(readOnly=true)
    public Optional<Utente> getUtenteById(Long id){
        return utenteRepository.findById(id);
    }

    @Transactional
    public boolean deleteUtente(Long id){
        Optional<Utente> utenteOpt = utenteRepository.findById(id);
        if(utenteOpt.isPresent()){
            utenteRepository.delete(utenteOpt.get());
            return true;
        }else{
            return false;
        }
    }
}
