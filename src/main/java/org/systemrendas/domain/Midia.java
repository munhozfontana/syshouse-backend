package org.systemrendas.domain;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private UUID id;

    @Schema(readOnly = true)
    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

    @ManyToOne
    private Patrimonio patrimonio;

    @Column(length = 255)
    private String nome;

    @Column(length = 255)
    private String caminho;

    @Column(length = 500)
    private String obs;

    @Column(length = 45)
    private String tipo;

    public Midia() {
    }

    public Midia(UUID id, Date createdAt, Patrimonio patrimonio, String nome, String caminho, String obs, String tipo) {
        this.id = id;
        this.createdAt = createdAt;
        this.patrimonio = patrimonio;
        this.nome = nome;
        this.caminho = caminho;
        this.obs = obs;
        this.tipo = tipo;
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

    public Patrimonio getPatrimonio() {
        return this.patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminho() {
        return this.caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Midia id(UUID id) {
        this.id = id;
        return this;
    }

    public Midia createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Midia patrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
        return this;
    }

    public Midia nome(String nome) {
        this.nome = nome;
        return this;
    }

    public Midia caminho(String caminho) {
        this.caminho = caminho;
        return this;
    }

    public Midia obs(String obs) {
        this.obs = obs;
        return this;
    }

    public Midia tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Midia)) {
            return false;
        }
        Midia midia = (Midia) o;
        return Objects.equals(id, midia.id) && Objects.equals(createdAt, midia.createdAt)
                && Objects.equals(patrimonio, midia.patrimonio) && Objects.equals(nome, midia.nome)
                && Objects.equals(caminho, midia.caminho) && Objects.equals(obs, midia.obs)
                && Objects.equals(tipo, midia.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, patrimonio, nome, caminho, obs, tipo);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", patrimonio='"
                + getPatrimonio() + "'" + ", nome='" + getNome() + "'" + ", caminho='" + getCaminho() + "'" + ", obs='"
                + getObs() + "'" + ", tipo='" + getTipo() + "'" + "}";
    }

}
