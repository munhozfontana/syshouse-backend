package org.systemrendas.dto.patrimonio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PatrimonioDTO {
    private UUID id;

    @NotNull(message = "Não e permitido valor nulo")
    @Size(message = "Valor não deve ser acima de 100 caracters", max = 100)
    private String nome;

    @NotNull(message = "Não e permitido valor nulo")
    private BigDecimal valor;

    @NotNull(message = "Não e permitido valor nulo")
    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate dataFim;

    @NotNull(message = "Não e permitido valor nulo")
    private UUID tipoPatrimonioId;

    @NotNull(message = "Não e permitido valor nulo")
    private UUID localizacaoId;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public UUID getTipoPatrimonioId() {
        return this.tipoPatrimonioId;
    }

    public void setTipoPatrimonioId(UUID tipoPatrimonioId) {
        this.tipoPatrimonioId = tipoPatrimonioId;
    }

    public UUID getLocalizacaoId() {
        return this.localizacaoId;
    }

    public void setLocalizacaoId(UUID localizacaoId) {
        this.localizacaoId = localizacaoId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
