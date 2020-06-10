package org.systemrendas.dto.recebimentopatrimonio;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RecebimentoPatrimonioUpdateDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID recebimentoId;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioId;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private BigDecimal valorCalculado;

    public UUID getRecebimentoId() {
        return this.recebimentoId;
    }

    public void setRecebimentoId(UUID recebimentoId) {
        this.recebimentoId = recebimentoId;
    }

    public UUID getPatrimonioId() {
        return this.patrimonioId;
    }

    public void setPatrimonioId(UUID patrimonioId) {
        this.patrimonioId = patrimonioId;
    }

    public BigDecimal getValorCalculado() {
        return this.valorCalculado;
    }

    public void setValorCalculado(BigDecimal valorCalculado) {
        this.valorCalculado = valorCalculado;
    }

}
