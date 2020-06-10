package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Renda;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class RendaRepository implements PanacheRepositoryBase<Renda, UUID> {

    public PanacheQuery<Renda> listAllPage(Page pegeable) {
        return find("from Renda").page(pegeable);
    }

}