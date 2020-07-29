package org.systemrendas.dto.sociopatrimonio;

import java.util.UUID;

public class SocioPatrimonioDTO extends SocioPatrimonioNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
