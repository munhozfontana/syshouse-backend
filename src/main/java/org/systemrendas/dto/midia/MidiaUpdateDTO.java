package org.systemrendas.dto.midia;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MidiaUpdateDTO {

    @NotNull(message = "Não e permitido valor nulo")
    private UUID patrimonioId;

    @NotNull(message = "Não e permitido valor vazio")
    @Size(message = "Valor não deve ser acima de 255 caracters", max = 255)
    private String nome;

    @NotNull(message = "Não e permitido valor vazio")
    @Size(message = "Valor não deve ser acima de 255 caracters", max = 255)
    private String caminho;

    @Size(message = "Valor não deve ser acima de 500 caracters", max = 500)
    private String obs;

    @Size(message = "Valor não deve ser acima de 45 caracters", max = 45)
    private String tipo;

    public UUID getPatrimonioId() {
        return patrimonioId;
    }

    public void setPatrimonioId(UUID patrimonioId) {
        this.patrimonioId = patrimonioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
