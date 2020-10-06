package org.systemrendas.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sociopatrimonio")
public class SocioPatrimonio {

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
    private Socio socio;

    @ManyToOne
    private Patrimonio patrimonio;

    private BigDecimal porcentagem;

    private Boolean proprietario;

    public SocioPatrimonio() {
    }

    public SocioPatrimonio(UUID id, Date createdAt, Socio socio, Patrimonio patrimonio, BigDecimal porcentagem,
            Boolean proprietario) {
        this.id = id;
        this.createdAt = createdAt;
        this.socio = socio;
        this.patrimonio = patrimonio;
        this.porcentagem = porcentagem;
        this.proprietario = proprietario;
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

    public Socio getSocio() {
        return this.socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Patrimonio getPatrimonio() {
        return this.patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public BigDecimal getPorcentagem() {
        return this.porcentagem;
    }

    public void setPorcentagem(BigDecimal porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Boolean isProprietario() {
        return this.proprietario;
    }

    public Boolean getProprietario() {
        return this.proprietario;
    }

    public void setProprietario(Boolean proprietario) {
        this.proprietario = proprietario;
    }

    public SocioPatrimonio id(UUID id) {
        this.id = id;
        return this;
    }

    public SocioPatrimonio createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SocioPatrimonio socio(Socio socio) {
        this.socio = socio;
        return this;
    }

    public SocioPatrimonio patrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
        return this;
    }

    public SocioPatrimonio porcentagem(BigDecimal porcentagem) {
        this.porcentagem = porcentagem;
        return this;
    }

    public SocioPatrimonio proprietario(Boolean proprietario) {
        this.proprietario = proprietario;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SocioPatrimonio)) {
            return false;
        }
        SocioPatrimonio socioPatrimonio = (SocioPatrimonio) o;
        return Objects.equals(id, socioPatrimonio.id) && Objects.equals(createdAt, socioPatrimonio.createdAt)
                && Objects.equals(socio, socioPatrimonio.socio)
                && Objects.equals(patrimonio, socioPatrimonio.patrimonio)
                && Objects.equals(porcentagem, socioPatrimonio.porcentagem)
                && Objects.equals(proprietario, socioPatrimonio.proprietario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, socio, patrimonio, porcentagem, proprietario);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", socio='" + getSocio() + "'"
                + ", patrimonio='" + getPatrimonio() + "'" + ", porcentagem='" + getPorcentagem() + "'"
                + ", proprietario='" + isProprietario() + "'" + "}";
    }

}
