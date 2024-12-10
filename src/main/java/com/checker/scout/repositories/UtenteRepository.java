package com.checker.scout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checker.scout.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long>{

}
