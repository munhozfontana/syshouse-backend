package org.systemrendas.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private UUID id;

    @Schema(readOnly = true)
    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private Integer ibge;

    @Column(length = 50)
    private String nome;

    @Column(length = 2)
    private String uf;

    @Column(length = 50)
    private String pais;

    private Integer populacao;

    @OneToMany(mappedBy = "municipio")
    private List<Localizacao> localizacao = new ArrayList<>();

    public Municipio() {
    }

    public Municipio(UUID id, Integer ibge, String nome, String uf, String pais, Integer populacao,
            List<Localizacao> localizacao) {
        this.id = id;
        this.ibge = ibge;
        this.nome = nome;
        this.uf = uf;
        this.pais = pais;
        this.populacao = populacao;
        this.localizacao = localizacao;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getIbge() {
        return this.ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getPopulacao() {
        return this.populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

    public List<Localizacao> getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(List<Localizacao> localizacao) {
        this.localizacao = localizacao;
    }

    public Municipio id(UUID id) {
        this.id = id;
        return this;
    }

    public Municipio ibge(Integer ibge) {
        this.ibge = ibge;
        return this;
    }

    public Municipio nome(String nome) {
        this.nome = nome;
        return this;
    }

    public Municipio uf(String uf) {
        this.uf = uf;
        return this;
    }

    public Municipio pais(String pais) {
        this.pais = pais;
        return this;
    }

    public Municipio populacao(Integer populacao) {
        this.populacao = populacao;
        return this;
    }

    public Municipio localizacao(List<Localizacao> localizacao) {
        this.localizacao = localizacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Municipio)) {
            return false;
        }
        Municipio municipio = (Municipio) o;
        return Objects.equals(id, municipio.id) && Objects.equals(ibge, municipio.ibge)
                && Objects.equals(nome, municipio.nome) && Objects.equals(uf, municipio.uf)
                && Objects.equals(pais, municipio.pais) && Objects.equals(populacao, municipio.populacao)
                && Objects.equals(localizacao, municipio.localizacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ibge, nome, uf, pais, populacao, localizacao);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", ibge='" + getIbge() + "'" + ", nome='" + getNome() + "'" + ", uf='"
                + getUf() + "'" + ", pais='" + getPais() + "'" + ", populacao='" + getPopulacao() + "'"
                + ", localizacao='" + getLocalizacao() + "'" + "}";
    }

}
