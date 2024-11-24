package com.checker.scout.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.Hosting;
import com.checker.scout.repositories.HostingRepository;

@Service
public class HostingService {
    @Autowired
    private HostingRepository hostingRepository;

    public List<Hosting> getAllHostingsByUtente(Long utenteId){
        return this.hostingRepository.findAllHostingByUtente(utenteId);
    }
    public boolean saveHosting(Hosting hosting){
        if(hosting.getId()==null){
            hostingRepository.save(hosting);
            return true;
        }else{
            Optional<Hosting> hostingOpt = hostingRepository.findById(hosting.getId());
            if(hostingOpt.isPresent()){
                hostingOpt.get().setNome(hosting.getNome());
                hostingOpt.get().setUrl(hosting.getUrl());
                hostingOpt.get().sethUsername(hosting.gethUsername());
                hostingOpt.get().sethPassword(hosting.gethPassword());
                hostingRepository.save(hostingOpt.get());
                return true;
            }else{
                return false;
            }
        }
    }
}
