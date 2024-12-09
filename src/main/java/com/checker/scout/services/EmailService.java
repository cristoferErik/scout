package com.checker.scout.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.checker.scout.security.repository.GestoreRepository;

@Service
public class EmailService {
    
    @Autowired
    private GestoreRepository gestoreRepository;
    public void sendEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        


    }
}
