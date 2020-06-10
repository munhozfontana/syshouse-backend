package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.TipoDespesa;
import org.systemrendas.dto.tipodespesa.TipoDespesaInsertDTO;
import org.systemrendas.dto.tipodespesa.TipoDespesaUpdateDTO;
import org.systemrendas.repositories.TipoDespesaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class TipoDespesaService {

    @Inject
    TipoDespesaRepository repo;

    private TipoDespesa find(final UUID id) {
        final Optional<TipoDespesa> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + TipoDespesaService.class.getName()));
    }

    public TipoDespesa findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<TipoDespesa> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
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

    public List<TipoDespesa> listAll() {
        return repo.listAll();
    }

    public TipoDespesa fromDTO(final TipoDespesaInsertDTO objDto) {
        TipoDespesa entidade = new TipoDespesa();
        entidade.setDescricao(objDto.getDescricao());
        return entidade;
    }

    public TipoDespesa fromDTO(TipoDespesaUpdateDTO objDto) {
        TipoDespesa entidade = new TipoDespesa();
        entidade.setDescricao(objDto.getDescricao());
        return entidade;
    }

    private void updateData(final TipoDespesa newObj, final TipoDespesa obj) {
        newObj.setDescricao(obj.getDescricao());
    }

}