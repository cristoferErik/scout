package com.checker.scout.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.checker.scout.entities.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long>{
    @Query("""
            select u from Utente u
            """)
    public List<Utente> findAllUtenti();
}
