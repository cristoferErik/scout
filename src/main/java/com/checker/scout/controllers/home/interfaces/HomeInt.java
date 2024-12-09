package com.checker.scout.controllers.home.interfaces;

import java.time.LocalDate;

public interface HomeInt {
    public class webSiteToUpdateDTO{
        private Long id;
        private String nomeWebSite;
        private LocalDate prossimoAgg;
        private String nomeServizio;

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getNomeWebSite() {
            return nomeWebSite;
        }
        public void setNomeWebSite(String nomeWebSite) {
            this.nomeWebSite = nomeWebSite;
        }
        public LocalDate getProssimoAgg() {
            return prossimoAgg;
        }
        public void setProssimoAgg(LocalDate prossimoAgg) {
            this.prossimoAgg = prossimoAgg;
        }
        public String getNomeServizio() {
            return nomeServizio;
        }
        public void setNomeServizio(String nomeServizio) {
            this.nomeServizio = nomeServizio;
        }

        
    }
    public class webSiteUpdatedDTO{
        private Long WebSiteId;
        private String descrizione;
        private String message;

        public Long getWebSiteId() {
            return WebSiteId;
        }

        public void setWebSiteId(Long WebSiteId) {
            this.WebSiteId = WebSiteId;
        }

        public String getDescrizione() {
            return descrizione;
        }

        public void setDescrizione(String descrizione) {
            this.descrizione = descrizione;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


    }
}
