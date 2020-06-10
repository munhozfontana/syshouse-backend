package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.PagamentoPatrimonio;
import org.systemrendas.dto.pagamentopatrimonio.PagamentoPatrimonioInsertDTO;
import org.systemrendas.dto.pagamentopatrimonio.PagamentoPatrimonioUpdateDTO;
import org.systemrendas.repositories.PagamentoPatrimonioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PagamentoPatrimonioService {

    @Inject
    PagamentoPatrimonioRepository repo;

    @Inject
    PagamentoService pagamentoService;

    @Inject
    PatrimonioService patrimonioService;

    private PagamentoPatrimonio find(final UUID id) {
        final Optional<PagamentoPatrimonio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PagamentoPatrimonioService.class.getName()));
    }

    public PagamentoPatrimonio findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<PagamentoPatrimonio> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final PagamentoPatrimonio obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final PagamentoPatrimonio obj) {
        final PagamentoPatrimonio newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + PagamentoPatrimonio.class.getName()
                    + " contem outros relacionamentos", null, PagamentoPatrimonioService.class.getName());

        }
    }

    public List<PagamentoPatrimonio> listAll() {
        return repo.listAll();
    }

    public PagamentoPatrimonio fromDTO(final PagamentoPatrimonioInsertDTO objDto) {
        PagamentoPatrimonio entidade = new PagamentoPatrimonio();
        entidade.setPagamento(pagamentoService.findById(objDto.getPagamentoId()));
        entidade.setPatrimonio(patrimonioService.findById(objDto.getPatrimonioId()));
        entidade.setValorCalculado(objDto.getValorCalculado());
        return entidade;
    }

    public PagamentoPatrimonio fromDTO(PagamentoPatrimonioUpdateDTO objDto) {
        PagamentoPatrimonio entidade = new PagamentoPatrimonio();
        entidade.setPagamento(pagamentoService.findById(objDto.getPagamentoId()));
        entidade.setPatrimonio(patrimonioService.findById(objDto.getPatrimonioId()));
        entidade.setValorCalculado(objDto.getValorCalculado());
        return entidade;
    }

    private void updateData(final PagamentoPatrimonio newObj, final PagamentoPatrimonio obj) {
        newObj.setPagamento(obj.getPagamento());
        newObj.setPatrimonio(obj.getPatrimonio());
        newObj.setValorCalculado(obj.getValorCalculado());
    }

}