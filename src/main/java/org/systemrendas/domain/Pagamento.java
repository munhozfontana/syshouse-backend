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
public class Pagamento {

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
    private Despesa despesa;

    private BigDecimal valor;

    private Date dataPagamento;

    @Column(length = 500)
    private String obs;

    public Pagamento() {
    }

    public Pagamento(UUID id, Date createdAt, Despesa despesa, BigDecimal valor, Date dataPagamento, String obs) {
        this.id = id;
        this.createdAt = createdAt;
        this.despesa = despesa;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
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

    public Despesa getDespesa() {
        return this.despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return this.dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Pagamento id(UUID id) {
        this.id = id;
        return this;
    }

    public Pagamento createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Pagamento despesa(Despesa despesa) {
        this.despesa = despesa;
        return this;
    }

    public Pagamento valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Pagamento dataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
        return this;
    }

    public Pagamento obs(String obs) {
        this.obs = obs;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pagamento)) {
            return false;
        }
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) && Objects.equals(createdAt, pagamento.createdAt)
                && Objects.equals(despesa, pagamento.despesa) && Objects.equals(valor, pagamento.valor)
                && Objects.equals(dataPagamento, pagamento.dataPagamento) && Objects.equals(obs, pagamento.obs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, despesa, valor, dataPagamento, obs);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", despesa='" + getDespesa()
                + "'" + ", valor='" + getValor() + "'" + ", dataPagamento='" + getDataPagamento() + "'" + ", obs='"
                + getObs() + "'" + "}";
    }

}
