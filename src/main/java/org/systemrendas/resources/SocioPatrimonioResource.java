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
import org.systemrendas.domain.SocioPatrimonio;
import org.systemrendas.dto.sociopatrimonio.SocioPatrimonioInsertDTO;
import org.systemrendas.dto.sociopatrimonio.SocioPatrimonioUpdateDTO;
import org.systemrendas.services.SocioPatrimonioService;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("sociopatrimonio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Socio Patrimonio")
public class SocioPatrimonioResource {

    @Inject
    SocioPatrimonioService service;

    @GET
    @Path("{id}")
    @Operation(summary = "SocioPatrimonio pelo ID", description = "Atravez do ID da entidade SocioPatrimonio é retornado apenas um objeto do mesmo")
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
    @Operation(summary = "Lista de SocioPatrimonio", description = "É retornado uma lista de objetos SocioPatrimonio")
    public Response listAll() {
        try {
            return Response.ok(service.listAll()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("page")
    @Operation(summary = "Lista de SocioPatrimonio pagináveis", description = "É retornado uma lista de objetos SocioPatrimonio com paginação")
    public Response listAllPage(@QueryParam Integer page, @QueryParam Integer size) {
        PanacheQuery<SocioPatrimonio> pages = service.findAllPage(page, size);
        return Response.ok(pages.list()).header("pages", pages.pageCount()).header("totalElements", pages.count())
                .build();
    }

    @POST
    @Operation(summary = "Insere SocioPatrimonio", description = "Insere um novo objeto SocioPatrimonio e retornado URI para localizar o objeto")
    public Response insert(final @RequestBody @Valid SocioPatrimonioInsertDTO dto) throws URISyntaxException {
        SocioPatrimonio entidade = null;

        try {
            entidade = service.fromDTO(dto);
        } catch (ObjectNotFoundException e) {
            return Response.status(Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }

        UUID id = service.insert(entidade);
        return Response.created(new URI("sociopatrimonio/" + id.toString())).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualiza SocioPatrimonio", description = "Atualiza o objeto SocioPatrimonio")
    public Response update(@PathParam("id") final UUID id, final @Valid SocioPatrimonioUpdateDTO dto) {
        SocioPatrimonio obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return Response.ok(obj).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Remove SocioPatrimonio", description = "Remove o objeto SocioPatrimonio passando o ID do mesmo")
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