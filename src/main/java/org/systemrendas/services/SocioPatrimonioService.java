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
import org.systemrendas.domain.SocioPatrimonio;
import org.systemrendas.dto.sociopatrimonio.SocioPatrimonioDTO;
import org.systemrendas.repositories.SocioPatrimonioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class SocioPatrimonioService {

    @Inject
    SocioPatrimonioRepository repo;

    @Inject
    PatrimonioService patrimonioService;

    private SocioPatrimonio find(final UUID id) {
        final Optional<SocioPatrimonio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + SocioPatrimonioService.class.getName()));
    }

    public SocioPatrimonioDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public PanacheQuery<SocioPatrimonio> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final SocioPatrimonio obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final SocioPatrimonio obj) {
        final SocioPatrimonio newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + SocioPatrimonio.class.getName()
                    + " contem outros relacionamentos", null, SocioPatrimonioService.class.getName());

        }
    }

    public List<SocioPatrimonioDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private SocioPatrimonioDTO toDTO(SocioPatrimonio entidade) {
        SocioPatrimonioDTO newObj = new SocioPatrimonioDTO();
        newObj.setId(entidade.getId());
        newObj.setPorcentagem(entidade.getPorcentagem());
        newObj.setProprietario(entidade.getProprietario());
        newObj.setPatrimonioId(entidade.getPatrimonio().getId());
        return newObj;
    }

    public SocioPatrimonio fromDTO(final SocioPatrimonioDTO objDto) {
        SocioPatrimonio entidade = new SocioPatrimonio();
        entidade.setPorcentagem(objDto.getPorcentagem());
        entidade.setProprietario(objDto.getProprietario());
        entidade.setPatrimonio(patrimonioService.find(objDto.getPatrimonioId()));
        return entidade;
    }

    private void updateData(final SocioPatrimonio newObj, final SocioPatrimonio obj) {
        newObj.setPorcentagem(obj.getPorcentagem());
        newObj.setProprietario(obj.getProprietario());
        newObj.setPatrimonio(obj.getPatrimonio());
    }

}