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
import org.systemrendas.domain.Patrimonio;
import org.systemrendas.dto.patrimonio.PatrimonioInsertDTO;
import org.systemrendas.dto.patrimonio.PatrimonioUpdateDTO;
import org.systemrendas.services.PatrimonioService;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("patrimonio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Patrimonio")
public class PatrimonioResource {

    @Inject
    PatrimonioService service;

    @GET
    @Path("{id}")
    @Operation(summary = "Patrimonio pelo ID", description = "Atravez do ID da entidade Patrimonio é retornado apenas um objeto do mesmo")
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
    @Operation(summary = "Lista de Patrimonio", description = "É retornado uma lista de objetos Patrimonio")
    public Response listAll() {
        try {
            return Response.ok(service.listAll()).build();
        } catch (Exception e) {
            return Response.status(500, Status.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
        }

    }

    @GET
    @Path("page")
    @Operation(summary = "Lista de Patrimonio pagináveis", description = "É retornado uma lista de objetos Patrimonio com paginação")
    public Response listAllPage(@QueryParam Integer page, @QueryParam Integer size) {
        PanacheQuery<Patrimonio> pages = service.findAllPage(page, size);
        return Response.ok(pages.list()).header("pages", pages.pageCount()).header("totalElements", pages.count())
                .build();
    }

    @POST
    @Operation(summary = "Insere Patrimonio", description = "Insere um novo objeto Patrimonio e retornado URI para localizar o objeto")
    public Response insert(final @RequestBody @Valid PatrimonioInsertDTO dto) throws URISyntaxException {
        Patrimonio patrimonio = service.fromDTO(dto);
        UUID id = service.insert(patrimonio);
        return Response.created(new URI("patrimonio/" + id.toString())).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualiza Patrimonio", description = "Atualiza o objeto Patrimonio")
    public Response update(@PathParam("id") final UUID id, final @Valid PatrimonioUpdateDTO dto) {
        Patrimonio obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return Response.ok(obj).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Remove Patrimonio", description = "Remove o objeto Patrimonio passando o ID do mesmo")
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