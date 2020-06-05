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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Recebimento {

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
    private Renda renda;

    private BigDecimal valor;

    private Date dataRecebimento;

    @Column(length = 500)
    private String obs;

    public Recebimento() {
    }

    public Recebimento(UUID id, Date createdAt, Renda renda, BigDecimal valor, Date dataRecebimento, String obs) {
        this.id = id;
        this.createdAt = createdAt;
        this.renda = renda;
        this.valor = valor;
        this.dataRecebimento = dataRecebimento;
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

    public Renda getRenda() {
        return this.renda;
    }

    public void setRenda(Renda renda) {
        this.renda = renda;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataRecebimento() {
        return this.dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Recebimento id(UUID id) {
        this.id = id;
        return this;
    }

    public Recebimento createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Recebimento renda(Renda renda) {
        this.renda = renda;
        return this;
    }

    public Recebimento valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Recebimento dataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
        return this;
    }

    public Recebimento obs(String obs) {
        this.obs = obs;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Recebimento)) {
            return false;
        }
        Recebimento recebimento = (Recebimento) o;
        return Objects.equals(id, recebimento.id) && Objects.equals(createdAt, recebimento.createdAt)
                && Objects.equals(renda, recebimento.renda) && Objects.equals(valor, recebimento.valor)
                && Objects.equals(dataRecebimento, recebimento.dataRecebimento) && Objects.equals(obs, recebimento.obs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, renda, valor, dataRecebimento, obs);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", renda='" + getRenda() + "'"
                + ", valor='" + getValor() + "'" + ", dataRecebimento='" + getDataRecebimento() + "'" + ", obs='"
                + getObs() + "'" + "}";
    }

}
