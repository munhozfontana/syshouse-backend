package org.systemrendas.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Pagador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private UUID id;

    @Schema(readOnly = true)
    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

    @Column(length = 100)
    private String nome;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate nascimento;

    @Column(length = 11)
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(length = 14)
    private String cnpj;

    @Column(length = 45)
    private String nacionalidade;

    @Column(length = 45)
    private String estadoCivil;

    @Column(length = 45)
    private String profissao;

    @Column(length = 500)
    private String endereco;

    public Pagador() {
    }

    public Pagador(UUID id, Date createdAt, String nome, LocalDate nascimento, String cpf, String rg, String cnpj,
            String nacionalidade, String estadoCivil, String profissao, String endereco) {
        this.id = id;
        this.createdAt = createdAt;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.cnpj = cnpj;
        this.nacionalidade = nacionalidade;
        this.estadoCivil = estadoCivil;
        this.profissao = profissao;
        this.endereco = endereco;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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

    public Pagador id(UUID id) {
        this.id = id;
        return this;
    }

    public Pagador createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Pagador nome(String nome) {
        this.nome = nome;
        return this;
    }

    public Pagador nascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public Pagador cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Pagador rg(String rg) {
        this.rg = rg;
        return this;
    }

    public Pagador cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public Pagador nacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
        return this;
    }

    public Pagador estadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
        return this;
    }

    public Pagador profissao(String profissao) {
        this.profissao = profissao;
        return this;
    }

    public Pagador endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pagador)) {
            return false;
        }
        Pagador pagador = (Pagador) o;
        return Objects.equals(id, pagador.id) && Objects.equals(createdAt, pagador.createdAt)
                && Objects.equals(nome, pagador.nome) && Objects.equals(nascimento, pagador.nascimento)
                && Objects.equals(cpf, pagador.cpf) && Objects.equals(rg, pagador.rg)
                && Objects.equals(cnpj, pagador.cnpj) && Objects.equals(nacionalidade, pagador.nacionalidade)
                && Objects.equals(estadoCivil, pagador.estadoCivil) && Objects.equals(profissao, pagador.profissao)
                && Objects.equals(endereco, pagador.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, nome, nascimento, cpf, rg, cnpj, nacionalidade, estadoCivil, profissao,
                endereco);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", nome='" + getNome() + "'"
                + ", nascimento='" + getNascimento() + "'" + ", cpf='" + getCpf() + "'" + ", rg='" + getRg() + "'"
                + ", cnpj='" + getCnpj() + "'" + ", nacionalidade='" + getNacionalidade() + "'" + ", estadoCivil='"
                + getEstadoCivil() + "'" + ", profissao='" + getProfissao() + "'" + ", endereco='" + getEndereco() + "'"
                + "}";
    }

}
