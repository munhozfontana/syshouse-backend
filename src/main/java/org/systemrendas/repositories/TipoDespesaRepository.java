package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.TipoDespesa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class TipoDespesaRepository implements PanacheRepositoryBase<TipoDespesa, UUID> {

}