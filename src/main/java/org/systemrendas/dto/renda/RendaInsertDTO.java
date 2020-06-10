package org.systemrendas.dto.renda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RendaInsertDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID pagador;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID renda;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonio;

    @Size(message = "Valor não deve ser acima de 100 caracters", max = 100)
    private String descricao;

    private BigDecimal valor;

    private LocalDate vencimento;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Size(message = "Valor não deve ser acima de 500 caracters", max = 500)
    private String obs;

    public UUID getPagador() {
        return this.pagador;
    }

    public void setPagador(UUID pagador) {
        this.pagador = pagador;
    }

    public UUID getRenda() {
        return this.renda;
    }

    public void setRenda(UUID renda) {
        this.renda = renda;
    }

    public UUID getPatrimonio() {
        return this.patrimonio;
    }

    public void setPatrimonio(UUID patrimonio) {
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

}
