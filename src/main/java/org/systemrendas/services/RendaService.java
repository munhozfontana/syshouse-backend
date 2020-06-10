package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Renda;
import org.systemrendas.dto.renda.RendaInsertDTO;
import org.systemrendas.dto.renda.RendaUpdateDTO;
import org.systemrendas.repositories.RendaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class RendaService {

    @Inject
    RendaRepository repo;

    private Renda find(final UUID id) {
        final Optional<Renda> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + RendaService.class.getName()));
    }

    public Renda findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Renda> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Renda obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Renda obj) {
        final Renda newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException(
                    "Não é possível excluir, a entidade " + Renda.class.getName() + " contem outros relacionamentos",
                    null, RendaService.class.getName());

        }
    }

    public List<Renda> listAll() {
        return repo.listAll();
    }

    private void updateData(final Renda newObj, final Renda obj) {
        newObj.setId(null);
    }

    public Renda fromDTO(final RendaInsertDTO objDto) {
        Renda Renda = new Renda();
        return Renda;
    }

    public Renda fromDTO(RendaUpdateDTO dto) {
        return null;
    }

}