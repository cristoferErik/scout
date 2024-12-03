package com.checker.scout.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.checker.scout.entities.Servizio;

@Repository
public interface ServizioRepository extends JpaRepository<Servizio, Long>{

    @Query("""
            select s from Servizio s
            """)
    public List<Servizio> findAllServizi(); 

    @Query("""
            select s from Servizio s where s.id not in (select d.servizio.id from DetailWsSe d inner join d.webSite w where w.id=:id) 
            """)
    public List<Servizio> findAllServiziByWebSite(Long id);
}
