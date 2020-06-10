package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Despesa;
import org.systemrendas.dto.despesa.DespesaInsertDTO;
import org.systemrendas.dto.despesa.DespesaUpdateDTO;
import org.systemrendas.repositories.DespesaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class DespesaService {

    @Inject
    DespesaRepository repo;

    private Despesa find(final UUID id) {
        final Optional<Despesa> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + DespesaService.class.getName()));
    }

    public Despesa findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Despesa> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Despesa obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Despesa obj) {
        final Despesa newObj = find(obj.getId());
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
                    "Não é possível excluir, a entidade " + Despesa.class.getName() + " contem outros relacionamentos",
                    null, DespesaService.class.getName());

        }
    }

    public List<Despesa> listAll() {
        return repo.listAll();
    }

    private void updateData(final Despesa newObj, final Despesa obj) {
        newObj.setId(null);
    }

    public Despesa fromDTO(final DespesaInsertDTO objDto) {
        Despesa despesa = new Despesa();
        return despesa;
    }

    public Despesa fromDTO(DespesaUpdateDTO dto) {
        return null;
    }

}