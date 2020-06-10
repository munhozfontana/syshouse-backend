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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Boolean getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pagador getPagador() {
        return pagador;
    }

    public void setPagador(Pagador pagador) {
        this.pagador = pagador;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
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

}
