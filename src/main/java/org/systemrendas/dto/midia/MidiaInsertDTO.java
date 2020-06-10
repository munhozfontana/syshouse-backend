package org.systemrendas.dto.midia;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MidiaInsertDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioId;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    @Max(message = "Valor não deve ser acima de 255 caracters", value = 255)
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    @Max(message = "Valor não deve ser acima de 255 caracters", value = 255)
    private String caminho;

    @Max(message = "Valor não deve ser acima de 500 caracters", value = 500)
    private String obs;

    @Max(message = "Valor não deve ser acima de 45 caracters", value = 45)
    private String tipo;

}
