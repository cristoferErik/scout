package com.checker.scout.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.checker.scout.entities.Servizio;

public interface ServizioRepository extends JpaRepository<Servizio, Long>{

    @Query("""
            select s from Servizio s
            """)
    public List<Servizio> findAllServizi(); 
}
