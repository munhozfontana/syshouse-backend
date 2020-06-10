package org.systemrendas.dto.municipio;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MunicipioUpdateDTO {

    private Integer ibge;

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    @Max(message = "Valor não deve ser acima de 50 caracters", value = 50)
    private String nome;

    @Max(message = "Valor não deve ser acima de 2 caracters", value = 2)
    private String uf;

    @Max(message = "Valor não deve ser acima de 50 caracters", value = 50)
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
