package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.RecebimentoPatrimonio;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class RecebimentoPatrimonioRepository implements PanacheRepositoryBase<RecebimentoPatrimonio, UUID> {

}