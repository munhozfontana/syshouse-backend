package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Dependente;
import org.systemrendas.dto.dependente.DependenteInsertDTO;
import org.systemrendas.dto.dependente.DependenteUpdateDTO;
import org.systemrendas.repositories.DependenteRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class DependenteService {

    @Inject
    DependenteRepository repo;

    private Dependente find(final UUID id) {
        final Optional<Dependente> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + DependenteService.class.getName()));
    }

    public Dependente findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Dependente> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
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

    public List<Dependente> listAll() {
        return repo.listAll();
    }

    private void updateData(final Dependente newObj, final Dependente obj) {
        newObj.setId(null);
    }

    public Dependente fromDTO(final DependenteInsertDTO objDto) {
        Dependente dependente = new Dependente();
        return dependente;
    }

    public Dependente fromDTO(DependenteUpdateDTO dto) {
        return null;
    }

}