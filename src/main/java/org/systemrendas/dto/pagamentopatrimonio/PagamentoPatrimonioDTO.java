package org.systemrendas.dto.pagamentopatrimonio;

import java.util.UUID;

public class PagamentoPatrimonioDTO extends PagamentoPatrimonioNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
