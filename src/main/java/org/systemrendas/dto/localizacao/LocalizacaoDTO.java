package org.systemrendas.dto.localizacao;

import java.util.UUID;

public class LocalizacaoDTO extends LocalizacaoNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
