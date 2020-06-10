package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.RecebimentoPatrimonio;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class RecebimentoPatrimonioRepository implements PanacheRepositoryBase<RecebimentoPatrimonio, UUID> {

    public PanacheQuery<RecebimentoPatrimonio> listAllPage(Page pegeable) {
        return find("from RecebimentoPatrimonio").page(pegeable);
    }

}