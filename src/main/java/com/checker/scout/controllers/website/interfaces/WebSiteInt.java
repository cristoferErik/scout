package com.checker.scout.controllers.website.interfaces;

import java.time.LocalDate;

public interface WebSiteInt {
    public class WebSiteDao {

        private Long webSiteId;
        private String nome;
        private String url;
        private LocalDate dataAggiornamento;
        private LocalDate dataBackup;
        private String descrizione;

        public WebSiteDao() {
        }


        public LocalDate getDataBackup() {
            return dataBackup;
        }

        public void setDataBackup(LocalDate dataBackup) {
            this.dataBackup = dataBackup;
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
        public LocalDate getDataAggiornamento() {
            return dataAggiornamento;
        }

        public void setDataAggiornamento(LocalDate dataAggiornamento) {
            this.dataAggiornamento = dataAggiornamento;
        }


        public Long getWebSiteId() {
            return webSiteId;
        }


        public void setWebSiteId(Long webSiteId) {
            this.webSiteId = webSiteId;
        }
        
    }
}
