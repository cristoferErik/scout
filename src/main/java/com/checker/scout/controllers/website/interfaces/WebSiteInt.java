package com.checker.scout.controllers.website.interfaces;

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
}
