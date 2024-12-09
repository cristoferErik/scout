package com.checker.scout.controllers.hosting.interfaces;

public interface HostingInt {
    public class HostingDao{
        private Long id;
        private String nome;
        private String netsonUrl;
        private String url;
        private String hUsername;
        private String hPassword; 
        private Long utenteId;

        public HostingDao() {
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
        public String gethUsername() {
            return hUsername;
        }
        public void sethUsername(String hUsername) {
            this.hUsername = hUsername;
        }
        public String gethPassword() {
            return hPassword;
        }
        public void sethPassword(String hPassword) {
            this.hPassword = hPassword;
        }
        public Long getUtenteId() {
            return utenteId;
        }
        public void setUtenteId(Long utenteId) {
            this.utenteId = utenteId;
        }

        public String getNetsonUrl() {
            return netsonUrl;
        }

        public void setNetsonUrl(String netsonUrl) {
            this.netsonUrl = netsonUrl;
        }
        
        

    }

}
