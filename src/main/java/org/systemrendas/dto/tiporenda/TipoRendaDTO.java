package org.systemrendas.dto.tiporenda;

import java.util.UUID;

public class TipoRendaDTO extends TipoRendaNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
