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
import org.systemrendas.domain.Contato;
import org.systemrendas.dto.contato.ContatoDTO;
import org.systemrendas.repositories.ContatoRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class ContatoService {

    @Inject
    ContatoRepository repo;

    @Inject
    SocioService socioService;

    @Inject
    PagadorService pagadorService;

    private Contato find(final UUID id) {
        final Optional<Contato> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + ContatoService.class.getName()));
    }

    public ContatoDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public PanacheQuery<Contato> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);

        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Contato obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Contato obj) {
        final Contato newObj = find(obj.getId());
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
                    "Não é possível excluir, a entidade " + Contato.class.getName() + " contem outros relacionamentos",
                    null, ContatoService.class.getName());

        }
    }

    public List<ContatoDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ContatoDTO toDTO(Contato entidade) {
        ContatoDTO newObj = new ContatoDTO();
        newObj.setId(entidade.getId());
        newObj.setFone(entidade.getFone());
        newObj.setWhatsapp(entidade.getWhatsapp());
        newObj.setEmail(entidade.getEmail());
        newObj.setPagadorId(entidade.getPagador().getId());
        newObj.setSocioId(entidade.getSocio().getId());
        return newObj;
    }

    public Contato fromDTO(final ContatoDTO objDto) {
        Contato entidade = new Contato();
        entidade.setFone(objDto.getFone());
        entidade.setWhatsapp(objDto.getWhatsapp());
        entidade.setEmail(objDto.getEmail());
        entidade.setPagador(pagadorService.find(objDto.getPagadorId()));
        entidade.setSocio(socioService.find(objDto.getSocioId()));
        return entidade;
    }

    private void updateData(final Contato newObj, final Contato obj) {
        newObj.setWhatsapp(obj.getWhatsapp());
        newObj.setEmail(obj.getEmail());
        newObj.setFone(obj.getFone());
        newObj.setPagador(pagadorService.find(newObj.getPagador().getId()));
        newObj.setSocio(socioService.find(newObj.getSocio().getId()));
    }

}