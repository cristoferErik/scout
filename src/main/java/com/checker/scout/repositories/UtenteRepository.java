package com.checker.scout.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.checker.scout.entities.Utente;
import com.checker.scout.entities.projections.IUtente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long>{
    @Query("""
            select u.id as id ,
            u.nome as nome,
            u.cognome as cognome,
            u.indirizzo as indirizzo,
            u.telefono as telefono,
            u.email as email
            from Utente u
            """)
    public List<IUtente> findAllUtenti();
}
