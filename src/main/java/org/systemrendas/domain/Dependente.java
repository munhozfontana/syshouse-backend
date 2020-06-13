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
public class Dependente {

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
    private Pagador pagador;

    @Column(length = 100)
    private String nome;

    public Dependente() {
    }

    public Dependente(UUID id, Date createdAt, Pagador pagador, String nome) {
        this.id = id;
        this.createdAt = createdAt;
        this.pagador = pagador;
        this.nome = nome;
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

    public Pagador getPagador() {
        return this.pagador;
    }

    public void setPagador(Pagador pagador) {
        this.pagador = pagador;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Dependente id(UUID id) {
        this.id = id;
        return this;
    }

    public Dependente createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Dependente pagador(Pagador pagador) {
        this.pagador = pagador;
        return this;
    }

    public Dependente nome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Dependente)) {
            return false;
        }
        Dependente dependente = (Dependente) o;
        return Objects.equals(id, dependente.id) && Objects.equals(createdAt, dependente.createdAt)
                && Objects.equals(pagador, dependente.pagador) && Objects.equals(nome, dependente.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, pagador, nome);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", pagador='" + getPagador()
                + "'" + ", nome='" + getNome() + "'" + "}";
    }

}
