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
import org.systemrendas.domain.Pagamento;
import org.systemrendas.dto.pagamento.PagamentoDTO;
import org.systemrendas.dto.pagamento.PagamentoNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.PagamentoRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PagamentoService {

    @Inject
    PagamentoRepository repo;

    @Inject
    DespesaService despesaService;

    public Pagamento find(final UUID id) {
        final Optional<Pagamento> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PagamentoService.class.getName()));
    }

    public PagamentoDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<PagamentoDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Pagamento> listPanache = repo.listAllPage(pegeable);
        List<PagamentoDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
    }

    @Transactional
    public UUID insert(final Pagamento obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Pagamento obj) {
        final Pagamento newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Pagamento.class.getName()
                    + " contem outros relacionamentos", null, PagamentoService.class.getName());

        }
    }

    public List<PagamentoDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PagamentoDTO toDTO(Pagamento entidade) {
        PagamentoDTO newObj = new PagamentoDTO();
        newObj.setDataPagamento(entidade.getDataPagamento());
        newObj.setId(entidade.getId());
        newObj.setDespesaId(entidade.getDespesa().getId());
        newObj.setObs(entidade.getObs());
        newObj.setValor(entidade.getValor());
        return newObj;
    }

    public Pagamento fromDTO(final PagamentoNewDTO objDto) {
        Pagamento entidade = new Pagamento();
        entidade.setDataPagamento(objDto.getDataPagamento());
        entidade.setDespesa(despesaService.find(objDto.getDespesaId()));
        entidade.setObs(objDto.getObs());
        entidade.setValor(objDto.getValor());
        return entidade;
    }

    private void updateData(final Pagamento newObj, final Pagamento obj) {
        newObj.setDataPagamento(obj.getDataPagamento());
        newObj.setDespesa(obj.getDespesa());
        newObj.setObs(obj.getObs());
        newObj.setValor(obj.getValor());
    }

}