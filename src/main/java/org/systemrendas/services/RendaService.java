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
import org.systemrendas.domain.Renda;
import org.systemrendas.dto.renda.RendaDTO;
import org.systemrendas.dto.renda.RendaNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.RendaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class RendaService {

    @Inject
    RendaRepository repo;

    @Inject
    PagadorService pagadorService;

    @Inject
    PatrimonioService patrimonioService;

    @Inject
    TipoRendaService tipoRendaService;

    public Renda find(final UUID id) {
        final Optional<Renda> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + RendaService.class.getName()));
    }

    public RendaDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<RendaDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Renda> listPanache = repo.listAllPage(pegeable);
        List<RendaDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
    }

    @Transactional
    public UUID insert(final Renda obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Renda obj) {
        final Renda newObj = find(obj.getId());
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
                    "Não é possível excluir, a entidade " + Renda.class.getName() + " contem outros relacionamentos",
                    null, RendaService.class.getName());

        }
    }

    public List<RendaDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private RendaDTO toDTO(Renda entidade) {
        RendaDTO newObj = new RendaDTO();
        newObj.setId(entidade.getId());
        newObj.setDataFim(entidade.getDataFim());
        newObj.setDataInicio(entidade.getDataInicio());
        newObj.setDescricao(entidade.getDescricao());
        newObj.setObs(entidade.getObs());
        newObj.setValor(entidade.getValor());
        newObj.setVencimento(entidade.getVencimento());
        newObj.setPagadorId(entidade.getPagador().getId());
        newObj.setTipoRendaId(entidade.getTipoRenda().getId());
        newObj.setPatrimonioId(entidade.getPatrimonio().getId());
        return newObj;
    }

    public Renda fromDTO(final RendaNewDTO objDto) {
        Renda entidade = new Renda();
        entidade.setDataFim(objDto.getDataFim());
        entidade.setDataInicio(objDto.getDataInicio());
        entidade.setDescricao(objDto.getDescricao());
        entidade.setObs(objDto.getObs());
        entidade.setValor(objDto.getValor());
        entidade.setVencimento(objDto.getVencimento());
        entidade.setPagador(pagadorService.find(objDto.getPagadorId()));
        entidade.setTipoRenda(tipoRendaService.find(objDto.getTipoRendaId()));
        entidade.setPatrimonio(patrimonioService.find(objDto.getPatrimonioId()));
        return entidade;
    }

    private void updateData(final Renda newObj, final Renda obj) {
        newObj.setDataFim(obj.getDataFim());
        newObj.setDataInicio(obj.getDataInicio());
        newObj.setDescricao(obj.getDescricao());
        newObj.setObs(obj.getObs());
        newObj.setValor(obj.getValor());
        newObj.setVencimento(obj.getVencimento());
        newObj.setPagador(obj.getPagador());
        newObj.setTipoRenda(obj.getTipoRenda());
        newObj.setPatrimonio(obj.getPatrimonio());
    }

}