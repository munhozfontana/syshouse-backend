package org.systemrendas.dto.socio;

public class SocioRelationshipsCountDTO {
    private Long contatoCount;
    private Long socioPatrimonioCount;

    public Long getContatoCount() {
        return this.contatoCount;
    }

    public void setContatoCount(Long contatoCount) {
        this.contatoCount = contatoCount;
    }

    public Long getSocioPatrimonioCount() {
        return this.socioPatrimonioCount;
    }

    public void setSocioPatrimonioCount(Long socioPatrimonioCount) {
        this.socioPatrimonioCount = socioPatrimonioCount;
    }

    public SocioRelationshipsCountDTO(Long contatoCount, Long socioPatrimonioCount) {
        this.contatoCount = contatoCount;
        this.socioPatrimonioCount = socioPatrimonioCount;
    }

}