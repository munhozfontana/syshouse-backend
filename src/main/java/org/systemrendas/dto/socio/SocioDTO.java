package org.systemrendas.dto.socio;

import java.util.UUID;

public class SocioDTO extends SocioNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
