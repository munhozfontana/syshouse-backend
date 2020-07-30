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
import org.systemrendas.domain.TipoDespesa;
import org.systemrendas.dto.tipodespesa.TipoDespesaDTO;
import org.systemrendas.dto.tipodespesa.TipoDespesaNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.TipoDespesaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class TipoDespesaService {

    @Inject
    TipoDespesaRepository repo;

    public TipoDespesa find(final UUID id) {
        final Optional<TipoDespesa> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + TipoDespesaService.class.getName()));
    }

    public TipoDespesaDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<TipoDespesaDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<TipoDespesa> listPanache = repo.listAllPage(pegeable);
        List<TipoDespesaDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
    }

    @Transactional
    public UUID insert(final TipoDespesa obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final TipoDespesa obj) {
        final TipoDespesa newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + TipoDespesa.class.getName()
                    + " contem outros relacionamentos", null, TipoDespesaService.class.getName());

        }
    }

    public List<TipoDespesaDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TipoDespesaDTO toDTO(TipoDespesa entidade) {
        TipoDespesaDTO newObj = new TipoDespesaDTO();
        newObj.setId(entidade.getId());
        newObj.setDescricao(entidade.getDescricao());
        return newObj;
    }

    public TipoDespesa fromDTO(final TipoDespesaNewDTO objDto) {
        TipoDespesa entidade = new TipoDespesa();
        entidade.setDescricao(objDto.getDescricao());
        return entidade;
    }

    private void updateData(final TipoDespesa newObj, final TipoDespesa obj) {
        newObj.setDescricao(obj.getDescricao());
    }

}