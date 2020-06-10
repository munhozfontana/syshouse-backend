package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.Movimentacao;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class MovimentacaoRepository implements PanacheRepositoryBase<Movimentacao, UUID> {

    public PanacheQuery<Movimentacao> listAllPage(Page pegeable) {
        return find("from Movimentacao").page(pegeable);
    }

}