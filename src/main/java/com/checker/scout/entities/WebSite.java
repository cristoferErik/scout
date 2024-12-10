package com.checker.scout.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="website")
public class WebSite {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String url;
    @Column(name="data_aggiornamento")
    private LocalDate dataAggiornamento;
    @Column(name="data_backup")
    private LocalDate dataBackup;
  
    @Column(columnDefinition="TEXT")
    private String descrizione;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hosting_id")
    private Hosting hosting;

    @JsonIgnore
    @OneToMany(mappedBy="webSite", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<AccountWebSite> listAccountWebSites;

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

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public List<AccountWebSite> getListAccountWebSites() {
        return listAccountWebSites;
    }

    public void setListAccountWebSites(List<AccountWebSite> listAccountWebSites) {
        this.listAccountWebSites = listAccountWebSites;
    }

    public Hosting getHosting() {
        return hosting;
    }

    public void setHosting(Hosting hosting) {
        this.hosting = hosting;
    }

    public LocalDate getDataAggiornamento() {
        return dataAggiornamento;
    }

    public void setDataAggiornamento(LocalDate dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    public LocalDate getDataBackup() {
        return dataBackup;
    }

    public void setDataBackup(LocalDate dataBackup) {
        this.dataBackup = dataBackup;
    }


}
