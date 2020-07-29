package org.systemrendas.dto.contato;

import java.util.UUID;

public class ContatoDTO extends ContatoNewDTO {

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
