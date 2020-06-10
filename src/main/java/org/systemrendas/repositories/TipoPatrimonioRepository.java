package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.TipoPatrimonio;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class TipoPatrimonioRepository implements PanacheRepositoryBase<TipoPatrimonio, UUID> {

    public PanacheQuery<TipoPatrimonio> listAllPage(Page pegeable) {
        return find("from TipoPatrimonio").page(pegeable);
    }

}