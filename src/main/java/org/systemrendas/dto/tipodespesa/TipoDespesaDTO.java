package org.systemrendas.dto.tipodespesa;

import java.util.UUID;

public class TipoDespesaDTO extends TipoDespesaNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
