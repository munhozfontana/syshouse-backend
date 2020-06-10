package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Contato;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class ContatoRepository implements PanacheRepositoryBase<Contato, UUID> {

    public PanacheQuery<Contato> listAllPage(Page pegeable) {
        return find("from Contato").page(pegeable);
    }

}