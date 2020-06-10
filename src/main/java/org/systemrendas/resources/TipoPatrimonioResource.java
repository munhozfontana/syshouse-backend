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
import org.systemrendas.domain.TipoPatrimonio;
import org.systemrendas.dto.tipopatrimonio.TipoPatrimonioInsertDTO;
import org.systemrendas.dto.tipopatrimonio.TipoPatrimonioUpdateDTO;
import org.systemrendas.services.TipoPatrimonioService;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("tipopatrimonio")
@Tag(name = "Tipo de Patrimonio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoPatrimonioResource {

    @Inject
    TipoPatrimonioService service;

    @GET
    @Path("{id}")
    @Operation(summary = "TipoPatrimonio pelo ID", description = "Atravez do ID da entidade TipoPatrimonio é retornado apenas um objeto do mesmo")
    public Response find(@PathParam("id") final UUID id) {
        try {
            return Response.ok(service.findById(id)).build();
        } catch (ObjectNotFoundException e) {
            return Response.status(Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Operation(summary = "Lista de TipoPatrimonio", description = "É retornado uma lista de objetos TipoPatrimonio")
    public Response listAll() {
        try {
            return Response.ok(service.listAll()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("page")
    @Operation(summary = "Lista de TipoPatrimonio pagináveis", description = "É retornado uma lista de objetos TipoPatrimonio com paginação")
    public Response listAllPage(@QueryParam Integer page, @QueryParam Integer size) {
        PanacheQuery<TipoPatrimonio> pages = service.findAllPage(page, size);
        return Response.ok(pages.list()).header("pages", pages.pageCount()).header("totalElements", pages.count())
                .build();
    }

    @POST
    @Operation(summary = "Insere TipoPatrimonio", description = "Insere um novo objeto TipoPatrimonio e retornado URI para localizar o objeto")
    public Response insert(final @RequestBody @Valid TipoPatrimonioInsertDTO dto) throws URISyntaxException {
        TipoPatrimonio tipopatrimonio = service.fromDTO(dto);
        UUID id = service.insert(tipopatrimonio);
        return Response.created(new URI("tipopatrimonio/" + id.toString())).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualiza TipoPatrimonio", description = "Atualiza o objeto TipoPatrimonio")
    public Response update(@PathParam("id") final UUID id, final @Valid TipoPatrimonioUpdateDTO dto) {
        TipoPatrimonio obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return Response.ok(obj).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Remove TipoPatrimonio", description = "Remove o objeto TipoPatrimonio passando o ID do mesmo")
    public Response delete(@PathParam("id") final UUID id) {
        try {
            service.delete(id);
            return Response.noContent().build();
        } catch (ObjectNotFoundException e) {
            return Response.status(Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}