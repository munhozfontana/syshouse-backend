package org.systemrendas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class Despesa {

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
    private TipoDespesa tipoDespesa;

    @ManyToOne
    private Patrimonio patrimonio;

    @Column(length = 100)
    private String descricao;

    private BigDecimal valor;

    private LocalDate vencimento;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Column(length = 500)
    private String obs;

    public Despesa() {
    }

    public Despesa(UUID id, Date createdAt, TipoDespesa tipoDespesa, Patrimonio patrimonio, String descricao,
            BigDecimal valor, LocalDate vencimento, LocalDate dataInicio, LocalDate dataFim, String obs) {
        this.id = id;
        this.createdAt = createdAt;
        this.tipoDespesa = tipoDespesa;
        this.patrimonio = patrimonio;
        this.descricao = descricao;
        this.valor = valor;
        this.vencimento = vencimento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
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

    public TipoDespesa getTipoDespesa() {
        return this.tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public Patrimonio getPatrimonio() {
        return this.patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getVencimento() {
        return this.vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Despesa id(UUID id) {
        this.id = id;
        return this;
    }

    public Despesa createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Despesa tipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
        return this;
    }

    public Despesa patrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
        return this;
    }

    public Despesa descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Despesa valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Despesa vencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
        return this;
    }

    public Despesa dataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public Despesa dataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public Despesa obs(String obs) {
        this.obs = obs;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Despesa)) {
            return false;
        }
        Despesa despesa = (Despesa) o;
        return Objects.equals(id, despesa.id) && Objects.equals(createdAt, despesa.createdAt)
                && Objects.equals(tipoDespesa, despesa.tipoDespesa) && Objects.equals(patrimonio, despesa.patrimonio)
                && Objects.equals(descricao, despesa.descricao) && Objects.equals(valor, despesa.valor)
                && Objects.equals(vencimento, despesa.vencimento) && Objects.equals(dataInicio, despesa.dataInicio)
                && Objects.equals(dataFim, despesa.dataFim) && Objects.equals(obs, despesa.obs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, tipoDespesa, patrimonio, descricao, valor, vencimento, dataInicio, dataFim,
                obs);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", tipoDespesa='"
                + getTipoDespesa() + "'" + ", patrimonio='" + getPatrimonio() + "'" + ", descricao='" + getDescricao()
                + "'" + ", valor='" + getValor() + "'" + ", vencimento='" + getVencimento() + "'" + ", dataInicio='"
                + getDataInicio() + "'" + ", dataFim='" + getDataFim() + "'" + ", obs='" + getObs() + "'" + "}";
    }

}
