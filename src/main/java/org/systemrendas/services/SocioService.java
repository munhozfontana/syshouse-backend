package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Socio;
import org.systemrendas.dto.socio.SocioInsertDTO;
import org.systemrendas.dto.socio.SocioUpdateDTO;
import org.systemrendas.repositories.SocioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class SocioService {

    @Inject
    SocioRepository repo;

    private Socio find(final UUID id) {
        final Optional<Socio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + SocioService.class.getName()));
    }

    public Socio findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Socio> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Socio obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Socio obj) {
        final Socio newObj = find(obj.getId());
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
                    "Não é possível excluir, a entidade " + Socio.class.getName() + " contem outros relacionamentos",
                    null, SocioService.class.getName());

        }
    }

    public List<Socio> listAll() {
        return repo.listAll();
    }

    public Socio fromDTO(final SocioInsertDTO objDto) {
        Socio entidade = new Socio();
        entidade.setCpf(objDto.getCpf());
        entidade.setEstadoCivil(objDto.getEstadoCivil());
        entidade.setNacionalidade(objDto.getNacionalidade());
        entidade.setNome(objDto.getNome());
        entidade.setProfissao(objDto.getProfissao());
        entidade.setRg(objDto.getRg());
        return entidade;
    }

    public Socio fromDTO(SocioUpdateDTO objDto) {
        Socio entidade = new Socio();
        entidade.setCpf(objDto.getCpf());
        entidade.setEstadoCivil(objDto.getEstadoCivil());
        entidade.setNacionalidade(objDto.getNacionalidade());
        entidade.setNome(objDto.getNome());
        entidade.setProfissao(objDto.getProfissao());
        entidade.setRg(objDto.getRg());
        return entidade;
    }

    private void updateData(final Socio newObj, final Socio obj) {
        newObj.setCpf(obj.getCpf());
        newObj.setEstadoCivil(obj.getEstadoCivil());
        newObj.setNacionalidade(obj.getNacionalidade());
        newObj.setNome(obj.getNome());
        newObj.setProfissao(obj.getProfissao());
        newObj.setRg(obj.getRg());
    }

}