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
import org.systemrendas.domain.TipoRenda;
import org.systemrendas.dto.tiporenda.TipoRendaInsertDTO;
import org.systemrendas.dto.tiporenda.TipoRendaUpdateDTO;
import org.systemrendas.services.TipoRendaService;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("tiporenda")
@Tag(name = "Tipo de Renda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoRendaResource {

    @Inject
    TipoRendaService service;

    @GET
    @Path("{id}")
    @Operation(summary = "TipoRenda pelo ID", description = "Atravez do ID da entidade TipoRenda é retornado apenas um objeto do mesmo")
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
    @Operation(summary = "Lista de TipoRenda", description = "É retornado uma lista de objetos TipoRenda")
    public Response listAll() {
        try {
            return Response.ok(service.listAll()).build();
        } catch (Exception e) {
            return Response.status(500, Status.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
        }

    }

    @GET
    @Path("page")
    @Operation(summary = "Lista de TipoRenda pagináveis", description = "É retornado uma lista de objetos TipoRenda com paginação")
    public Response listAllPage(@QueryParam Integer page, @QueryParam Integer size) {
        PanacheQuery<TipoRenda> pages = service.findAllPage(page, size);
        return Response.ok(pages.list()).header("pages", pages.pageCount()).header("totalElements", pages.count())
                .build();
    }

    @POST
    @Operation(summary = "Insere TipoRenda", description = "Insere um novo objeto TipoRenda e retornado URI para localizar o objeto")
    public Response insert(final @RequestBody @Valid TipoRendaInsertDTO dto) throws URISyntaxException {
        TipoRenda tiporenda = service.fromDTO(dto);
        UUID id = service.insert(tiporenda);
        return Response.created(new URI("tiporenda/" + id.toString())).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualiza TipoRenda", description = "Atualiza o objeto TipoRenda")
    public Response update(@PathParam("id") final UUID id, final @Valid TipoRendaUpdateDTO dto) {
        TipoRenda obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return Response.ok(obj).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Remove TipoRenda", description = "Remove o objeto TipoRenda passando o ID do mesmo")
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