package org.systemrendas.dto.dependente;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DependenteInsertDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID pagadorId;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    @Max(message = "Valor não deve ser acima de 100 caracters", value = 100)
    private String nome;

    public UUID getPagadorId() {
        return this.pagadorId;
    }

    public void setPagadorId(UUID pagadorId) {
        this.pagadorId = pagadorId;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
