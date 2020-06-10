package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Pagamento;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepositoryBase<Pagamento, UUID> {

    public PanacheQuery<Pagamento> listAllPage(Page pegeable) {
        return find("from Pagamento").page(pegeable);
    }

}