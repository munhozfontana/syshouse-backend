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

import com.fasterxml.jackson.annotation.JsonFormat;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class TipoDespesa {

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
    private String descricao;

    public TipoDespesa() {
    }

    public TipoDespesa(UUID id, Date createdAt, String descricao) {
        this.id = id;
        this.createdAt = createdAt;
        this.descricao = descricao;
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

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoDespesa id(UUID id) {
        this.id = id;
        return this;
    }

    public TipoDespesa createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TipoDespesa descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TipoDespesa)) {
            return false;
        }
        TipoDespesa tipoDespesa = (TipoDespesa) o;
        return Objects.equals(id, tipoDespesa.id) && Objects.equals(createdAt, tipoDespesa.createdAt)
                && Objects.equals(descricao, tipoDespesa.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, descricao);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", createdAt='" + getCreatedAt() + "'" + ", descricao='" + getDescricao()
                + "'" + "}";
    }

}
