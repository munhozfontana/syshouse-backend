package org.systemrendas.dto.pagamento;

import java.util.UUID;

public class PagamentoDTO extends PagamentoNewDTO {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
