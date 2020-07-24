package org.systemrendas.dto.pagamentopatrimonio;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class PagamentoPatrimonioDTO {
    private UUID id;

    @NotNull(message = "Não e permitido valor nulo")
    private UUID pagamentoId;

    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioId;

    @NotNull(message = "Não e permitido valor nulo")
    private BigDecimal valorCalculado;

    public UUID getPagamentoId() {
        return this.pagamentoId;
    }

    public void setPagamentoId(UUID pagamentoId) {
        this.pagamentoId = pagamentoId;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
