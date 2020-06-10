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
public class RecebimentoPatrimonio {

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
    private Recebimento recebimento;

    @ManyToOne
    private Patrimonio patrimonio;

    private BigDecimal valorCalculado;

    public RecebimentoPatrimonio() {
    }

    public RecebimentoPatrimonio(UUID id, Date createdAt, Recebimento recebimento, Patrimonio patrimonio,
            BigDecimal valorCalculado) {
        this.id = id;
        this.createdAt = createdAt;
        this.recebimento = recebimento;
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

    public Recebimento getRecebimento() {
        return this.recebimento;
    }

    public void setRecebimento(Recebimento recebimento) {
        this.recebimento = recebimento;
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

    public RecebimentoPatrimonio id(UUID id) {
        this.id = id;
        return this;
    }

    public RecebimentoPatrimonio createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public RecebimentoPatrimonio recebimento(Recebimento recebimento) {
        this.recebimento = recebimento;
        return this;
    }

    public RecebimentoPatrimonio patrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
        return this;
    }

    public RecebimentoPatrimonio valorCalculado(BigDecimal valorCalculado) {
        this.valorCalculado = valorCalculado;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RecebimentoPatrimonio)) {
            return false;
        }
        RecebimentoPatrimonio recebimentoPatrimonio = (RecebimentoPatrimonio) o;
        return Objects.equals(id, recebimentoPatrimonio.id)
                && Objects.equals(createdAt, recebimentoPatrimonio.createdAt)
                && Objects.equals(recebimento, recebimentoPatrimonio.recebimento)
                && Objects.equals(patrimonio, recebimentoPatrimonio.patrimonio)
                && Objects.equals(valorCalculado, recebimentoPatrimonio.valorCalculado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, recebimento, patrimonio, valorCalculado);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", recebimento='"
                + getRecebimento() + "'" + ", patrimonio='" + getPatrimonio() + "'" + ", valorCalculado='"
                + getValorCalculado() + "'" + "}";
    }

}
