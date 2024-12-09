package com.checker.scout.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.checker.scout.entities.WebSite;
import com.checker.scout.entities.projections.IWebSite;

@Repository
public interface ServizioWebSiteRepository extends JpaRepository<WebSite, Long> {

    @Query("""
         select 
           w.id as id, 
           w.nome as nomeWebSite,
           d.prossimoAgg as prossimoAgg, 
           s.nome as nomeServizio
        from WebSite w
        inner join DetailWsSe d on w.id = d.webSite.id
        inner join Servizio s on d.servizio.id = s.id
        where d.status=true
    """)
    Page<IWebSite.WebSiteP> findAllWebSiteToUpdate(Pageable pageable);

    

}
