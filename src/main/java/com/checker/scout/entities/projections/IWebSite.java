package com.checker.scout.entities.projections;

import java.time.LocalDate;

public interface IWebSite {
    public Long getId();
    public String getNomeWebSite();
    public LocalDate getProssimoAgg();
    public String getNomeServizio();
}
