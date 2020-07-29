package org.systemrendas.dto.dependente;

import java.util.UUID;

public class DependenteDTO extends DependenteNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
