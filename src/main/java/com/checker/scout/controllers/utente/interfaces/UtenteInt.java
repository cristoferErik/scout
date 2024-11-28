package com.checker.scout.controllers.utente.interfaces;

public interface UtenteInt {
    
    public class utenteDao{
        private Long id;
        private String nome;
        private String cognome;
        private String indirizzo;
        private String telefono;
        private String email;

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

        public String getCognome() {
            return cognome;
        }

        public void setCognome(String cognome) {
            this.cognome = cognome;
        }

        public String getIndirizzo() {
            return indirizzo;
        }

        public void setIndirizzo(String indirizzo) {
            this.indirizzo = indirizzo;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("utenteDao{");
            sb.append("id=").append(id);
            sb.append(", nome=").append(nome);
            sb.append(", cognome=").append(cognome);
            sb.append(", indirizzo=").append(indirizzo);
            sb.append(", telefono=").append(telefono);
            sb.append(", email=").append(email);
            sb.append('}');
            return sb.toString();
        }


    }

}
