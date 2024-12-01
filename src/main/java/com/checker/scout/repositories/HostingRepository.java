package com.checker.scout.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.checker.scout.entities.Hosting;

@Repository
public interface HostingRepository extends  JpaRepository<Hosting, Long>{
    @Query("""
            select h from Hosting h where  h.utente.id=:utenteId 
            """)
    public List<Hosting> findAllHostingByUtente(Long utenteId);
}