package org.systemrendas.dto.contato;

import java.util.UUID;

import javax.validation.constraints.Max;

public class ContatoUpdateDTO {

    @Max(message = "Valor não deve ser acima de 11 caracters", value = 11)
    private String fone;

    private Boolean whatsapp;

    @Max(message = "Valor não deve ser acima de 100 caracters", value = 100)
    private String email;

    private UUID pagadorId;

    private UUID socioId;

    public String getFone() {
        return this.fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Boolean isWhatsapp() {
        return this.whatsapp;
    }

    public Boolean getWhatsapp() {
        return this.whatsapp;
    }

    public void setWhatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getPagadorId() {
        return this.pagadorId;
    }

    public void setPagadorId(UUID pagadorId) {
        this.pagadorId = pagadorId;
    }

    public UUID getSocioId() {
        return this.socioId;
    }

    public void setSocioId(UUID socioId) {
        this.socioId = socioId;
    }

}
