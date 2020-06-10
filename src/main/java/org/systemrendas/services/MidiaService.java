package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Midia;
import org.systemrendas.dto.midia.MidiaInsertDTO;
import org.systemrendas.dto.midia.MidiaUpdateDTO;
import org.systemrendas.repositories.MidiaRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class MidiaService {

    @Inject
    MidiaRepository repo;

    private Midia find(final UUID id) {
        final Optional<Midia> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + MidiaService.class.getName()));
    }

    public Midia findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Midia> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
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

    public List<Midia> listAll() {
        return repo.listAll();
    }

    private void updateData(final Midia newObj, final Midia obj) {
        newObj.setId(null);
    }

    public Midia fromDTO(final MidiaInsertDTO objDto) {
        Midia midia = new Midia();
        return midia;
    }

    public Midia fromDTO(MidiaUpdateDTO dto) {
        return null;
    }

}