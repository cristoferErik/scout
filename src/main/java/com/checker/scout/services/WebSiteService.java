package com.checker.scout.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checker.scout.controllers.website.interfaces.WebSiteInt;
import com.checker.scout.entities.Hosting;
import com.checker.scout.entities.WebSite;
import com.checker.scout.entities.projections.IDetailWsSe;
import com.checker.scout.repositories.DetailWsSeRepository;
import com.checker.scout.repositories.HostingRepository;
import com.checker.scout.repositories.WebSiteRepository;



@Service
public class WebSiteService {
    @Autowired
    private WebSiteRepository webSiteRepository;

    @Autowired
    private HostingRepository hostingRepository;

    @Autowired
    private DetailWsSeRepository detailWsSeRepository;

     public List<WebSite> findAllWebSite(Long idHosting){
        return webSiteRepository.findAllWebSite(idHosting);
    }
    
    @Transactional
    public Map<String,Object> saveWebSite(WebSiteInt.WebSiteDao webSiteDao){
        WebSite webSite= new WebSite();
        webSite.setId(webSiteDao.getId());
        webSite.setNome(webSiteDao.getNome());
        webSite.setUrl(webSiteDao.getUrl());
        webSite.setDescrizione(webSiteDao.getDescrizione());

        Map<String,Object> response = new HashMap<>();
        if(webSiteDao.getHostingId()!=null){
            Optional<Hosting> optHosting = hostingRepository.findById(webSiteDao.getHostingId());
         
            if(optHosting.isPresent()){
                webSite.setHosting(optHosting.get());
                webSiteRepository.save(webSite);
                response.put("status","success");
                response.put("message","Il web site é stato salvato successivamente!");
            }else{
                response.put("status", "not_found");
                response.put("message","Il web Site non é potuto essere salvato per che il hosting no é valido!");    
            }
        }else{
            response.put("status", "not_found");
            response.put("message","Il web Site non é potuto essere salvato per che il hosting no é valido!"); 
        }
       
        return response;
    }

    public Optional<WebSite> getWebSiteById(Long id){
        Optional<WebSite> optWebSite = this.webSiteRepository.findWebSiteById(id);
        return optWebSite;
    }

    public Map<String,Object> deleteWebSite(Long id){
        Optional<WebSite> optWebSite = this.webSiteRepository.findWebSiteById(id);
        Map<String,Object> response= new HashMap<>();
        if(optWebSite.isPresent()){
            this.webSiteRepository.deleteById(id);
            response.put("status", "success");
            response.put("message", "WebSite eliminato successivamente!");
        }else{
            response.put("status", "not_found");
            response.put("message", "WebSite non ha potuto essere eliminato per che non essiste!");
        }
        return response;
    }

    @Transactional(readOnly=true)
    public Page<IDetailWsSe.HistorialService> historialServiceByWebSite(Long webSiteId,Pageable pageable){
        return this.detailWsSeRepository.findAllHistorialServicesByWebSiteId(webSiteId, pageable);
    }
}
