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

import com.fasterxml.jackson.annotation.JsonFormat;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Renda {

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
    private Pagador pagador;

    @ManyToOne
    private TipoRenda tipoRenda;

    @ManyToOne
    private Patrimonio patrimonio;

    @Column(length = 100)
    private String descricao;

    private BigDecimal valor;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate vencimento;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate dataFim;

    @Column(length = 500)
    private String obs;

    public Renda() {
    }

    public Renda(UUID id, Date createdAt, Pagador pagador, TipoRenda tipoRenda, Patrimonio patrimonio, String descricao,
            BigDecimal valor, LocalDate vencimento, LocalDate dataInicio, LocalDate dataFim, String obs) {
        this.id = id;
        this.createdAt = createdAt;
        this.pagador = pagador;
        this.tipoRenda = tipoRenda;
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

    public Pagador getPagador() {
        return this.pagador;
    }

    public void setPagador(Pagador pagador) {
        this.pagador = pagador;
    }

    public TipoRenda getTipoRenda() {
        return this.tipoRenda;
    }

    public void setTipoRenda(TipoRenda tipoRenda) {
        this.tipoRenda = tipoRenda;
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

    public Renda id(UUID id) {
        this.id = id;
        return this;
    }

    public Renda createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Renda pagador(Pagador pagador) {
        this.pagador = pagador;
        return this;
    }

    public Renda tipoRenda(TipoRenda tipoRenda) {
        this.tipoRenda = tipoRenda;
        return this;
    }

    public Renda patrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
        return this;
    }

    public Renda descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Renda valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Renda vencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
        return this;
    }

    public Renda dataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public Renda dataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public Renda obs(String obs) {
        this.obs = obs;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Renda)) {
            return false;
        }
        Renda renda = (Renda) o;
        return Objects.equals(id, renda.id) && Objects.equals(createdAt, renda.createdAt)
                && Objects.equals(pagador, renda.pagador) && Objects.equals(tipoRenda, renda.tipoRenda)
                && Objects.equals(patrimonio, renda.patrimonio) && Objects.equals(descricao, renda.descricao)
                && Objects.equals(valor, renda.valor) && Objects.equals(vencimento, renda.vencimento)
                && Objects.equals(dataInicio, renda.dataInicio) && Objects.equals(dataFim, renda.dataFim)
                && Objects.equals(obs, renda.obs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, pagador, tipoRenda, patrimonio, descricao, valor, vencimento, dataInicio,
                dataFim, obs);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", pagador='" + getPagador()
                + "'" + ", tipoRenda='" + getTipoRenda() + "'" + ", patrimonio='" + getPatrimonio() + "'"
                + ", descricao='" + getDescricao() + "'" + ", valor='" + getValor() + "'" + ", vencimento='"
                + getVencimento() + "'" + ", dataInicio='" + getDataInicio() + "'" + ", dataFim='" + getDataFim() + "'"
                + ", obs='" + getObs() + "'" + "}";
    }

}
