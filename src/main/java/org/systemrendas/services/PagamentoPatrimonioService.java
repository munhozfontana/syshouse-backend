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
import org.systemrendas.domain.PagamentoPatrimonio;
import org.systemrendas.dto.pagamentopatrimonio.PagamentoPatrimonioDTO;
import org.systemrendas.dto.pagamentopatrimonio.PagamentoPatrimonioNewDTO;
import org.systemrendas.dto.utils.Pagination;
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

    public PagamentoPatrimonioDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<PagamentoPatrimonioDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<PagamentoPatrimonio> listPanache = repo.listAllPage(pegeable);
        List<PagamentoPatrimonioDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
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

    public List<PagamentoPatrimonioDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private PagamentoPatrimonioDTO toDTO(PagamentoPatrimonio entidade) {
        PagamentoPatrimonioDTO newObj = new PagamentoPatrimonioDTO();
        newObj.setId(entidade.getId());
        newObj.setPagamentoId(entidade.getPagamento().getId());
        newObj.setPatrimonioId(entidade.getPatrimonio().getId());
        newObj.setValorCalculado(entidade.getValorCalculado());
        return newObj;
    }

    public PagamentoPatrimonio fromDTO(final PagamentoPatrimonioNewDTO objDto) {
        PagamentoPatrimonio entidade = new PagamentoPatrimonio();
        entidade.setPagamento(pagamentoService.find(objDto.getPagamentoId()));
        entidade.setPatrimonio(patrimonioService.find(objDto.getPatrimonioId()));
        entidade.setValorCalculado(objDto.getValorCalculado());
        return entidade;
    }

    private void updateData(final PagamentoPatrimonio newObj, final PagamentoPatrimonio obj) {
        newObj.setPagamento(obj.getPagamento());
        newObj.setPatrimonio(obj.getPatrimonio());
        newObj.setValorCalculado(obj.getValorCalculado());
    }

}