package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Socio;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class SocioRepository implements PanacheRepositoryBase<Socio, UUID> {

    public PanacheQuery<Socio> listAllPage(Page pegeable) {
        return find("from Socio").page(pegeable);
    }

}