package org.systemrendas.dto.tiporenda;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TipoRendaInsertDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    @Max(message = "Valor não deve ser acima de 100 caracters", value = 100)
    private String descricao;

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
