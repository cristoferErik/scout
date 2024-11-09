package com.checker.scout.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.checker.scout.entities.Hosting;
import com.checker.scout.repositories.HostingRepository;

@Service
public class HostingService {
    @Autowired
    private HostingRepository hostingRepository;

    public Page<Hosting> getAllHostingsByUtente(String nome, Long id,Pageable pageable){
        return this.hostingRepository.findAllHostingByUtente(nome, id, pageable);
    }
}
