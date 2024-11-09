package com.checker.scout.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="hosting")
public class Hosting {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String url;
    @Column(name="h_username")
    private String hUsername;
    @Column(name="h_password")
    private String hPassword; 
    
    @ManyToOne
    @JoinColumn(name="utente_id",nullable=false)
    private Utente utente;

    @OneToMany(mappedBy="hosting")
    private List<WebSite> listWebSite;

    public Hosting() {
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

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<WebSite> getListWebSite() {
        return listWebSite;
    }

    public void setListWebSite(List<WebSite> listWebSite) {
        this.listWebSite = listWebSite;
    }


}
