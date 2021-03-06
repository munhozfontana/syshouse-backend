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
import org.systemrendas.domain.Midia;
import org.systemrendas.dto.midia.MidiaDTO;
import org.systemrendas.dto.midia.MidiaNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.MidiaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class MidiaService {

    @Inject
    MidiaRepository repo;

    @Inject
    PatrimonioService patrimonioService;

    private Midia find(final UUID id) {
        final Optional<Midia> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + MidiaService.class.getName()));
    }

    public MidiaDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<MidiaDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Midia> listPanache = repo.listAllPage(pegeable);
        List<MidiaDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
    }

    @Transactional
    public UUID insert(final Midia obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Midia obj) {
        final Midia newObj = find(obj.getId());
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
                    "Não é possível excluir, a entidade " + Midia.class.getName() + " contem outros relacionamentos",
                    null, MidiaService.class.getName());

        }
    }

    public List<MidiaDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MidiaDTO toDTO(Midia entidade) {
        MidiaDTO newObj = new MidiaDTO();
        newObj.setId(entidade.getId());
        newObj.setNome(entidade.getNome());
        newObj.setCaminho(entidade.getCaminho());
        newObj.setObs(entidade.getObs());
        newObj.setTipo(entidade.getTipo());
        newObj.setPatrimonioId(entidade.getPatrimonio().getId());
        return newObj;
    }

    public Midia fromDTO(final MidiaNewDTO objDto) {
        Midia entidade = new Midia();
        entidade.setCaminho(objDto.getCaminho());
        entidade.setNome(objDto.getNome());
        entidade.setObs(objDto.getObs());
        entidade.setPatrimonio(patrimonioService.find(objDto.getPatrimonioId()));
        entidade.setTipo(objDto.getTipo());
        return entidade;
    }

    private void updateData(final Midia newObj, final Midia obj) {
        newObj.setNome(obj.getNome());
        newObj.setCaminho(obj.getCaminho());
        newObj.setObs(obj.getObs());
        newObj.setTipo(obj.getTipo());
        newObj.setPatrimonio(obj.getPatrimonio());
    }

}