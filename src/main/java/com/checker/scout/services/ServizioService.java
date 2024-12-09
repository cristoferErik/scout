package com.checker.scout.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checker.scout.controllers.servizio.interfaces.ServizioInt;
import com.checker.scout.entities.DetailWsSe;
import com.checker.scout.entities.Servizio;
import com.checker.scout.entities.WebSite;
import com.checker.scout.entities.projections.IDetailWsSe;
import com.checker.scout.repositories.DetailWsSeRepository;
import com.checker.scout.repositories.ServizioRepository;
import com.checker.scout.repositories.WebSiteRepository;

@Service
public class ServizioService {

    @Autowired
    private ServizioRepository servizioRepository;

    @Autowired
    private WebSiteRepository webSiteRepository;

    @Autowired
    private DetailWsSeRepository detailWsSeRepository;

    @Transactional(readOnly = true)
    public List<Servizio> getAllServizi() {
        return this.servizioRepository.findAllServizi();
    }

    @Transactional
    public boolean saveServizio(Servizio servizio) {

        LocalDateTime localDateTime = LocalDateTime.now();
        if (servizio.getId() == null) {
            servizio.setDataAggiornamento(localDateTime);
            servizio.setDataCreazione(localDateTime);
            servizioRepository.save(servizio);
            return true;
        } else {
            Optional<Servizio> servizioOpt = servizioRepository.findById(servizio.getId());
            if (servizioOpt.isPresent()) {
                servizioOpt.get().setNome(servizio.getNome());
                servizioOpt.get().setCosto(servizio.getCosto());
                servizioOpt.get().setDescrizione(servizio.getDescrizione());
                servizioOpt.get().setDataAggiornamento(localDateTime);
                servizioRepository.saveAndFlush(servizioOpt.get());
                return true;
            } else {
                return false;
            }
        }
    }

    @Transactional
    public boolean deleteServizio(Long id) {
        Optional<Servizio> servizioOpt = servizioRepository.findById(id);
        if (servizioOpt.isPresent()) {
            servizioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Servizio> getServizioById(Long id) {
        Optional<Servizio> servizioOpt = servizioRepository.findById(id);
        return servizioOpt;
    }

    @Transactional
    public Map<String, Object> saveServizioForWebSite(ServizioInt.ServizioForWebSite servizioForWebSite) {
        boolean detailFlag=false;
        if(servizioForWebSite.getId()!=null){
            detailFlag=detailWsSeRepository.existsById(servizioForWebSite.getId());
        }
        
        boolean servizioFlag = servizioRepository.existsById(servizioForWebSite.getServizioId());
        boolean webSiteOpt = webSiteRepository.existsById(servizioForWebSite.getWebSiteId());

        Map<String, Object> response = new HashMap<>();

        //Qui si verifica se gia essiste il servizio collegato con il sitoWeb
        IDetailWsSe.webSiteServiceIdsDto superKey= new IDetailWsSe.webSiteServiceIdsDto(servizioForWebSite.getServizioId(),servizioForWebSite.getWebSiteId());
        boolean flagSuperKey=detailWsSeRepository.isExist(superKey);
        
        //Si sono falso quindi se restituirà questo errore
        if(!flagSuperKey && !detailFlag){
            response.put("status", "conflict");
            response.put("message", "Il servizio che vuoi aggiungere gia essiste in questo sito web!");
            return response;
        }

        if (!webSiteOpt) {
            response.put("status", "not_found");
            response.put("message", "Il webSite non é stato trovato nel database!");
            return response;
        }else if (!servizioFlag) {
            response.put("status", "not_found");
            response.put("message", "Il servizio non é stato trovato nel database!");
            return response;
        }
        
        LocalDate dateIni = servizioForWebSite.getDateIni();
        LocalDate dateFine = servizioForWebSite.getDateFine();
        int periodo = servizioForWebSite.getPeriodo();

        LocalDate prossimoAgg = dateIni.plusDays(periodo);

        //Si verifica se la dataFine sia maiore di dataIni
        if (dateFine.isAfter(dateIni)) {
            //Si verifica che il prossimo aggiornamento, si trovi tra dateIni e dateFine
            if (prossimoAgg.isBefore(dateFine) && prossimoAgg.isAfter(dateIni)) {
                DetailWsSe detailWsSe = new DetailWsSe();
                if(detailFlag) detailWsSe.setId(servizioForWebSite.getId());
                
                WebSite webSite = new WebSite();
                webSite.setId(servizioForWebSite.getWebSiteId());
                Servizio servizio =new Servizio();
                servizio.setId(servizioForWebSite.getServizioId());
                detailWsSe.setWebSite(webSite);
                detailWsSe.setServizio(servizio);
                detailWsSe.setDateIni(servizioForWebSite.getDateIni());
                detailWsSe.setDateFine(servizioForWebSite.getDateFine());
                detailWsSe.setPeriodo(servizioForWebSite.getPeriodo());
                detailWsSe.setDescrizione(servizioForWebSite.getDescrizione());

                detailWsSe.setProssimoAgg(prossimoAgg);
                if(!detailFlag)detailWsSe.setStatus(true);
                else detailWsSe.setStatus(servizioForWebSite.getStatus());
                
                //TO-DO
                detailWsSe.setMessage("da compilare");
                 
                
                detailWsSeRepository.save(detailWsSe);
                response.put("status", "success");
                response.put("message", "salvato con successo!");
            }else{
                response.put("status", "bad_request");
                response.put("message", "Il periodo si deve trovare tra le data iniziale e finale");
                Map<String,Object> res=new HashMap<>();
                res.put("data_iniziale",dateIni);
                res.put("periodo", prossimoAgg);
                res.put("data_finale",dateFine);
                response.put("body",res);
            }
        }else{
            response.put("status", "bad_request");
            response.put("message", "La data Fine deve di essere maiore alla dataIni!");
            Map<String,Object> res=new HashMap<>();
            res.put("data_iniziale",dateIni);
            res.put("data_finale",dateFine);
            response.put("body",res);
        }
        return response;
    }

    @Transactional(readOnly=true)
    public Map<String,Object> getServizioForWebSiteById(Long id){
        Optional<DetailWsSe> detailWsSeOpt =detailWsSeRepository.findById(id);
        Map<String,Object> response = new HashMap<>();
        if(detailWsSeOpt.isPresent()){
            response.put("status","success");
            response.put("body", detailWsSeOpt.get());
        }else{
            response.put("status","not_found");
            response.put("message", "La riga che sta cercando non essiste!");
        }
        return response;
    }

    @Transactional
    public Map<String,Object> deleteServizioForWebSiteById(Long id){
        Map<String,Object> response = new HashMap<>();
        try {
            detailWsSeRepository.deleteById(id);
            response.put("status", "success");
            response.put("message","servizio eliminato con successo!");
        } catch (IllegalArgumentException e) {
            response.put("status", "not_found");
            response.put("message",e.getMessage());
        }
        return response;
        
    }
}
