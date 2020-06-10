package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Pagamento;
import org.systemrendas.dto.pagamento.PagamentoInsertDTO;
import org.systemrendas.dto.pagamento.PagamentoUpdateDTO;
import org.systemrendas.repositories.PagamentoRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PagamentoService {

    @Inject
    PagamentoRepository repo;

    private Pagamento find(final UUID id) {
        final Optional<Pagamento> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PagamentoService.class.getName()));
    }

    public Pagamento findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Pagamento> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Pagamento obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Pagamento obj) {
        final Pagamento newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Pagamento.class.getName()
                    + " contem outros relacionamentos", null, PagamentoService.class.getName());

        }
    }

    public List<Pagamento> listAll() {
        return repo.listAll();
    }

    private void updateData(final Pagamento newObj, final Pagamento obj) {
        newObj.setId(null);
    }

    public Pagamento fromDTO(final PagamentoInsertDTO objDto) {
        Pagamento pagamento = new Pagamento();
        return pagamento;
    }

    public Pagamento fromDTO(PagamentoUpdateDTO dto) {
        return null;
    }

}