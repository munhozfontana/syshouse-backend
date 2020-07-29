package org.systemrendas.dto.recebimento;

import java.util.UUID;

public class RecebimentoDTO extends RecebimentoNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
