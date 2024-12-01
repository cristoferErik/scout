package com.checker.scout.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="servizio")
public class Servizio {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrizione;
    private float costo;
    private LocalDateTime dataCreazione;
    private LocalDateTime dataAggiornamento;
    
    @JsonIgnore
    @OneToMany(mappedBy="servizio", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<DetailWsSe> listDetailWsSe;




    public void prePersist(){
        if(dataCreazione == null){
            dataCreazione = LocalDateTime.now();
        }
    }

    public Servizio() {
        this.listDetailWsSe=new ArrayList<>();
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

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public List<DetailWsSe> getListDetailWsSe() {
        return listDetailWsSe;
    }

    public void setListDetailWsSe(List<DetailWsSe> listDetailWsSe) {
        this.listDetailWsSe = listDetailWsSe;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizzione) {
        this.descrizione = descrizzione;
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
