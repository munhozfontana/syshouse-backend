package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Dependente;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class DependenteRepository implements PanacheRepositoryBase<Dependente, UUID> {

    public PanacheQuery<Dependente> listAllPage(Page pegeable) {
        return find("from Dependente").page(pegeable);
    }

}