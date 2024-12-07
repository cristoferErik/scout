package com.checker.scout.entities.projections;

import java.time.LocalDate;


public interface IDetailWsSe {
    public class webSiteServiceIdsDto{
        private Long webSiteId;
        private Long servizioId;

        public webSiteServiceIdsDto(Long servizioId, Long webSiteId) {
            this.servizioId = servizioId;
            this.webSiteId = webSiteId;
        }

        public Long getWebSiteId() {
            return webSiteId;
        }
        public void setWebSiteId(Long webSiteId) {
            this.webSiteId = webSiteId;
        }
        public Long getServizioId() {
            return servizioId;
        }
        public void setServizioId(Long servizioId) {
            this.servizioId = servizioId;
        }
    }
    
    public interface webSiteServizeIds{
        Long webSiteId();
        Long servizioId();
    }

    public interface HistorialService{
        public Long getId();
        public Long getServizioId();
        public Long getWebSiteId();
        public String getDescrizione();
        public Integer getPeriodo();
        public Boolean getStatus();
        public LocalDate getDateIni();
        public LocalDate getDateFine();
        public String getNomeServizio();
    }
}
