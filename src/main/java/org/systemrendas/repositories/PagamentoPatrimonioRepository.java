package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.PagamentoPatrimonio;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PagamentoPatrimonioRepository implements PanacheRepositoryBase<PagamentoPatrimonio, UUID> {

}