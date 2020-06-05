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
public class TipoPatrimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private UUID id;

    @Schema(readOnly = true)
    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String descricao;

    public TipoPatrimonio() {
    }

    public TipoPatrimonio(UUID id, Date createdAt, String descricao) {
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

    public TipoPatrimonio id(UUID id) {
        this.id = id;
        return this;
    }

    public TipoPatrimonio createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TipoPatrimonio descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TipoPatrimonio)) {
            return false;
        }
        TipoPatrimonio tipoPatrimonio = (TipoPatrimonio) o;
        return Objects.equals(id, tipoPatrimonio.id) && Objects.equals(createdAt, tipoPatrimonio.createdAt)
                && Objects.equals(descricao, tipoPatrimonio.descricao);
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
