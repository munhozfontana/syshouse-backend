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
import org.systemrendas.domain.Municipio;
import org.systemrendas.dto.municipio.MunicipioDTO;
import org.systemrendas.dto.municipio.MunicipioNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.MunicipioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class MunicipioService {

    @Inject
    MunicipioRepository repo;

    public Municipio find(final UUID id) {
        final Optional<Municipio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + MunicipioService.class.getName()));
    }

    public MunicipioDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<MunicipioDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Municipio> listPanache = repo.listAllPage(pegeable);
        List<MunicipioDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
    }

    @Transactional
    public UUID insert(final Municipio obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Municipio obj) {
        final Municipio newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Municipio.class.getName()
                    + " contem outros relacionamentos", null, MunicipioService.class.getName());

        }
    }

    public List<MunicipioDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MunicipioDTO toDTO(Municipio entidade) {
        MunicipioDTO newObj = new MunicipioDTO();
        newObj.setId(entidade.getId());
        newObj.setIbge(entidade.getIbge());
        newObj.setNome(entidade.getNome());
        newObj.setPais(entidade.getNome());
        newObj.setPopulacao(entidade.getPopulacao());
        newObj.setUf(entidade.getUf());
        return newObj;
    }

    public Municipio fromDTO(final MunicipioNewDTO objDto) {
        Municipio municipio = new Municipio();
        municipio.setIbge(objDto.getIbge());
        municipio.setNome(objDto.getNome());
        municipio.setPais(objDto.getNome());
        municipio.setPopulacao(objDto.getPopulacao());
        municipio.setUf(objDto.getUf());
        return municipio;
    }

    private void updateData(final Municipio newObj, final Municipio obj) {
        newObj.setIbge(obj.getIbge());
        newObj.setNome(obj.getNome());
        newObj.setPais(obj.getNome());
        newObj.setPopulacao(obj.getPopulacao());
        newObj.setUf(obj.getUf());
    }
}