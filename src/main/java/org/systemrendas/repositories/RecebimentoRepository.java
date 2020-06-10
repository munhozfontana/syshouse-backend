package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Recebimento;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class RecebimentoRepository implements PanacheRepositoryBase<Recebimento, UUID> {

    public PanacheQuery<Recebimento> listAllPage(Page pegeable) {
        return find("from Recebimento").page(pegeable);
    }

}