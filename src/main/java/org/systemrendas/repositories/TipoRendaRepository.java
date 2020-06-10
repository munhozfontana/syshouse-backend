package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.TipoRenda;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class TipoRendaRepository implements PanacheRepositoryBase<TipoRenda, UUID> {

    public PanacheQuery<TipoRenda> listAllPage(Page pegeable) {
        return find("from TipoRenda").page(pegeable);
    }

}