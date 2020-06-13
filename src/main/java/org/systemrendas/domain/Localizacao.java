package org.systemrendas.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Localizacao {

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

    private String endereco;

    @Column(length = 50)
    private String bairro;

    @Column(length = 8)
    private String cep;

    @Column(length = 1000)
    private String complemento;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @ManyToOne
    private Municipio municipio;

    @OneToOne(mappedBy = "localizacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Patrimonio patrimonio;

    public Localizacao() {
    }

    public Localizacao(UUID id, Date createdAt, String endereco, String bairro, String cep, String complemento,
            BigDecimal latitude, BigDecimal longitude, Municipio municipio) {
        this.id = id;
        this.createdAt = createdAt;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.latitude = latitude;
        this.longitude = longitude;
        this.municipio = municipio;
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

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public BigDecimal getLatitude() {
        return this.latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return this.longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Localizacao id(UUID id) {
        this.id = id;
        return this;
    }

    public Localizacao createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Localizacao endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public Localizacao bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public Localizacao cep(String cep) {
        this.cep = cep;
        return this;
    }

    public Localizacao complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public Localizacao latitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public Localizacao longitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public Localizacao municipio(Municipio municipio) {
        this.municipio = municipio;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Localizacao)) {
            return false;
        }
        Localizacao localizacao = (Localizacao) o;
        return Objects.equals(id, localizacao.id) && Objects.equals(createdAt, localizacao.createdAt)
                && Objects.equals(endereco, localizacao.endereco) && Objects.equals(bairro, localizacao.bairro)
                && Objects.equals(cep, localizacao.cep) && Objects.equals(complemento, localizacao.complemento)
                && Objects.equals(latitude, localizacao.latitude) && Objects.equals(longitude, localizacao.longitude)
                && Objects.equals(municipio, localizacao.municipio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, endereco, bairro, cep, complemento, latitude, longitude, municipio);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", endereco='" + getEndereco()
                + "'" + ", bairro='" + getBairro() + "'" + ", cep='" + getCep() + "'" + ", complemento='"
                + getComplemento() + "'" + ", latitude='" + getLatitude() + "'" + ", longitude='" + getLongitude() + "'"
                + ", municipio='" + getMunicipio() + "'" + "}";
    }

}
