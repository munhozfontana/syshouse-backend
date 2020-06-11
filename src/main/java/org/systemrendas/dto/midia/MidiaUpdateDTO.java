package org.systemrendas.dto.midia;

import javax.validation.constraints.Size;

import javax.validation.constraints.NotNull;

public class MidiaUpdateDTO {

    @NotNull(message = "Não e permitido valor nulo")
    @Size(message = "Valor não deve ser acima de 255 caracters", max = 255)
    private String nome;

    @Size(message = "Valor não deve ser acima de 255 caracters", max = 255)
    private String caminho;

    @Size(message = "Valor não deve ser acima de 500 caracters", max = 500)
    private String obs;

    @Size(message = "Valor não deve ser acima de 45 caracters", max = 45)
    private String tipo;

}
