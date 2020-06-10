package org.systemrendas.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.hibernate.ObjectNotFoundException;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.systemrendas.domain.Pagador;
import org.systemrendas.dto.pagador.PagadorInsertDTO;
import org.systemrendas.dto.pagador.PagadorUpdateDTO;
import org.systemrendas.services.PagadorService;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("pagador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pagador")
public class PagadorResource {

    @Inject
    PagadorService service;

    @GET
    @Path("{id}")
    @Operation(summary = "Pagador pelo ID", description = "Atravez do ID da entidade Pagador é retornado apenas um objeto do mesmo")
    public Response find(@PathParam("id") final UUID id) {
        try {
            return Response.ok(service.findById(id)).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(404, e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(500, Status.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
        }
    }

    @GET
    @Operation(summary = "Lista de Pagador", description = "É retornado uma lista de objetos Pagador")
    public Response listAll() {
        try {
            return Response.ok(service.listAll()).build();
        } catch (Exception e) {
            return Response.status(500, Status.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
        }

    }

    @GET
    @Path("page")
    @Operation(summary = "Lista de Pagador pagináveis", description = "É retornado uma lista de objetos Pagador com paginação")
    public Response listAllPage(@QueryParam Integer page, @QueryParam Integer size) {
        PanacheQuery<Pagador> pages = service.findAllPage(page, size);
        return Response.ok(pages.list()).header("pages", pages.pageCount()).header("totalElements", pages.count())
                .build();
    }

    @POST
    @Operation(summary = "Insere Pagador", description = "Insere um novo objeto Pagador e retornado URI para localizar o objeto")
    public Response insert(final @RequestBody @Valid PagadorInsertDTO dto) throws URISyntaxException {
        Pagador pagador = service.fromDTO(dto);
        UUID id = service.insert(pagador);
        return Response.created(new URI("pagador/" + id.toString())).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualiza Pagador", description = "Atualiza o objeto Pagador")
    public Response update(@PathParam("id") final UUID id, final @Valid PagadorUpdateDTO dto) {
        Pagador obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return Response.ok(obj).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Remove Pagador", description = "Remove o objeto Pagador passando o ID do mesmo")
    public Response delete(@PathParam("id") final UUID id) {
        try {
            service.delete(id);
            return Response.noContent().build();
        } catch (ObjectNotFoundException e) {
            return Response.status(404, e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(500, Status.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
        }
    }

}