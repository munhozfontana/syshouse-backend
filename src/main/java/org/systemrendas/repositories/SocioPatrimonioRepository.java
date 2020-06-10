package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.SocioPatrimonio;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class SocioPatrimonioRepository implements PanacheRepositoryBase<SocioPatrimonio, UUID> {

    public PanacheQuery<SocioPatrimonio> listAllPage(Page pegeable) {
        return find("from SocioPatrimonio").page(pegeable);
    }

}