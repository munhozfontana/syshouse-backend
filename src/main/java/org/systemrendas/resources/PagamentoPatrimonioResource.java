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
import org.systemrendas.domain.PagamentoPatrimonio;
import org.systemrendas.dto.pagamentopatrimonio.PagamentoPatrimonioInsertDTO;
import org.systemrendas.dto.pagamentopatrimonio.PagamentoPatrimonioUpdateDTO;
import org.systemrendas.services.PagamentoPatrimonioService;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("pagamentopatrimonio")
@Tag(name = "Pagamento do Patrimonio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoPatrimonioResource {

    @Inject
    PagamentoPatrimonioService service;

    @GET
    @Path("{id}")
    @Operation(summary = "PagamentoPatrimonio pelo ID", description = "Atravez do ID da entidade PagamentoPatrimonio é retornado apenas um objeto do mesmo")
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
    @Operation(summary = "Lista de PagamentoPatrimonio", description = "É retornado uma lista de objetos PagamentoPatrimonio")
    public Response listAll() {
        try {
            return Response.ok(service.listAll()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("page")
    @Operation(summary = "Lista de PagamentoPatrimonio pagináveis", description = "É retornado uma lista de objetos PagamentoPatrimonio com paginação")
    public Response listAllPage(@QueryParam Integer page, @QueryParam Integer size) {
        PanacheQuery<PagamentoPatrimonio> pages = service.findAllPage(page, size);
        return Response.ok(pages.list()).header("pages", pages.pageCount()).header("totalElements", pages.count())
                .build();
    }

    @POST
    @Operation(summary = "Insere PagamentoPatrimonio", description = "Insere um novo objeto PagamentoPatrimonio e retornado URI para localizar o objeto")
    public Response insert(final @RequestBody @Valid PagamentoPatrimonioInsertDTO dto) throws URISyntaxException {
        PagamentoPatrimonio pagamentopatrimonio = service.fromDTO(dto);
        UUID id = service.insert(pagamentopatrimonio);
        return Response.created(new URI("pagamentopatrimonio/" + id.toString())).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualiza PagamentoPatrimonio", description = "Atualiza o objeto PagamentoPatrimonio")
    public Response update(@PathParam("id") final UUID id, final @Valid PagamentoPatrimonioUpdateDTO dto) {
        PagamentoPatrimonio obj = service.fromDTO(dto);
        obj.setId(id);
        service.update(obj);
        return Response.ok(obj).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Remove PagamentoPatrimonio", description = "Remove o objeto PagamentoPatrimonio passando o ID do mesmo")
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