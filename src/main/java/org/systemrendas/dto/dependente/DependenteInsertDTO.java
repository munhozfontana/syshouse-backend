package org.systemrendas.dto.dependente;

import java.util.UUID;

import javax.validation.constraints.Size;

import javax.validation.constraints.NotNull;

public class DependenteInsertDTO {

    @NotNull(message = "Não e permitido valor nulo")
    private UUID pagadorId;

    @NotNull(message = "Não e permitido valor nulo")
    @Size(message = "Valor não deve ser acima de 100 caracters", max = 100)
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
