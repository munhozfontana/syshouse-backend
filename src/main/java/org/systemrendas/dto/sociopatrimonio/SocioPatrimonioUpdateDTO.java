package org.systemrendas.dto.sociopatrimonio;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class SocioPatrimonioUpdateDTO {

    @NotNull(message = "Não e permitido valor nulo")
    private UUID socioId;

    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioId;

    @NotNull(message = "Não e permitido valor nulo")
    private BigDecimal porcentagem;

    private Boolean proprietario;

    public UUID getSocioId() {
        return this.socioId;
    }

    public void setSocioId(UUID socioId) {
        this.socioId = socioId;
    }

    public UUID getPatrimonioId() {
        return this.patrimonioId;
    }

    public void setPatrimonioId(UUID patrimonioId) {
        this.patrimonioId = patrimonioId;
    }

    public BigDecimal getPorcentagem() {
        return this.porcentagem;
    }

    public void setPorcentagem(BigDecimal porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Boolean isProprietario() {
        return this.proprietario;
    }

    public Boolean getProprietario() {
        return this.proprietario;
    }

    public void setProprietario(Boolean proprietario) {
        this.proprietario = proprietario;
    }

}
