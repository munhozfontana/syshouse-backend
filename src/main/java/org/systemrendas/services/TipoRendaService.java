package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.TipoRenda;
import org.systemrendas.dto.tiporenda.TipoRendaInsertDTO;
import org.systemrendas.dto.tiporenda.TipoRendaUpdateDTO;
import org.systemrendas.repositories.TipoRendaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class TipoRendaService {

    @Inject
    TipoRendaRepository repo;

    private TipoRenda find(final UUID id) {
        final Optional<TipoRenda> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + TipoRendaService.class.getName()));
    }

    public TipoRenda findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<TipoRenda> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final TipoRenda obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final TipoRenda obj) {
        final TipoRenda newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + TipoRenda.class.getName()
                    + " contem outros relacionamentos", null, TipoRendaService.class.getName());

        }
    }

    public List<TipoRenda> listAll() {
        return repo.listAll();
    }

    private void updateData(final TipoRenda newObj, final TipoRenda obj) {
        newObj.setDescricao(obj.getDescricao());
    }

    public TipoRenda fromDTO(final TipoRendaInsertDTO objDto) {
        TipoRenda tipoRenda = new TipoRenda();
        return tipoRenda;
    }

    public TipoRenda fromDTO(TipoRendaUpdateDTO dto) {
        return null;
    }

}