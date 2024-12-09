package com.checker.scout.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.checker.scout.security.gestori.Gestore;


public interface GestoreRepository extends  JpaRepository<Gestore,Long>{
    Optional<Gestore> findByEmail(String email);
}
