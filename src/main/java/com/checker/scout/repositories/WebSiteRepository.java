package com.checker.scout.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.checker.scout.entities.WebSite;

@Repository
public interface WebSiteRepository extends JpaRepository<WebSite, Long>{
    @Query("""
            select w from WebSite w where  w.hosting.id = :HostingId
            """)
    public Page<WebSite> findAllWebSite(Long HostingId,Pageable pageable);
    
    @Query("""
            select w from WebSite w where w.id= :idWebSite
            """)
    public Optional<WebSite> findWebSiteById(Long idWebSite);
    
}
