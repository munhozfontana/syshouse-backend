package org.systemrendas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.TipoPatrimonio;
import org.systemrendas.dto.tipopatrimonio.TipoPatrimonioDTO;
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

    public TipoPatrimonioDTO findById(final UUID id) {
        return toDTO(find(id));
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

    public List<TipoPatrimonioDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private TipoPatrimonioDTO toDTO(TipoPatrimonio entidade) {
        TipoPatrimonioDTO newObj = new TipoPatrimonioDTO();
        newObj.setId(entidade.getId());
        newObj.setDescricao(entidade.getDescricao());
        return newObj;
    }

    public TipoPatrimonio fromDTO(final TipoPatrimonioDTO objDto) {
        TipoPatrimonio entidade = new TipoPatrimonio();
        entidade.setDescricao(objDto.getDescricao());
        entidade.createdAt(new Date());
        return entidade;
    }

    private void updateData(final TipoPatrimonio newObj, final TipoPatrimonio obj) {
        newObj.setDescricao(obj.getDescricao());
    }

}