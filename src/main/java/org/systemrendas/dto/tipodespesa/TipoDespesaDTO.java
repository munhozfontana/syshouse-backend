package org.systemrendas.dto.tipodespesa;

import javax.validation.constraints.Size;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class TipoDespesaDTO {
    private UUID id;

    @NotNull(message = "Não e permitido valor nulo")
    @Size(message = "Valor não deve ser acima de 100 caracters", max = 100)
    private String descricao;

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
