package org.systemrendas.dto.tipopatrimonio;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoPatrimonioNewDTO {
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
