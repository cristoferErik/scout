package com.checker.scout.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.checker.scout.entities.Servizio;

public interface ServizioRepository extends JpaRepository<Servizio, Long>{

    @Query("""
            select s from Servizio s where :nome IS NULL or s.nome like %:nome%
            """)
    public Page<Servizio> findAllServizi(String nome,Pageable pageable); 
}
