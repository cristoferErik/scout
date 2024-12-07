package com.checker.scout.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.checker.scout.entities.DetailWsSe;
import com.checker.scout.entities.projections.IDetailWsSe;

public interface DetailWsSeRepository extends JpaRepository<DetailWsSe, Long>{
    
    @Query("""
        SELECT COUNT(d)<1 FROM DetailWsSe d WHERE
            d.webSite.id = :#{#superKey.webSiteId} AND
            d.servizio.id  = :#{#superKey.servizioId}
        """)
    public boolean isExist(@Param("superKey") IDetailWsSe.webSiteServiceIdsDto superKey);

    @Query("""
                SELECT d.id as id,
                d.dateIni as dateIni,
                d.dateFine as dateFine,
                d.periodo as periodo,
                d.descrizione as descrizione,
                d.status as status,
                d.servizio.id as servizioId,
                d.servizio.nome as nomeServizio,
                d.webSite.id as webSiteId
                 FROM DetailWsSe d 
                 where d.webSite.id =:id
            """)
    Page<IDetailWsSe.HistorialService> findAllHistorialServicesByWebSiteId(Long id,Pageable pageable);
}
