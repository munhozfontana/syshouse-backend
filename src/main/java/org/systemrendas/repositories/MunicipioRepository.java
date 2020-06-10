package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Municipio;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepositoryBase<Municipio, UUID> {

    public PanacheQuery<Municipio> listAllPage(Page pegeable) {
        return find("from Municipio").page(pegeable);
    }

}