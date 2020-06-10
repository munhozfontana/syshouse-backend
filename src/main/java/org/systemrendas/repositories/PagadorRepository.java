package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Pagador;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PagadorRepository implements PanacheRepositoryBase<Pagador, UUID> {

    public PanacheQuery<Pagador> listAllPage(Page pegeable) {
        return find("from Pagador").page(pegeable);
    }

}