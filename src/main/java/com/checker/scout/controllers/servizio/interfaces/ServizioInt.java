package com.checker.scout.controllers.servizio.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ServizioInt {
    public class ServizioDao{
            private Long id;
            private String nome;
            private String descrizione;
            private float costo;
            private LocalDateTime dataCreazione;
            private LocalDateTime dataAggiornamento;

            public ServizioDao() {
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
            public String getDescrizione() {
                return descrizione;
            }
            public void setDescrizione(String descrizione) {
                this.descrizione = descrizione;
            }
            public float getCosto() {
                return costo;
            }
            public void setCosto(float costo) {
                this.costo = costo;
            }
            public LocalDateTime getDataCreazione() {
                return dataCreazione;
            }
            public void setDataCreazione(LocalDateTime dataCreazione) {
                this.dataCreazione = dataCreazione;
            }
            public LocalDateTime getDataAggiornamento() {
                return dataAggiornamento;
            }
            public void setDataAggiornamento(LocalDateTime dataAggiornamento) {
                this.dataAggiornamento = dataAggiornamento;
            }

            
    }

    public class ServizioForWebSite{
        private Long webSiteId;
        private Long servizioId;
        private LocalDate dateIni;
        private LocalDate dateFine;
        private Integer periodo;
        private String descrizione;
        
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
        public LocalDate getDateIni() {
            return dateIni;
        }
        public void setDateIni(LocalDate dateIni) {
            this.dateIni = dateIni;
        }
        public LocalDate getDateFine() {
            return dateFine;
        }
        public void setDateFine(LocalDate dateFine) {
            this.dateFine = dateFine;
        }
        public Integer getPeriodo() {
            return periodo;
        }
        public void setPeriodo(Integer periodo) {
            this.periodo = periodo;
        }
        public String getDescrizione() {
            return descrizione;
        }
        public void setDescrizione(String descrizione) {
            this.descrizione = descrizione;
        }
       
        
        
    }
}
