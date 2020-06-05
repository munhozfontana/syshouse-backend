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
public class PagamentoPatrimonio {

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
    private Pagamento pagamento;

    @ManyToOne
    private Patrimonio patrimonio;

    private BigDecimal valorCalculado;

    public PagamentoPatrimonio() {
    }

    public PagamentoPatrimonio(UUID id, Date createdAt, Pagamento pagamento, Patrimonio patrimonio,
            BigDecimal valorCalculado) {
        this.id = id;
        this.createdAt = createdAt;
        this.pagamento = pagamento;
        this.patrimonio = patrimonio;
        this.valorCalculado = valorCalculado;
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

    public Pagamento getPagamento() {
        return this.pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Patrimonio getPatrimonio() {
        return this.patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public BigDecimal getValorCalculado() {
        return this.valorCalculado;
    }

    public void setValorCalculado(BigDecimal valorCalculado) {
        this.valorCalculado = valorCalculado;
    }

    public PagamentoPatrimonio id(UUID id) {
        this.id = id;
        return this;
    }

    public PagamentoPatrimonio createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PagamentoPatrimonio pagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
        return this;
    }

    public PagamentoPatrimonio patrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
        return this;
    }

    public PagamentoPatrimonio valorCalculado(BigDecimal valorCalculado) {
        this.valorCalculado = valorCalculado;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PagamentoPatrimonio)) {
            return false;
        }
        PagamentoPatrimonio pagamentoPatrimonio = (PagamentoPatrimonio) o;
        return Objects.equals(id, pagamentoPatrimonio.id) && Objects.equals(createdAt, pagamentoPatrimonio.createdAt)
                && Objects.equals(pagamento, pagamentoPatrimonio.pagamento)
                && Objects.equals(patrimonio, pagamentoPatrimonio.patrimonio)
                && Objects.equals(valorCalculado, pagamentoPatrimonio.valorCalculado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, pagamento, patrimonio, valorCalculado);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", pagamento='" + getPagamento()
                + "'" + ", patrimonio='" + getPatrimonio() + "'" + ", valorCalculado='" + getValorCalculado() + "'"
                + "}";
    }

}
