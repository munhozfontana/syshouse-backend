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
import org.systemrendas.domain.Despesa;
import org.systemrendas.dto.despesa.DespesaDTO;
import org.systemrendas.dto.despesa.DespesaNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.DespesaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class DespesaService {

    @Inject
    DespesaRepository repo;

    @Inject
    TipoDespesaService tipoDespesaService;

    @Inject
    PatrimonioService patrimonioService;

    public Despesa find(final UUID id) {
        final Optional<Despesa> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + DespesaService.class.getName()));
    }

    public DespesaDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<DespesaDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Despesa> listPanache = repo.listAllPage(pegeable);
        List<DespesaDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
    }

    @Transactional
    public UUID insert(final Despesa obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Despesa obj) {
        final Despesa newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException(
                    "Não é possível excluir, a entidade " + Despesa.class.getName() + " contem outros relacionamentos",
                    null, DespesaService.class.getName());

        }
    }

    public List<DespesaDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private DespesaDTO toDTO(Despesa entidade) {
        DespesaDTO newObj = new DespesaDTO();
        newObj.setId(entidade.getId());
        newObj.setDescricao(entidade.getDescricao());
        newObj.setValor(entidade.getValor());
        newObj.setVencimento(entidade.getVencimento());
        newObj.setDataInicio(entidade.getDataInicio());
        newObj.setDataFim(entidade.getDataFim());
        newObj.setObs(entidade.getObs());
        newObj.setTipoDespesaId(entidade.getTipoDespesa().getId());
        newObj.setPatrimonioId(entidade.getPatrimonio().getId());
        return newObj;
    }

    public Despesa fromDTO(final DespesaNewDTO objDto) {
        Despesa entidade = new Despesa();
        entidade.setDescricao(objDto.getDescricao());
        entidade.setValor(objDto.getValor());
        entidade.setVencimento(objDto.getVencimento());
        entidade.setDataInicio(objDto.getDataInicio());
        entidade.setDataFim(objDto.getDataFim());
        entidade.setObs(objDto.getObs());
        entidade.setTipoDespesa(tipoDespesaService.find(objDto.getTipoDespesaId()));
        entidade.setPatrimonio(patrimonioService.find(objDto.getPatrimonioId()));
        return entidade;
    }

    private void updateData(final Despesa newObj, final Despesa obj) {
        newObj.setDescricao(obj.getDescricao());
        newObj.setValor(obj.getValor());
        newObj.setVencimento(obj.getVencimento());
        newObj.setDataInicio(obj.getDataInicio());
        newObj.setDataFim(obj.getDataFim());
        newObj.setObs(obj.getObs());
        newObj.setTipoDespesa(obj.getTipoDespesa());
        newObj.setPatrimonio(obj.getPatrimonio());
    }

}