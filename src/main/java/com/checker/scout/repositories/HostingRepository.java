package com.checker.scout.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.checker.scout.entities.Hosting;

public interface HostingRepository extends  JpaRepository<Hosting, Long>{
    @Query("""
            select h from Hosting h where (:nome is NULL or h.nome LIKE %:nome%) and h.utente.id=:utenteId 
            """)
    public Page<Hosting> findAllHostingByUtente(String nome,Long utenteId,Pageable pageable);
}