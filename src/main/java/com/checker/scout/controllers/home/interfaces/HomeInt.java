package com.checker.scout.controllers.home.interfaces;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

public interface HomeInt {
    public class webSiteToUpdateDTO extends  RepresentationModel<webSiteToUpdateDTO>{
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
}
