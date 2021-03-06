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
import org.systemrendas.domain.Movimentacao;
import org.systemrendas.dto.movimentacao.MovimentacaoDTO;
import org.systemrendas.dto.movimentacao.MovimentacaoNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.MovimentacaoRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class MovimentacaoService {

    @Inject
    MovimentacaoRepository repo;

    @Inject
    PatrimonioService patrimonioService;

    private Movimentacao find(final UUID id) {
        final Optional<Movimentacao> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + MovimentacaoService.class.getName()));
    }

    public MovimentacaoDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<MovimentacaoDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Movimentacao> listPanache = repo.listAllPage(pegeable);
        List<MovimentacaoDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
    }

    @Transactional
    public UUID insert(final Movimentacao obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Movimentacao obj) {
        final Movimentacao newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Movimentacao.class.getName()
                    + " contem outros relacionamentos", null, MovimentacaoService.class.getName());

        }
    }

    public List<MovimentacaoDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MovimentacaoDTO toDTO(Movimentacao entidade) {
        MovimentacaoDTO newObj = new MovimentacaoDTO();
        newObj.setId(entidade.getId());
        newObj.setData(entidade.getData());
        newObj.setObs(entidade.getObs());
        newObj.setValor(entidade.getValor());
        newObj.setPatrimonioIn(entidade.getPatrimonioIn().getId());
        newObj.setPatrimonioOut(entidade.getPatrimonioOut().getId());
        return newObj;
    }

    public Movimentacao fromDTO(final MovimentacaoNewDTO objDto) {
        Movimentacao entidade = new Movimentacao();
        entidade.setData(objDto.getData());
        entidade.setObs(objDto.getObs());
        entidade.setValor(objDto.getValor());
        entidade.setPatrimonioIn(patrimonioService.find(objDto.getPatrimonioIn()));
        entidade.setPatrimonioOut(patrimonioService.find(objDto.getPatrimonioOut()));
        return entidade;
    }

    private void updateData(final Movimentacao newObj, final Movimentacao obj) {
        newObj.setData(obj.getData());
        newObj.setObs(obj.getObs());
        newObj.setValor(obj.getValor());
        newObj.setPatrimonioIn(obj.getPatrimonioIn());
        newObj.setPatrimonioOut(obj.getPatrimonioOut());
    }

}