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
import org.systemrendas.domain.Renda;
import org.systemrendas.dto.renda.RendaDTO;
import org.systemrendas.dto.renda.RendaNewDTO;
import org.systemrendas.dto.utils.Pagination;
import org.systemrendas.services.RendaService;

@Path("renda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Renda")
public class RendaResource {

    @Inject
    RendaService service;

    @GET
    @Path("{id}")
    @Operation(summary = "Renda pelo ID", description = "Atravez do ID da entidade Renda é retornado apenas um objeto do mesmo")
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
    @Operation(summary = "Lista de Renda", description = "É retornado uma lista de objetos Renda")
    public Response listAll() {
        try {
            return Response.ok(service.listAll()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("page")
    @Operation(summary = "Lista de Renda pagináveis", description = "É retornado uma lista de objetos Renda com paginação")
    public Response listAllPage(@QueryParam Integer page, @QueryParam Integer size) {
        Pagination<RendaDTO> pages = service.findAllPage(page, size);
        return Response.ok(pages.getList()).header("page", pages.getPage()).header("size", pages.getSize())
                .header("countByPage", pages.getCount()).build();
    }

    @POST
    @Operation(summary = "Insere Renda", description = "Insere um novo objeto Renda e retornado URI para localizar o objeto")
    public Response insert(final @RequestBody @Valid RendaNewDTO dto) throws URISyntaxException {
        Renda entidade = null;

        try {
            entidade = service.fromDTO(dto);
        } catch (ObjectNotFoundException e) {
            return Response.status(Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }

        UUID id = service.insert(entidade);
        entidade.setId(id);
        return Response.created(new URI("renda/" + id.toString())).entity(entidade).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualiza Renda", description = "Atualiza o objeto Renda")
    public Response update(@PathParam("id") final UUID id, final @Valid RendaDTO dto) {
        Renda obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return Response.ok(service.toDTO(obj)).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Remove Renda", description = "Remove o objeto Renda passando o ID do mesmo")
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