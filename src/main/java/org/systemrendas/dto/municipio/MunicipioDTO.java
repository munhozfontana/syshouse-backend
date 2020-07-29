package org.systemrendas.dto.municipio;

import java.util.UUID;

public class MunicipioDTO extends MunicipioNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
