package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Patrimonio;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PatrimonioRepository implements PanacheRepositoryBase<Patrimonio, UUID> {

    public PanacheQuery<Patrimonio> listAllPage(Page pegeable) {
        return find("from Patrimonio").page(pegeable);
    }

}