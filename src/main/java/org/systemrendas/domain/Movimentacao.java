package org.systemrendas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.json.bind.annotation.JsonbDateFormat;
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
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private UUID id;

    @Schema(readOnly = true)
    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    private Patrimonio patrimonioOut;

    @ManyToOne
    private Patrimonio patrimonioIn;

    private BigDecimal valor;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate data;

    @Column(length = 500)
    private String obs;

    public Movimentacao() {
    }

    public Movimentacao(UUID id, Date createdAt, Patrimonio patrimonioOut, Patrimonio patrimonioIn, BigDecimal valor,
            LocalDate data, String obs) {
        this.id = id;
        this.createdAt = createdAt;
        this.patrimonioOut = patrimonioOut;
        this.patrimonioIn = patrimonioIn;
        this.valor = valor;
        this.data = data;
        this.obs = obs;
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

    public Patrimonio getPatrimonioOut() {
        return this.patrimonioOut;
    }

    public void setPatrimonioOut(Patrimonio patrimonioOut) {
        this.patrimonioOut = patrimonioOut;
    }

    public Patrimonio getPatrimonioIn() {
        return this.patrimonioIn;
    }

    public void setPatrimonioIn(Patrimonio patrimonioIn) {
        this.patrimonioIn = patrimonioIn;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Movimentacao id(UUID id) {
        this.id = id;
        return this;
    }

    public Movimentacao createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Movimentacao patrimonioOut(Patrimonio patrimonioOut) {
        this.patrimonioOut = patrimonioOut;
        return this;
    }

    public Movimentacao patrimonioIn(Patrimonio patrimonioIn) {
        this.patrimonioIn = patrimonioIn;
        return this;
    }

    public Movimentacao valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Movimentacao data(LocalDate data) {
        this.data = data;
        return this;
    }

    public Movimentacao obs(String obs) {
        this.obs = obs;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Movimentacao)) {
            return false;
        }
        Movimentacao movimentacao = (Movimentacao) o;
        return Objects.equals(id, movimentacao.id) && Objects.equals(createdAt, movimentacao.createdAt)
                && Objects.equals(patrimonioOut, movimentacao.patrimonioOut)
                && Objects.equals(patrimonioIn, movimentacao.patrimonioIn) && Objects.equals(valor, movimentacao.valor)
                && Objects.equals(data, movimentacao.data) && Objects.equals(obs, movimentacao.obs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, patrimonioOut, patrimonioIn, valor, data, obs);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", patrimonioOut='"
                + getPatrimonioOut() + "'" + ", patrimonioIn='" + getPatrimonioIn() + "'" + ", valor='" + getValor()
                + "'" + ", data='" + getData() + "'" + ", obs='" + getObs() + "'" + "}";
    }

}
