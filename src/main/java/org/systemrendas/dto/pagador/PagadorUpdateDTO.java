package org.systemrendas.dto.pagador;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PagadorUpdateDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @NotNull(message = "Não e permitido valor nulo")
    @Max(message = "Valor não deve ser acima de 100 caracters", value = 100)
    private String nome;

    @JsonbDateFormat(value = "dd-MM-yyyy")
    private LocalDate nascimento;

    @Max(message = "Valor não deve ser acima de 11 caracters", value = 11)
    private String cpf;

    @Max(message = "Valor não deve ser acima de 45 caracters", value = 45)
    private String rg;

    @Max(message = "Valor não deve ser acima de 14 caracters", value = 14)
    private String cnpj;

    @Max(message = "Valor não deve ser acima de 45 caracters", value = 45)
    private String nacionalidade;

    @Max(message = "Valor não deve ser acima de 45 caracters", value = 45)
    private String estadoCivil;

    @Max(message = "Valor não deve ser acima de 45 caracters", value = 45)
    private String profissao;

    @Max(message = "Valor não deve ser acima de 500 caracters", value = 500)
    private String endereco;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
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

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
