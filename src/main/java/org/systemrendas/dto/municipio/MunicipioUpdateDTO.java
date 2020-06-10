package org.systemrendas.dto.municipio;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MunicipioUpdateDTO {

    private Integer ibge;

    @NotNull(message = "Não e permitido valor nulo")
    @Size(message = "Valor não deve ser acima de 50 caracters", max = 50)
    private String nome;

    @Size(message = "Valor não deve ser acima de 2 caracters", max = 2)
    private String uf;

    @Size(message = "Valor não deve ser acima de 50 caracters", max = 50)
    private String pais;

    private Integer populacao;

    public Integer getIbge() {
        return this.ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getPopulacao() {
        return this.populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

}
