package org.systemrendas.dto.tiporenda;

import javax.validation.constraints.Size;

import javax.validation.constraints.NotNull;

public class TipoRendaInsertDTO {

    @NotNull(message = "Não e permitido valor nulo")
    @Size(message = "Valor não deve ser acima de 100 caracters", max = 100)
    private String descricao;

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
