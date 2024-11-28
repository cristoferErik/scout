package com.checker.scout.entities.dto;

public class WebSiteDTO {

    private Long id;
    private String nome;
    private String url;
    private String descrizione;
    private Long idHosting;

    public WebSiteDTO() {
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getIdHosting() {
        return idHosting;
    }

    public void setIdHosting(Long idHosting) {
        this.idHosting = idHosting;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WebSiteDTO{");
        sb.append("id=").append(id);
        sb.append(", nome=").append(nome);
        sb.append(", url=").append(url);
        sb.append(", descrizione=").append(descrizione);
        sb.append(", idHosting=").append(idHosting);
        sb.append('}');
        return sb.toString();
    }

}
