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
import org.systemrendas.domain.Socio;
import org.systemrendas.dto.socio.SocioDTO;
import org.systemrendas.dto.socio.SocioNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.SocioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class SocioService {

    @Inject
    SocioRepository repo;

    public Socio find(final UUID id) {
        final Optional<Socio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + SocioService.class.getName()));
    }

    public SocioDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<SocioDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Socio> listPanache = repo.listAllPage(pegeable);
        List<SocioDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
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

    public List<SocioDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public SocioDTO toDTO(Socio entidade) {
        SocioDTO newObj = new SocioDTO();
        newObj.setId(entidade.getId());
        newObj.setCpf(entidade.getCpf());
        newObj.setEstadoCivil(entidade.getEstadoCivil());
        newObj.setNacionalidade(entidade.getNacionalidade());
        newObj.setNome(entidade.getNome());
        newObj.setProfissao(entidade.getProfissao());
        newObj.setRg(entidade.getRg());
        return newObj;
    }

    public Socio fromDTO(final SocioNewDTO objDto) {
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