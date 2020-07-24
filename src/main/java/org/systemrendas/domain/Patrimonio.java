package org.systemrendas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Patrimonio {

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

    @Column(length = 100)
    private String nome;

    private BigDecimal valor;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate dataFim;

    @ManyToOne
    private TipoPatrimonio tipoPatrimonio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Localizacao localizacao;

    public Patrimonio() {
    }

    public Patrimonio(UUID id, Date createdAt, String nome, BigDecimal valor, LocalDate dataInicio, LocalDate dataFim,
            TipoPatrimonio tipoPatrimonio, Localizacao localizacao) {
        this.id = id;
        this.createdAt = createdAt;
        this.nome = nome;
        this.valor = valor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tipoPatrimonio = tipoPatrimonio;
        this.localizacao = localizacao;
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

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    public TipoPatrimonio getTipoPatrimonio() {
        return this.tipoPatrimonio;
    }

    public void setTipoPatrimonio(TipoPatrimonio tipoPatrimonio) {
        this.tipoPatrimonio = tipoPatrimonio;
    }

    public Localizacao getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Patrimonio id(UUID id) {
        this.id = id;
        return this;
    }

    public Patrimonio createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Patrimonio nome(String nome) {
        this.nome = nome;
        return this;
    }

    public Patrimonio valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Patrimonio dataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public Patrimonio dataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public Patrimonio tipoPatrimonio(TipoPatrimonio tipoPatrimonio) {
        this.tipoPatrimonio = tipoPatrimonio;
        return this;
    }

    public Patrimonio localizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Patrimonio)) {
            return false;
        }
        Patrimonio patrimonio = (Patrimonio) o;
        return Objects.equals(id, patrimonio.id) && Objects.equals(createdAt, patrimonio.createdAt)
                && Objects.equals(nome, patrimonio.nome) && Objects.equals(valor, patrimonio.valor)
                && Objects.equals(dataInicio, patrimonio.dataInicio) && Objects.equals(dataFim, patrimonio.dataFim)
                && Objects.equals(tipoPatrimonio, patrimonio.tipoPatrimonio)
                && Objects.equals(localizacao, patrimonio.localizacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, nome, valor, dataInicio, dataFim, tipoPatrimonio, localizacao);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", nome='" + getNome() + "'"
                + ", valor='" + getValor() + "'" + ", dataInicio='" + getDataInicio() + "'" + ", dataFim='"
                + getDataFim() + "'" + ", tipoPatrimonio='" + getTipoPatrimonio() + "'" + ", localizacao='"
                + getLocalizacao() + "'" + "}";
    }

}
