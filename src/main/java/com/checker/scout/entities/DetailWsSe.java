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
import jakarta.persistence.Transient;

//Tabella aggiuntiva website e servizio
@Entity
@Table(name="detail_ws_sa")
public class DetailWsSe {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="date_ini")
    private LocalDate dateIni;
    @Column(name="date_fine")
    private LocalDate dateFine;
    private int periodo;

    @Column(columnDefinition="TEXT")
    private String descrizione;
    
    @Column(name="prossimo_agg")
    private LocalDate prossimoAgg;
    
    private boolean status;
    
    @Transient
    private String message;

    @ManyToOne
    @JoinColumn(name="website_id",nullable=false)
    private WebSite webSite;

    @ManyToOne
    @JoinColumn(name="servizio_id",nullable=false)
    private Servizio servizio;

    @JsonIgnore
    @OneToMany(mappedBy="detailWsSe",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<RecordAgg> listRecordAgg;

    public DetailWsSe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateIni() {
        return dateIni;
    }

    public void setDateIni(LocalDate dateIni) {
        this.dateIni = dateIni;
    }

    public LocalDate getDateFine() {
        return dateFine;
    }

    public void setDateFine(LocalDate dateFine) {
        this.dateFine = dateFine;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizzione) {
        this.descrizione = descrizzione;
    }

    public LocalDate getProssimoAgg() {
        return prossimoAgg;
    }

    public void setProssimoAgg(LocalDate prossimoAgg) {
        this.prossimoAgg = prossimoAgg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }

    public Servizio getServizio() {
        return servizio;
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }

    public List<RecordAgg> getListRecordAgg() {
        return listRecordAgg;
    }

    public void setListRecordAgg(List<RecordAgg> listRecordAgg) {
        this.listRecordAgg = listRecordAgg;
    }

    


}
