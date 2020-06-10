package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Municipio;
import org.systemrendas.dto.municipio.MunicipioInsertDTO;
import org.systemrendas.dto.municipio.MunicipioUpdateDTO;
import org.systemrendas.repositories.MunicipioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class MunicipioService {

    @Inject
    MunicipioRepository repo;

    private Municipio find(final UUID id) {
        final Optional<Municipio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + MunicipioService.class.getName()));
    }

    public Municipio findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Municipio> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Municipio obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Municipio obj) {
        final Municipio newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Municipio.class.getName()
                    + " contem outros relacionamentos", null, MunicipioService.class.getName());

        }
    }

    public List<Municipio> listAll() {
        return repo.listAll();
    }

    public Municipio fromDTO(final MunicipioInsertDTO objDto) {
        Municipio municipio = new Municipio();
        municipio.setIbge(objDto.getIbge());
        municipio.setNome(objDto.getNome());
        municipio.setPais(objDto.getNome());
        municipio.setPopulacao(objDto.getPopulacao());
        municipio.setUf(objDto.getUf());
        return municipio;
    }

    public Municipio fromDTO(MunicipioUpdateDTO objDto) {
        Municipio municipio = new Municipio();
        municipio.setIbge(objDto.getIbge());
        municipio.setNome(objDto.getNome());
        municipio.setPais(objDto.getNome());
        municipio.setPopulacao(objDto.getPopulacao());
        municipio.setUf(objDto.getUf());
        return municipio;
    }

    private void updateData(final Municipio newObj, final Municipio obj) {
        newObj.setIbge(obj.getIbge());
        newObj.setNome(obj.getNome());
        newObj.setPais(obj.getNome());
        newObj.setPopulacao(obj.getPopulacao());
        newObj.setUf(obj.getUf());
    }
}