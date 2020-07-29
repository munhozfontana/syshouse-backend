package org.systemrendas.dto.pagador;

import java.util.UUID;

public class PagadorDTO extends PagadorNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
