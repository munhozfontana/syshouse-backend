package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Midia;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class MidiaRepository implements PanacheRepositoryBase<Midia, UUID> {

    public PanacheQuery<Midia> listAllPage(Page pegeable) {
        return find("from Midia").page(pegeable);
    }

}