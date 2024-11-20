package com.checker.scout.services;

import java.util.List;

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
}
