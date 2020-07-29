package org.systemrendas.dto.tipopatrimonio;

import java.util.UUID;

public class TipoPatrimonioDTO extends TipoPatrimonioNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}