package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.TipoDespesa;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class TipoDespesaRepository implements PanacheRepositoryBase<TipoDespesa, UUID> {

    public PanacheQuery<TipoDespesa> listAllPage(Page pegeable) {
        return find("from TipoDespesa").page(pegeable);
    }

}