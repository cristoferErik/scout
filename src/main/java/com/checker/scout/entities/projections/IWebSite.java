package com.checker.scout.entities.projections;

import java.time.LocalDate;

public interface IWebSite {
    public interface WebSiteP{
        Long getId();
        String getNomeWebSite();
        LocalDate getProssimoAgg();
        String getNomeServizio();
    }
    
    
}
