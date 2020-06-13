package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Patrimonio;
import org.systemrendas.dto.patrimonio.PatrimonioInsertDTO;
import org.systemrendas.dto.patrimonio.PatrimonioUpdateDTO;
import org.systemrendas.repositories.PatrimonioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PatrimonioService {

    @Inject
    PatrimonioRepository repo;

    @Inject
    private LocalizacaoService localizacaoService;

    private Patrimonio find(final UUID id) {
        final Optional<Patrimonio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PatrimonioService.class.getName()));
    }

    public Patrimonio findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Patrimonio> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Patrimonio obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Patrimonio obj) {
        final Patrimonio newObj = find(obj.getId());
        updateData(newObj, obj);
        repo.persist(newObj);
    }

    @Transactional
    public boolean delete(final UUID id) {
        find(id);
        try {
            return repo.deleteById(id);
        } catch (final ObjectDeletedException e) {
            throw new ObjectDeletedException("Não é possível excluir, a entidade " + Patrimonio.class.getName()
                    + " contem outros relacionamentos", null, PatrimonioService.class.getName());

        }
    }

    public List<Patrimonio> listAll() {
        return repo.listAll();
    }

    public Patrimonio fromDTO(final PatrimonioInsertDTO objDto) {
        Patrimonio entidade = new Patrimonio();
        entidade.setDataFim(objDto.getDataFim());
        entidade.setDataInicio(objDto.getDataInicio());
        entidade.setNome(objDto.getNome());
        entidade.setLocalizacao(localizacaoService.findById(objDto.getLocalizacaoId()));
        return entidade;
    }

    public Patrimonio fromDTO(PatrimonioUpdateDTO objDto) {
        Patrimonio entidade = new Patrimonio();
        entidade.setDataFim(objDto.getDataFim());
        entidade.setDataInicio(objDto.getDataInicio());
        entidade.setNome(objDto.getNome());
        entidade.setLocalizacao(localizacaoService.findById(objDto.getLocalizacaoId()));
        return entidade;
    }

    private void updateData(final Patrimonio newObj, final Patrimonio obj) {
        newObj.setDataFim(obj.getDataFim());
        newObj.setDataInicio(obj.getDataInicio());
        newObj.setNome(obj.getNome());
        newObj.setLocalizacao(obj.getLocalizacao());
    }

}