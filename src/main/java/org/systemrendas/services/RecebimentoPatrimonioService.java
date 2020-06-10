package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.RecebimentoPatrimonio;
import org.systemrendas.dto.recebimentopatrimonio.RecebimentoPatrimonioInsertDTO;
import org.systemrendas.dto.recebimentopatrimonio.RecebimentoPatrimonioUpdateDTO;
import org.systemrendas.repositories.RecebimentoPatrimonioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class RecebimentoPatrimonioService {

    @Inject
    RecebimentoPatrimonioRepository repo;

    private RecebimentoPatrimonio find(final UUID id) {
        final Optional<RecebimentoPatrimonio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + RecebimentoPatrimonioService.class.getName()));
    }

    public RecebimentoPatrimonio findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<RecebimentoPatrimonio> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final RecebimentoPatrimonio obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final RecebimentoPatrimonio obj) {
        final RecebimentoPatrimonio newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade "
                    + RecebimentoPatrimonio.class.getName() + " contem outros relacionamentos", null,
                    RecebimentoPatrimonioService.class.getName());

        }
    }

    public List<RecebimentoPatrimonio> listAll() {
        return repo.listAll();
    }

    private void updateData(final RecebimentoPatrimonio newObj, final RecebimentoPatrimonio obj) {
        newObj.setId(null);
    }

    public RecebimentoPatrimonio fromDTO(final RecebimentoPatrimonioInsertDTO objDto) {
        RecebimentoPatrimonio recebimentoPatrimonio = new RecebimentoPatrimonio();
        return recebimentoPatrimonio;
    }

    public RecebimentoPatrimonio fromDTO(RecebimentoPatrimonioUpdateDTO dto) {
        return null;
    }

}