package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.TipoRenda;
import org.systemrendas.dto.tiporenda.TipoRendaDTO;
import org.systemrendas.dto.tiporenda.TipoRendaNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.TipoRendaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class TipoRendaService {

    @Inject
    TipoRendaRepository repo;

    public TipoRenda find(final UUID id) {
        final Optional<TipoRenda> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + TipoRendaService.class.getName()));
    }

    public TipoRendaDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<TipoRendaDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<TipoRenda> listPanache = repo.listAllPage(pegeable);
        List<TipoRendaDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
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

    public List<TipoRendaDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TipoRendaDTO toDTO(TipoRenda entidade) {
        TipoRendaDTO newObj = new TipoRendaDTO();
        newObj.setId(entidade.getId());
        newObj.setDescricao(entidade.getDescricao());
        return newObj;
    }

    public TipoRenda fromDTO(final TipoRendaNewDTO objDto) {
        TipoRenda entidade = new TipoRenda();
        entidade.setDescricao(objDto.getDescricao());
        return entidade;
    }

    private void updateData(final TipoRenda newObj, final TipoRenda obj) {
        newObj.setDescricao(obj.getDescricao());
    }

}