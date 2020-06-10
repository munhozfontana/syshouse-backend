package org.systemrendas.dto.despesa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DespesaUpdateDTO {

    @Max(message = "Valor não deve ser acima de 100 caracters", value = 100)
    private String descricao;

    private BigDecimal valor;

    private LocalDate vencimento;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Column(length = 500)
    private String obs;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID tipoDespesaId;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioId;

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

    public UUID getTipoDespesaId() {
        return this.tipoDespesaId;
    }

    public void setTipoDespesaId(UUID tipoDespesaId) {
        this.tipoDespesaId = tipoDespesaId;
    }

    public UUID getPatrimonioId() {
        return this.patrimonioId;
    }

    public void setPatrimonioId(UUID patrimonioId) {
        this.patrimonioId = patrimonioId;
    }

}
