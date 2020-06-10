package org.systemrendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.systemrendas.domain.Pagador;
import org.systemrendas.dto.pagador.PagadorInsertDTO;
import org.systemrendas.dto.pagador.PagadorUpdateDTO;
import org.systemrendas.repositories.PagadorRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class PagadorService {

    @Inject
    PagadorRepository repo;

    private Pagador find(final UUID id) {
        final Optional<Pagador> obj = repo.findByIdOptional(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(null,
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PagadorService.class.getName()));
    }

    public Pagador findById(final UUID id) {
        return find(id);
    }

    public PanacheQuery<Pagador> findAllPage(Integer page, Integer size) {
        Page pegeable = Page.of(page, size);
        return repo.listAllPage(pegeable);
    }

    @Transactional
    public UUID insert(final Pagador obj) {
        obj.setId(null);
        repo.persist(obj);
        return obj.getId();
    }

    @Transactional
    public void update(final Pagador obj) {
        final Pagador newObj = find(obj.getId());
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
                    "Não é possível excluir, a entidade " + Pagador.class.getName() + " contem outros relacionamentos",
                    null, PagadorService.class.getName());

        }
    }

    public List<Pagador> listAll() {
        return repo.listAll();
    }

    public Pagador fromDTO(final PagadorInsertDTO objDto) {
        Pagador pagador = new Pagador();

        pagador.setCnpj(objDto.getCnpj());
        pagador.setCpf(objDto.getCpf());
        pagador.setEndereco(objDto.getEndereco());
        pagador.setEstadoCivil(objDto.getEstadoCivil());
        pagador.setNacionalidade(objDto.getNacionalidade());
        pagador.setNascimento(objDto.getNascimento());
        pagador.setNome(objDto.getNome());
        pagador.setProfissao(objDto.getProfissao());
        pagador.setRg(objDto.getRg());

        return pagador;
    }

    public Pagador fromDTO(final PagadorUpdateDTO objDto) {
        Pagador pagador = new Pagador();
        pagador.setCnpj(objDto.getCnpj());
        pagador.setCpf(objDto.getCpf());
        pagador.setEndereco(objDto.getEndereco());
        pagador.setEstadoCivil(objDto.getEstadoCivil());
        pagador.setNacionalidade(objDto.getNacionalidade());
        pagador.setNascimento(objDto.getNascimento());
        pagador.setNome(objDto.getNome());
        pagador.setProfissao(objDto.getProfissao());
        pagador.setRg(objDto.getRg());
        return pagador;
    }

    private void updateData(final Pagador newObj, final Pagador obj) {
        newObj.setCnpj(obj.getCnpj());
        newObj.setCpf(obj.getCpf());
        newObj.setEndereco(obj.getEndereco());
        newObj.setEstadoCivil(obj.getEstadoCivil());
        newObj.setNacionalidade(obj.getNacionalidade());
        newObj.setNascimento(obj.getNascimento());
        newObj.setNome(obj.getNome());
        newObj.setProfissao(obj.getProfissao());
        newObj.setRg(obj.getRg());
        new Pagador();
    }

}