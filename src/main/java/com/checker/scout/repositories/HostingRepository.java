package com.checker.scout.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.checker.scout.entities.Hosting;

@Repository
public interface HostingRepository extends  JpaRepository<Hosting, Long>{
    @Query("""
            select h from Hosting h where  h.utente.id=:utenteId 
            """)
    public Page<Hosting> findAllHostingByUtente(Long utenteId,Pageable pageable);
}