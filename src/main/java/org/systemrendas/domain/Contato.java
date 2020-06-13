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
public class Contato {

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

    @Column(length = 11)
    private String fone;

    private Boolean whatsapp;

    @Column(length = 100)
    private String email;

    @ManyToOne
    private Pagador pagador;

    @ManyToOne
    private Socio socio;

    public Contato() {
    }

    public Contato(UUID id, Date createdAt, String fone, Boolean whatsapp, String email, Pagador pagador, Socio socio) {
        this.id = id;
        this.createdAt = createdAt;
        this.fone = fone;
        this.whatsapp = whatsapp;
        this.email = email;
        this.pagador = pagador;
        this.socio = socio;
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

    public String getFone() {
        return this.fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Boolean isWhatsapp() {
        return this.whatsapp;
    }

    public Boolean getWhatsapp() {
        return this.whatsapp;
    }

    public void setWhatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pagador getPagador() {
        return this.pagador;
    }

    public void setPagador(Pagador pagador) {
        this.pagador = pagador;
    }

    public Socio getSocio() {
        return this.socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Contato id(UUID id) {
        this.id = id;
        return this;
    }

    public Contato createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Contato fone(String fone) {
        this.fone = fone;
        return this;
    }

    public Contato whatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
        return this;
    }

    public Contato email(String email) {
        this.email = email;
        return this;
    }

    public Contato pagador(Pagador pagador) {
        this.pagador = pagador;
        return this;
    }

    public Contato socio(Socio socio) {
        this.socio = socio;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Contato)) {
            return false;
        }
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id) && Objects.equals(createdAt, contato.createdAt)
                && Objects.equals(fone, contato.fone) && Objects.equals(whatsapp, contato.whatsapp)
                && Objects.equals(email, contato.email) && Objects.equals(pagador, contato.pagador)
                && Objects.equals(socio, contato.socio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, fone, whatsapp, email, pagador, socio);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", fone='" + getFone() + "'"
                + ", whatsapp='" + isWhatsapp() + "'" + ", email='" + getEmail() + "'" + ", pagador='" + getPagador()
                + "'" + ", socio='" + getSocio() + "'" + "}";
    }

}
