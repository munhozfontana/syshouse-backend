package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.PagamentoPatrimonio;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PagamentoPatrimonioRepository implements PanacheRepositoryBase<PagamentoPatrimonio, UUID> {

    public PanacheQuery<PagamentoPatrimonio> listAllPage(Page pegeable) {
        return find("from PagamentoPatrimonio").page(pegeable);
    }

}