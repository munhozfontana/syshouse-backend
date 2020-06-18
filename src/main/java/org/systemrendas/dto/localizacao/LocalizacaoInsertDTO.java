package org.systemrendas.dto.localizacao;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.Size;

import javax.validation.constraints.NotNull;

public class LocalizacaoInsertDTO {

    @NotNull(message = "Não e permitido valor nulo")
    @Size(message = "Valor não deve ser acima de 500 caracters", max = 500)
    private String endereco;

    @Size(message = "Valor não deve ser acima de 50 caracters", max = 50)
    private String bairro;

    @Size(message = "Valor não deve ser acima de 8 caracters", max = 8)
    private String cep;

    @Size(message = "Valor não deve ser acima de 1000 caracters", max = 1000)
    private String complemento;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @NotNull(message = "Não e permitido valor nulo")
    private UUID municipioId;

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public BigDecimal getLatitude() {
        return this.latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return this.longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public UUID getMunicipioId() {
        return this.municipioId;
    }

    public void setMunicipioId(UUID municipioId) {
        this.municipioId = municipioId;
    }

}
