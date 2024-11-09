package com.checker.scout.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.checker.scout.entities.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long>{
    @Query("""
            select u from Utente u where :nome is NULL OR u.nome like %:nome%
            """)
    public Page<Utente> findAllUtenti(String nome,Pageable pageable);
}
