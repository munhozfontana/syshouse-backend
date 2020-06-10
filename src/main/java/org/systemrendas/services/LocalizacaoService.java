package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Localizacao;
import org.systemrendas.dto.localizacao.LocalizacaoInsertDTO;
import org.systemrendas.dto.localizacao.LocalizacaoUpdateDTO;
import org.systemrendas.repositories.LocalizacaoRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class LocalizacaoService {

    @Inject
    LocalizacaoRepository repo;

    private Localizacao find(final UUID id) {
        final Optional<Localizacao> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + LocalizacaoService.class.getName()));
    }

    public Localizacao findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Localizacao> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Localizacao obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Localizacao obj) {
        final Localizacao newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Localizacao.class.getName()
                    + " contem outros relacionamentos", null, LocalizacaoService.class.getName());

        }
    }

    public List<Localizacao> listAll() {
        return repo.listAll();
    }

    private void updateData(final Localizacao newObj, final Localizacao obj) {
        newObj.setId(null);
    }

    public Localizacao fromDTO(final LocalizacaoInsertDTO objDto) {
        Localizacao localizacao = new Localizacao();
        return localizacao;
    }

    public Localizacao fromDTO(LocalizacaoUpdateDTO dto) {
        return null;
    }

}