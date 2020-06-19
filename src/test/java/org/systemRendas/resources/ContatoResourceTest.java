package org.systemrendas.resources;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.specification.RequestSpecification;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ContatoResourceTest {

    private static final String PATH_SOCIO = "/socio";
    private static final String PATH_PAGADOR = "/pagador";
    private static final String PATH_CONTATO = "/contato";

    private String idContato;
    private String idSocio;
    private String idPagador;

    private HashMap<String, Object> bodySocio = new HashMap<String, Object>();
    private HashMap<String, Object> bodyPagador = new HashMap<String, Object>();
    private HashMap<String, Object> bodyContato = new HashMap<String, Object>();

    private String atibuteValue = "email";

    private RequestSpecification requisicao() {
        return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(0)
    public void testSetUpLoad() {
        idSocio = insertSocio();
        idPagador = insertPagador();
    }

    private String insertSocio() {
        bodySocio.put("cpf", "cpf");
        bodySocio.put("estadoCivil", "estadoCivil");
        bodySocio.put("nacionalidade", "nacionalidade");
        bodySocio.put("nome", "nome");
        bodySocio.put("profissao", "profissao");
        bodySocio.put("rg", "rg");

        requisicao().body(bodySocio).when().post(PATH_SOCIO).then().statusCode(Response.Status.CREATED.getStatusCode());

        return requisicao().when().get(PATH_SOCIO).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    private String insertPagador() {
        bodyPagador.put("cnpj", "string");
        bodyPagador.put("cpf", "string");
        bodyPagador.put("endereco", "string");
        bodyPagador.put("estadoCivil", "string");
        bodyPagador.put("nacionalidade", "nacionalidade");
        bodyPagador.put("nascimento", "06/06/1994");
        bodyPagador.put("nome", "string");
        bodyPagador.put("profissao", "string");
        bodyPagador.put("rg", "string");

        requisicao().body(bodyPagador).when().post(PATH_PAGADOR).then()
                .statusCode(Response.Status.CREATED.getStatusCode());

        return requisicao().when().get(PATH_PAGADOR).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    @Test
    @Order(1)
    public void testInsert() {
        bodyContato.put("email", atibuteValue);
        bodyContato.put("fone", "string");
        bodyContato.put("pagadorId", idPagador);
        bodyContato.put("socioId", idSocio);
        bodyContato.put("whatsapp", true);

        requisicao().body(bodyContato).when().post(PATH_CONTATO).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());
    }

    @Test
    @Order(2)
    public void testListAll() {
        idContato = requisicao().when().get(PATH_CONTATO).then().statusCode(Response.Status.OK.getStatusCode())
                .body("size()", CoreMatchers.is(1)).extract().jsonPath().get("[0].id");
    }

    @Test
    @Order(3)
    public void testListAllPegeable() {
        final int page = 0;
        final int size = 5;
        requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_CONTATO + "/page").then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
    }

    @Test
    @Order(4)
    public void testFindById() {
        requisicao().pathParam("id", idContato).when().get(PATH_CONTATO + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode()).body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
    }

    @Test
    @Order(5)
    public void testUpdate() {
        Map<String, Object> body = bodyContato;
        body.put(atibuteValue, "ValorAlterado@gmail.com");
        requisicao().pathParam("id", idContato).body(body).when().put(PATH_CONTATO + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(atibuteValue, CoreMatchers.equalTo("ValorAlterado@gmail.com"));
    }

    @Test
    @Order(6)
    public void testDelete() {
        requisicao().pathParam("id", idContato).when().delete(PATH_CONTATO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().when().get(PATH_CONTATO).then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
                CoreMatchers.is(0));

    }

    @Test
    @Order(7)
    public void testCleanUp() {

        requisicao().pathParam("id", idSocio).when().delete(PATH_SOCIO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().pathParam("id", idPagador).when().delete(PATH_PAGADOR + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

    }

}