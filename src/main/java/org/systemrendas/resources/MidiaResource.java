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
import org.systemrendas.domain.Midia;
import org.systemrendas.dto.midia.MidiaInsertDTO;
import org.systemrendas.dto.midia.MidiaUpdateDTO;
import org.systemrendas.services.MidiaService;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("midia")
@Tag(name = "Midia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MidiaResource {

    @Inject
    MidiaService service;

    @GET
    @Path("{id}")
    @Operation(summary = "Midia pelo ID", description = "Atravez do ID da entidade Midia é retornado apenas um objeto do mesmo")
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
    @Operation(summary = "Lista de Midia", description = "É retornado uma lista de objetos Midia")
    public Response listAll() {
        try {
            return Response.ok(service.listAll()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("page")
    @Operation(summary = "Lista de Midia pagináveis", description = "É retornado uma lista de objetos Midia com paginação")
    public Response listAllPage(@QueryParam Integer page, @QueryParam Integer size) {
        PanacheQuery<Midia> pages = service.findAllPage(page, size);
        return Response.ok(pages.list()).header("pages", pages.pageCount()).header("totalElements", pages.count())
                .build();
    }

    @POST
    @Operation(summary = "Insere Midia", description = "Insere um novo objeto Midia e retornado URI para localizar o objeto")
    public Response insert(final @RequestBody @Valid MidiaInsertDTO dto) throws URISyntaxException {
        Midia entidade = null;

        try {
            entidade = service.fromDTO(dto);
        } catch (ObjectNotFoundException e) {
            return Response.status(Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }

        UUID id = service.insert(entidade);
        return Response.created(new URI("midia/" + id.toString())).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualiza Midia", description = "Atualiza o objeto Midia")
    public Response update(@PathParam("id") final UUID id, final @Valid MidiaUpdateDTO dto) {
        Midia obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return Response.ok(obj).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Remove Midia", description = "Remove o objeto Midia passando o ID do mesmo")
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