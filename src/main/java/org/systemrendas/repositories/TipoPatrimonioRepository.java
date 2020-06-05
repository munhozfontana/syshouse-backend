package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.TipoPatrimonio;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class TipoPatrimonioRepository implements PanacheRepositoryBase<TipoPatrimonio, UUID> {

}