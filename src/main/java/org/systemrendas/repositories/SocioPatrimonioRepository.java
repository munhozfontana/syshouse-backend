package org.systemrendas.repositories;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import org.systemrendas.domain.SocioPatrimonio;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class SocioPatrimonioRepository implements PanacheRepositoryBase<SocioPatrimonio, UUID> {

}