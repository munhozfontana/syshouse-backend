package org.systemrendas.dto.despesa;

import java.util.UUID;

public class DespesaDTO extends DespesaNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
