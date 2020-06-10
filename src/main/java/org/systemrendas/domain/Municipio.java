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

    public Municipio() {
    }

    public Municipio(UUID id, Date createdAt, Integer ibge, String nome, String uf, String pais, Integer populacao) {
        this.id = id;
        this.createdAt = createdAt;
        this.ibge = ibge;
        this.nome = nome;
        this.uf = uf;
        this.pais = pais;
        this.populacao = populacao;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public Municipio id(UUID id) {
        this.id = id;
        return this;
    }

    public Municipio createdAt(Date createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Municipio)) {
            return false;
        }
        Municipio municipio = (Municipio) o;
        return Objects.equals(id, municipio.id) && Objects.equals(createdAt, municipio.createdAt)
                && Objects.equals(ibge, municipio.ibge) && Objects.equals(nome, municipio.nome)
                && Objects.equals(uf, municipio.uf) && Objects.equals(pais, municipio.pais)
                && Objects.equals(populacao, municipio.populacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, ibge, nome, uf, pais, populacao);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", ibge='" + getIbge() + "'"
                + ", nome='" + getNome() + "'" + ", uf='" + getUf() + "'" + ", pais='" + getPais() + "'"
                + ", populacao='" + getPopulacao() + "'" + "}";
    }

}
