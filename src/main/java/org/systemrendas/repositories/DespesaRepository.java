package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Despesa;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class DespesaRepository implements PanacheRepositoryBase<Despesa, UUID> {

    public PanacheQuery<Despesa> listAllPage(Page pegeable) {
        return find("from Despesa").page(pegeable);
    }

}