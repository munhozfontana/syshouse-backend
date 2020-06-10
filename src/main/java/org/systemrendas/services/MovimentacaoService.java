package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Movimentacao;
import org.systemrendas.dto.movimentacao.MovimentacaoInsertDTO;
import org.systemrendas.dto.movimentacao.MovimentacaoUpdateDTO;
import org.systemrendas.repositories.MovimentacaoRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class MovimentacaoService {

    @Inject
    MovimentacaoRepository repo;

    private Movimentacao find(final UUID id) {
        final Optional<Movimentacao> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + MovimentacaoService.class.getName()));
    }

    public Movimentacao findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Movimentacao> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
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

    public List<Movimentacao> listAll() {
        return repo.listAll();
    }

    private void updateData(final Movimentacao newObj, final Movimentacao obj) {
        newObj.setId(null);
    }

    public Movimentacao fromDTO(final MovimentacaoInsertDTO objDto) {
        Movimentacao movimentacao = new Movimentacao();
        return movimentacao;
    }

    public Movimentacao fromDTO(MovimentacaoUpdateDTO dto) {
        return null;
    }

}