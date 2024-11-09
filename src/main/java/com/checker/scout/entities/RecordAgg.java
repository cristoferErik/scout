package com.checker.scout.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

//Questa classe ci server come historiale del aggiornamento
//Record dei aggiornamenti
@Entity
@Table(name="record_agg")
public class RecordAgg {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String descrizzione;
    @Transient
    private String message;

    //Detail_webSite_ServizioAggiornamento_id
    @ManyToOne
    @JoinColumn(name="detail_ws_sa_id")
    private DetailWsSe detailWsSe;

    public RecordAgg() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescrizzione() {
        return descrizzione;
    }

    public void setDescrizzione(String descrizzione) {
        this.descrizzione = descrizzione;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DetailWsSe getDetailWsSe() {
        return detailWsSe;
    }

    public void setDetailWsSe(DetailWsSe detailWsSe) {
        this.detailWsSe = detailWsSe;
    }


}
