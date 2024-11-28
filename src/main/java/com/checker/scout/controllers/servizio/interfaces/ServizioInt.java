package com.checker.scout.controllers.servizio.interfaces;

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
            public void setDescrizione(String descrizzione) {
                this.descrizione = descrizzione;
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
}
