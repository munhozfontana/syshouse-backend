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
import org.systemrendas.domain.Patrimonio;
import org.systemrendas.dto.patrimonio.PatrimonioDTO;
import org.systemrendas.dto.patrimonio.PatrimonioNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.repositories.PatrimonioRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PatrimonioService {

    @Inject
    PatrimonioRepository repo;

    @Inject
    LocalizacaoService localizacaoService;

    @Inject
    TipoPatrimonioService tipoPatrimonioService;

    public Patrimonio find(final UUID id) {
        final Optional<Patrimonio> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PatrimonioService.class.getName()));
    }

    public PatrimonioDTO findById(final UUID id) {
        return toDTO(find(id));
    }

    public Pagination<PatrimonioDTO> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        PanacheQuery<Patrimonio> listPanache = repo.listAllPage(pegeable);
        List<PatrimonioDTO> list = listPanache.list().stream().map(this::toDTO).collect(Collectors.toList());
        return new Pagination<>(list, listPanache.page().index, listPanache.page().size, listPanache.count());
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

    public List<PatrimonioDTO> listAll() {
        return repo.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PatrimonioDTO toDTO(Patrimonio entidade) {
        PatrimonioDTO newObj = new PatrimonioDTO();
        newObj.setId(entidade.getId());
        newObj.setDataFim(entidade.getDataFim());
        newObj.setDataInicio(entidade.getDataInicio());
        newObj.setNome(entidade.getNome());
        newObj.setLocalizacaoId(entidade.getLocalizacao().getId());
        newObj.setTipoPatrimonioId(entidade.getTipoPatrimonio().getId());
        newObj.setValor(entidade.getValor());
        return newObj;
    }

    public Patrimonio fromDTO(final PatrimonioNewDTO objDto) {
        Patrimonio entidade = new Patrimonio();
        entidade.setDataFim(objDto.getDataFim());
        entidade.setDataInicio(objDto.getDataInicio());
        entidade.setNome(objDto.getNome());
        entidade.setLocalizacao(localizacaoService.find(objDto.getLocalizacaoId()));
        entidade.setTipoPatrimonio(tipoPatrimonioService.find(objDto.getTipoPatrimonioId()));
        entidade.setValor(objDto.getValor());
        return entidade;
    }

    private void updateData(final Patrimonio newObj, final Patrimonio obj) {
        newObj.setDataFim(obj.getDataFim());
        newObj.setDataInicio(obj.getDataInicio());
        newObj.setNome(obj.getNome());
        newObj.setLocalizacao(localizacaoService.find(obj.getLocalizacao().getId()));
        newObj.setTipoPatrimonio(tipoPatrimonioService.find(obj.getTipoPatrimonio().getId()));
        newObj.setValor(obj.getValor());
    }

}