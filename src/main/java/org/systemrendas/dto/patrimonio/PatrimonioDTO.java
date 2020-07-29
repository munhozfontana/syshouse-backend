package org.systemrendas.dto.patrimonio;

import java.util.UUID;

public class PatrimonioDTO extends PatrimonioNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
