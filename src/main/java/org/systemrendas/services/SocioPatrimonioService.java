package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.SocioPatrimonio;
import org.systemrendas.dto.sociopatrimonio.SocioPatrimonioInsertDTO;
import org.systemrendas.dto.sociopatrimonio.SocioPatrimonioUpdateDTO;
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

    public SocioPatrimonio findById(final UUID id) {
        return find(id);
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

    public List<SocioPatrimonio> listAll() {
        return repo.listAll();
    }

    public SocioPatrimonio fromDTO(final SocioPatrimonioInsertDTO objDto) {
        SocioPatrimonio entidade = new SocioPatrimonio();
        entidade.setPorcentagem(objDto.getPorcentagem());
        entidade.setProprietario(objDto.getProprietario());
        entidade.setPatrimonio(patrimonioService.findById(objDto.getPatrimonioId()));
        return entidade;
    }

    public SocioPatrimonio fromDTO(SocioPatrimonioUpdateDTO objDto) {
        SocioPatrimonio entidade = new SocioPatrimonio();
        entidade.setPorcentagem(objDto.getPorcentagem());
        entidade.setProprietario(objDto.getProprietario());
        entidade.setPatrimonio(patrimonioService.findById(objDto.getPatrimonioId()));
        return entidade;
    }

    private void updateData(final SocioPatrimonio newObj, final SocioPatrimonio obj) {
        newObj.setPorcentagem(obj.getPorcentagem());
        newObj.setProprietario(obj.getProprietario());
        newObj.setPatrimonio(obj.getPatrimonio());
    }

}