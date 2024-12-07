package com.checker.scout.controllers.website.interfaces;

import java.time.LocalDate;

public interface WebSiteInt {
    public class WebSiteDao {

        private Long id;
        private String nome;
        private String url;
        private String descrizione;
        private Long hostingId;

        public WebSiteDao() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescrizione() {
            return descrizione;
        }

        public void setDescrizione(String descrizzione) {
            this.descrizione = descrizzione;
        }

        public Long getHostingId() {
            return hostingId;
        }

        public void setHostingId(Long hostingId) {
            this.hostingId = hostingId;
        }

        
    }
    public class DetailServizioDTO{
        private Long getId;
        private Long getServizioId;
        private Long getWebSiteId;
        private String getDescrizione;
        private Integer getPeriodo;
        private Boolean getStatus;
        private LocalDate getDateIni;
        private LocalDate getDateFine;
        private String getNomeServizio;
        
        public Long getGetId() {
            return getId;
        }
        public void setGetId(Long getId) {
            this.getId = getId;
        }
        public Long getGetServizioId() {
            return getServizioId;
        }
        public void setGetServizioId(Long getServizioId) {
            this.getServizioId = getServizioId;
        }
        public Long getGetWebSiteId() {
            return getWebSiteId;
        }
        public void setGetWebSiteId(Long getWebSiteId) {
            this.getWebSiteId = getWebSiteId;
        }
        public String getGetDescrizione() {
            return getDescrizione;
        }
        public void setGetDescrizione(String getDescrizione) {
            this.getDescrizione = getDescrizione;
        }
        public Integer getGetPeriodo() {
            return getPeriodo;
        }
        public void setGetPeriodo(Integer getPeriodo) {
            this.getPeriodo = getPeriodo;
        }
        public Boolean getGetStatus() {
            return getStatus;
        }
        public void setGetStatus(Boolean getStatus) {
            this.getStatus = getStatus;
        }
        public LocalDate getGetDateIni() {
            return getDateIni;
        }
        public void setGetDateIni(LocalDate getDateIni) {
            this.getDateIni = getDateIni;
        }
        public LocalDate getGetDateFine() {
            return getDateFine;
        }
        public void setGetDateFine(LocalDate getDateFine) {
            this.getDateFine = getDateFine;
        }
        public String getGetNomeServizio() {
            return getNomeServizio;
        }
        public void setGetNomeServizio(String getNomeServizio) {
            this.getNomeServizio = getNomeServizio;
        }

        
    }
}
