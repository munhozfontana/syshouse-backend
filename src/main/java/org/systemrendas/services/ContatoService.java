package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Contato;
import org.systemrendas.domain.Pagador;
import org.systemrendas.domain.Socio;
import org.systemrendas.dto.contato.ContatoInsertDTO;
import org.systemrendas.dto.contato.ContatoUpdateDTO;
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

    public Contato findById(final UUID id) {
        return find(id);
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

    public List<Contato> listAll() {
        return repo.listAll();
    }

    public Contato fromDTO(final ContatoInsertDTO objDto) {

        Contato contato = new Contato();
        contato.setFone(objDto.getFone());
        contato.setWhatsapp(objDto.getWhatsapp());
        contato.setEmail(objDto.getEmail());

        contato.setPagador(pagadorService.find(objDto.getPagadorId()));
        contato.setSocio(socioService.find(objDto.getSocioId()));

        return contato;
    }

    public Contato fromDTO(final ContatoUpdateDTO objDto) {
        Contato entidade = new Contato();

        return entidade;
    }

    private void updateData(final Contato newObj, final Contato obj) {
        newObj.setWhatsapp(obj.getWhatsapp());
        newObj.setEmail(obj.getEmail());
        newObj.setFone(obj.getFone());

        Pagador pagador = new Pagador();
        pagador.setId(newObj.getPagador().getId());
        newObj.setPagador(pagador);

        Socio socio = new Socio();
        socio.setId(newObj.getSocio().getId());
        newObj.setSocio(socio);
    }

}