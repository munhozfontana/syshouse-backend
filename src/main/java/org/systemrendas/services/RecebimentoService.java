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
import org.systemrendas.domain.Recebimento;
import org.systemrendas.dto.recebimento.RecebimentoDTO;
import org.systemrendas.dto.recebimento.RecebimentoNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.RecebimentoRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class RecebimentoService {

    @Inject
    RecebimentoRepository repo;

    @Inject
    private RendaService rendaService;

    public Recebimento find(final UUID id) {
        final Optional<Recebimento> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + RecebimentoService.class.getName()));
    }

    public RecebimentoDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<RecebimentoDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Recebimento> listPanache = repo.listAllPage(pegeable);
        List<RecebimentoDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
    }

    @Transactional
    public UUID insert(final Recebimento obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Recebimento obj) {
        final Recebimento newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Recebimento.class.getName()
                    + " contem outros relacionamentos", null, RecebimentoService.class.getName());

        }
    }

    public List<RecebimentoDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RecebimentoDTO toDTO(Recebimento entidade) {
        RecebimentoDTO newObj = new RecebimentoDTO();
        newObj.setId(entidade.getId());
        newObj.setDataRecebimento(entidade.getDataRecebimento());
        newObj.setObs(entidade.getObs());
        newObj.setValor(entidade.getValor());
        newObj.setRendaId(entidade.getRenda().getId());
        return newObj;
    }

    public Recebimento fromDTO(final RecebimentoNewDTO objDto) {
        Recebimento entidade = new Recebimento();
        entidade.setDataRecebimento(objDto.getDataRecebimento());
        entidade.setObs(objDto.getObs());
        entidade.setValor(objDto.getValor());
        entidade.setRenda(rendaService.find(objDto.getRendaId()));
        return entidade;
    }

    private void updateData(final Recebimento newObj, final Recebimento obj) {
        newObj.setDataRecebimento(obj.getDataRecebimento());
        newObj.setObs(obj.getObs());
        newObj.setValor(obj.getValor());
        newObj.setRenda(obj.getRenda());
    }

}