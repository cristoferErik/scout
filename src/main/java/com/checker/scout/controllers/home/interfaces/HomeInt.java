package com.checker.scout.controllers.home.interfaces;


public interface HomeInt {
    public class webSiteMessage{
        private Long id;
        private String nameWebSite;
        private String descrizione;
        private String message;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getNameWebSite() {
            return nameWebSite;
        }
        public void setNameWebSite(String nameWebSite) {
            this.nameWebSite = nameWebSite;
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
