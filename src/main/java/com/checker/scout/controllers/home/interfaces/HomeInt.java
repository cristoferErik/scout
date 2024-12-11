package com.checker.scout.controllers.home.interfaces;


public interface HomeInt {
    public class webSiteMessage{
        private Long webSiteId;
        private String webSiteNome;
        private String subject;
        private String message;
        
        public Long getWebSiteId() {
            return webSiteId;
        }

        public void setWebSiteId(Long webSiteId) {
            this.webSiteId = webSiteId;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getWebSiteNome() {
            return webSiteNome;
        }

        public void setWebSiteNome(String webSiteNome) {
            this.webSiteNome = webSiteNome;
        }
    
    }
}
