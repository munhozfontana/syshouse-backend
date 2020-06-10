package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Recebimento;
import org.systemrendas.dto.recebimento.RecebimentoInsertDTO;
import org.systemrendas.dto.recebimento.RecebimentoUpdateDTO;
import org.systemrendas.repositories.RecebimentoRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class RecebimentoService {

    @Inject
    RecebimentoRepository repo;

    private Recebimento find(final UUID id) {
        final Optional<Recebimento> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + RecebimentoService.class.getName()));
    }

    public Recebimento findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Recebimento> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Recebimento obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Recebimento obj) {
        final Recebimento newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Recebimento.class.getName()
                    + " contem outros relacionamentos", null, RecebimentoService.class.getName());

        }
    }

    public List<Recebimento> listAll() {
        return repo.listAll();
    }

    private void updateData(final Recebimento newObj, final Recebimento obj) {
        newObj.setId(null);
    }

    public Recebimento fromDTO(final RecebimentoInsertDTO objDto) {
        Recebimento Recebimento = new Recebimento();
        return Recebimento;
    }

    public Recebimento fromDTO(RecebimentoUpdateDTO dto) {
        return null;
    }

}