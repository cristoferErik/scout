package com.checker.scout.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.checker.scout.entities.WebSite;

public interface SitiWebRepository extends JpaRepository<WebSite, Long>{
    @Query("""
            select w from WebSite w where (:nome is null or w.nome like %:nome%) and w.hosting.id = :hostingId
            """)
    public Page<WebSite> findAllWebSite(String nome,Long hostingId,Pageable pageable);
}
