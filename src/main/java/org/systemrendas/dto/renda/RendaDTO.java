package org.systemrendas.dto.renda;

import java.util.UUID;

public class RendaDTO extends RendaNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }
}
