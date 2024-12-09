package com.checker.scout.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checker.scout.security.gestori.Gestore;
import com.checker.scout.security.repository.GestoreRepository;


@Service
public class AuthService implements UserDetailsService{
    @Autowired
    private GestoreRepository gestoreRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void saveGestore(Gestore gestore){
        String password=passwordEncoder.encode(gestore.getPassword());
        gestore.setPassword(password);
        gestoreRepository.save(gestore);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Gestore> gestoreOpt = gestoreRepository.findByEmail(email);
        if (gestoreOpt.isPresent()) {
            Gestore gestore = gestoreOpt.get();
            return new User(gestore.getEmail(),gestore.getPassword(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
