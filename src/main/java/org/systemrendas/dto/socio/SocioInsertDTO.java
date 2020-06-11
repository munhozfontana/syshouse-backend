package org.systemrendas.dto.socio;

import javax.validation.constraints.Size;

import javax.validation.constraints.NotNull;

public class SocioInsertDTO {

    @NotNull(message = "Não e permitido valor nulo")
    @Size(message = "Valor não deve ser acima de 100 caracters", max = 100)
    private String nome;

    @NotNull(message = "Não e permitido valor nulo")
    @Size(message = "Valor não deve ser acima de 11 caracters", max = 11)
    private String cpf;

    @Size(message = "Valor não deve ser acima de 11 caracters", max = 20)
    private String rg;

    @Size(message = "Valor não deve ser acima de 11 caracters", max = 45)
    private String nacionalidade;

    @Size(message = "Valor não deve ser acima de 11 caracters", max = 45)
    private String estadoCivil;

    @Size(message = "Valor não deve ser acima de 11 caracters", max = 45)
    private String profissao;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return this.rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNacionalidade() {
        return this.nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getProfissao() {
        return this.profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

}
