package org.systemrendas.domain;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private UUID id;

    @Schema(readOnly = true)
    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(length = 100)
    private String nome;

    @Column(length = 11)
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(length = 45)
    private String nacionalidade;

    @Column(length = 45)
    private String estadoCivil;

    @Column(length = 45)
    private String profissao;

    public Socio() {
    }

    public Socio(UUID id, String nome, String cpf, String rg, String nacionalidade, String estadoCivil,
            String profissao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nacionalidade = nacionalidade;
        this.estadoCivil = estadoCivil;
        this.profissao = profissao;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Socio id(UUID id) {
        this.id = id;
        return this;
    }

    public Socio nome(String nome) {
        this.nome = nome;
        return this;
    }

    public Socio cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Socio rg(String rg) {
        this.rg = rg;
        return this;
    }

    public Socio nacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
        return this;
    }

    public Socio estadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
        return this;
    }

    public Socio profissao(String profissao) {
        this.profissao = profissao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Socio)) {
            return false;
        }
        Socio socio = (Socio) o;
        return Objects.equals(id, socio.id) && Objects.equals(nome, socio.nome) && Objects.equals(cpf, socio.cpf)
                && Objects.equals(rg, socio.rg) && Objects.equals(nacionalidade, socio.nacionalidade)
                && Objects.equals(estadoCivil, socio.estadoCivil) && Objects.equals(profissao, socio.profissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, rg, nacionalidade, estadoCivil, profissao);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", nome='" + getNome() + "'" + ", cpf='" + getCpf() + "'" + ", rg='"
                + getRg() + "'" + ", nacionalidade='" + getNacionalidade() + "'" + ", estadoCivil='" + getEstadoCivil()
                + "'" + ", profissao='" + getProfissao() + "'" + "}";
    }

}
