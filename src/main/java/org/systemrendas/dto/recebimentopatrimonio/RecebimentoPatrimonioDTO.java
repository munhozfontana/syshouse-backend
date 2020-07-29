package org.systemrendas.dto.recebimentopatrimonio;

import java.util.UUID;

public class RecebimentoPatrimonioDTO extends RecebimentoPatrimonioNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
