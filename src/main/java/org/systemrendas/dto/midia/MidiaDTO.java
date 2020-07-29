package org.systemrendas.dto.midia;

import java.util.UUID;

public class MidiaDTO extends MidiaNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
