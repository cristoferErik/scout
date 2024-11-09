package com.checker.scout.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.checker.scout.entities.WebSite;
import com.checker.scout.entities.projections.IWebSite;


public interface ServizioWebSiteRepository extends JpaRepository<WebSite,Long>{
    @Query("""
        select w.id as id ,w.nome as nomeWebSite,
        d.prossimoAgg as  prossimoAgg ,s.nome as nomeServizio
            from WebSite w inner join DetailWsSe d on w.id=d.webSite.id
            inner join servizio s on s.id=d.servizio.id 
            where  (:nome IS NULL or w.nome like %:nome%)
            and datediff(d.prossimoAgg,current_date) <=:days
    """)
    Page<IWebSite> findAllWebSiteToUpdate(String nome,int days,Pageable pageable);
}
