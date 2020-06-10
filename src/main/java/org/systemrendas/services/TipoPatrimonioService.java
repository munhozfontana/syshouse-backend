package org.systemrendas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.TipoPatrimonio;
import org.systemrendas.dto.tipopatrimonio.TipoPatrimonioInsertDTO;
import org.systemrendas.dto.tipopatrimonio.TipoPatrimonioUpdateDTO;
import org.systemrendas.repositories.TipoPatrimonioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class TipoPatrimonioService {

    @Inject
    TipoPatrimonioRepository repo;

    private TipoPatrimonio find(final UUID id) {
        final Optional<TipoPatrimonio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + TipoPatrimonioService.class.getName()));
    }

    public TipoPatrimonio findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<TipoPatrimonio> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final TipoPatrimonio obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final TipoPatrimonio obj) {
        final TipoPatrimonio newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + TipoPatrimonio.class.getName()
                    + " contem outros relacionamentos", null, TipoPatrimonioService.class.getName());

        }
    }

    public List<TipoPatrimonio> listAll() {
        return repo.listAll();
    }

    public TipoPatrimonio fromDTO(final TipoPatrimonioInsertDTO objDto) {
        TipoPatrimonio entidade = new TipoPatrimonio();
        entidade.setDescricao(objDto.getDescricao());
        entidade.createdAt(new Date());
        return entidade;
    }

    public TipoPatrimonio fromDTO(TipoPatrimonioUpdateDTO objDto) {
        TipoPatrimonio entidade = new TipoPatrimonio();
        entidade.setDescricao(objDto.getDescricao());
        return entidade;
    }

    private void updateData(final TipoPatrimonio newObj, final TipoPatrimonio obj) {
        newObj.setDescricao(obj.getDescricao());
    }

}