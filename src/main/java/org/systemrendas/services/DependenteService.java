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
import org.systemrendas.domain.Dependente;
import org.systemrendas.dto.dependente.DependenteDTO;
import org.systemrendas.dto.dependente.DependenteNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.DependenteRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class DependenteService {

    @Inject
    DependenteRepository repo;

    @Inject
    PagadorService pagadorService;

    private Dependente find(final UUID id) {
        final Optional<Dependente> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + DependenteService.class.getName()));
    }

    public DependenteDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<DependenteDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Dependente> listPanache = repo.listAllPage(pegeable);
        List<DependenteDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
    }

    @Transactional
    public UUID insert(final Dependente obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Dependente obj) {
        final Dependente newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Dependente.class.getName()
                    + " contem outros relacionamentos", null, DependenteService.class.getName());

        }
    }

    public List<DependenteDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private DependenteDTO toDTO(Dependente entidade) {
        DependenteDTO newObj = new DependenteDTO();
        newObj.setId(entidade.getId());
        newObj.setNome(entidade.getNome());
        newObj.setPagadorId(entidade.getPagador().getId());
        return newObj;
    }

    public Dependente fromDTO(final DependenteNewDTO objDto) {
        Dependente entidade = new Dependente();
        entidade.setNome(objDto.getNome());
        entidade.setPagador(pagadorService.find(objDto.getPagadorId()));
        return entidade;
    }

    private void updateData(final Dependente newObj, final Dependente obj) {
        newObj.setNome(obj.getNome());
        newObj.setPagador(obj.getPagador());
    }

}