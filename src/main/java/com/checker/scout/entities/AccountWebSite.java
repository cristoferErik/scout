package com.checker.scout.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="account_website")
public class AccountWebSite {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="ac_username")
    private String acUsername;
    @Column(name="ac_password")
    private String acPassword;

    @ManyToOne
    @JoinColumn(name="website_id",nullable=true)
    private WebSite webSite;

    public AccountWebSite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcUsername() {
        return acUsername;
    }

    public void setAcUsername(String acUsername) {
        this.acUsername = acUsername;
    }

    public String getAcPassword() {
        return acPassword;
    }

    public void setAcPassword(String acPassword) {
        this.acPassword = acPassword;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }

  


    
}
