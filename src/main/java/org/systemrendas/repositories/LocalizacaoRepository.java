package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Localizacao;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class LocalizacaoRepository implements PanacheRepositoryBase<Localizacao, UUID> {

    public PanacheQuery<Localizacao> listAllPage(Page pegeable) {
        return find("from Localizacao").page(pegeable);
    }

}