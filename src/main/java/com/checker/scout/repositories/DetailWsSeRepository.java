package com.checker.scout.repositories;

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
}
