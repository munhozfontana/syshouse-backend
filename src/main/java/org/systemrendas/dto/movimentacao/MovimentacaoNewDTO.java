package org.systemrendas.dto.movimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovimentacaoNewDTO {
    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioOut;

    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioIn;

    @NotNull(message = "Não e permitido valor nulo")
    private BigDecimal valor;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate data;

    @Size(message = "Valor não deve ser acima de 500 caracters", max = 500)
    private String obs;

    public UUID getPatrimonioOut() {
        return this.patrimonioOut;
    }

    public void setPatrimonioOut(UUID patrimonioOut) {
        this.patrimonioOut = patrimonioOut;
    }

    public UUID getPatrimonioIn() {
        return this.patrimonioIn;
    }

    public void setPatrimonioIn(UUID patrimonioIn) {
        this.patrimonioIn = patrimonioIn;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
