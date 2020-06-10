package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Renda;
import org.systemrendas.dto.renda.RendaInsertDTO;
import org.systemrendas.dto.renda.RendaUpdateDTO;
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
    RendaService rendaService;

    private Renda find(final UUID id) {
        final Optional<Renda> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + RendaService.class.getName()));
    }

    public Renda findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Renda> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
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

    public List<Renda> listAll() {
        return repo.listAll();
    }

    public Renda fromDTO(final RendaInsertDTO objDto) {
        Renda entidade = new Renda();
        entidade.setDataFim(objDto.getDataFim());
        entidade.setDataInicio(objDto.getDataInicio());
        entidade.setDescricao(objDto.getDescricao());
        entidade.setObs(objDto.getObs());
        entidade.setValor(objDto.getValor());
        entidade.setVencimento(objDto.getVencimento());
        entidade.setPagador(pagadorService.findById(objDto.getPagadorId()));
        entidade.setRenda(rendaService.findById(objDto.getRendaId()));
        entidade.setPatrimonio(patrimonioService.findById(objDto.getPatrimonioId()));
        return entidade;
    }

    public Renda fromDTO(RendaUpdateDTO objDto) {
        Renda entidade = new Renda();
        entidade.setDataFim(objDto.getDataFim());
        entidade.setDataInicio(objDto.getDataInicio());
        entidade.setDescricao(objDto.getDescricao());
        entidade.setObs(objDto.getObs());
        entidade.setValor(objDto.getValor());
        entidade.setVencimento(objDto.getVencimento());
        entidade.setPagador(pagadorService.findById(objDto.getPagadorId()));
        entidade.setRenda(rendaService.findById(objDto.getRendaId()));
        entidade.setPatrimonio(patrimonioService.findById(objDto.getPatrimonioId()));
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
        newObj.setRenda(obj.getRenda());
        newObj.setPatrimonio(obj.getPatrimonio());
    }

}