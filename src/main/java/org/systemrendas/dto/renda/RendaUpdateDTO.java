package org.systemrendas.dto.renda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RendaUpdateDTO {

    @NotNull(message = "Não e permitido valor nulo")
    private UUID pagadorId;

    @NotNull(message = "Não e permitido valor nulo")
    private UUID rendaId;

    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioId;

    @Size(message = "Valor não deve ser acima de 100 caracters", max = 100)
    private String descricao;

    private BigDecimal valor;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate vencimento;

    @NotNull(message = "Não e permitido valor nulo")
    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate dataFim;

    @Size(message = "Valor não deve ser acima de 500 caracters", max = 500)
    private String obs;

    public UUID getPagadorId() {
        return this.pagadorId;
    }

    public void setPagadorId(UUID pagadorId) {
        this.pagadorId = pagadorId;
    }

    public UUID getRendaId() {
        return this.rendaId;
    }

    public void setRendaId(UUID rendaId) {
        this.rendaId = rendaId;
    }

    public UUID getPatrimonioId() {
        return this.patrimonioId;
    }

    public void setPatrimonioId(UUID patrimonioId) {
        this.patrimonioId = patrimonioId;
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
