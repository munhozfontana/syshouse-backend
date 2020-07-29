package org.systemrendas.dto.movimentacao;

import java.util.UUID;

public class MovimentacaoDTO extends MovimentacaoNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
