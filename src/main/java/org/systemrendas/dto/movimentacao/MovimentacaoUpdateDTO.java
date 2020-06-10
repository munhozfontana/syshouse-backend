package org.systemrendas.dto.movimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MovimentacaoUpdateDTO {
    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioOut;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioIn;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private BigDecimal valor;

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
