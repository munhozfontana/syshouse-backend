package org.systemrendas.dto.recebimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RecebimentoInsertDTO {

    @NotNull(message = "Não e permitido valor nulo")
    private UUID rendaId;

    @NotNull(message = "Não e permitido valor nulo")
    private BigDecimal valor;

    @NotNull(message = "Não e permitido valor nulo")
    private LocalDate dataRecebimento;

    @Size(message = "Valor não deve ser acima de 500 caracters", max = 500)
    private String obs;

    public UUID getRendaId() {
        return this.rendaId;
    }

    public void setRendaId(UUID rendaId) {
        this.rendaId = rendaId;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataRecebimento() {
        return this.dataRecebimento;
    }

    public void setDataRecebimento(LocalDate dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
