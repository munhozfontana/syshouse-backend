package org.systemrendas.resources;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.specification.RequestSpecification;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestInstance(Lifecycle.PER_CLASS)
public class SocioResourceTest {

    private static final String PATH_SOCIO = "/socio";
    private String atibuteValue = "nacionalidade";
    private String idSocio;
    private HashMap<String, Object> bodyReqSocio;

    @BeforeAll
    void setUp() {
        bodyReqSocio = new HashMap<String, Object>();
        bodyReqSocio.put("cpf", "cpf");
        bodyReqSocio.put("estadoCivil", atibuteValue);
        bodyReqSocio.put("nacionalidade", "nacionalidade");
        bodyReqSocio.put("nome", "nome");
        bodyReqSocio.put("profissao", "profissao");
        bodyReqSocio.put("rg", "rg");
    }

    private RequestSpecification requisicao() {
        return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    public void testInsert() {
        requisicao().body(bodyReqSocio).when().post(PATH_SOCIO).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());
    }

    @Test
    @Order(2)
    public void testListAll() {
        idSocio = requisicao().when().get(PATH_SOCIO).then().statusCode(Response.Status.OK.getStatusCode())
                .body("size()", CoreMatchers.is(1)).extract().jsonPath().get("[0].id");
    }

    @Test
    @Order(3)
    public void testListAllPegeable() {
        final int page = 0;
        final int size = 5;
        requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_SOCIO + "/page").then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
    }

    @Test
    @Order(4)
    public void testFindById() {
        requisicao().pathParam("id", idSocio).when().get(PATH_SOCIO + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode()).body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
    }

    @Test
    @Order(5)
    public void testUpdate() {
        Map<String, Object> body = bodyReqSocio;
        body.put(atibuteValue, "nomeDiferente");
        requisicao().pathParam("id", idSocio).body(body).when().put(PATH_SOCIO + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(atibuteValue, CoreMatchers.equalTo("nomeDiferente"));
    }

    @Test
    @Order(6)
    public void testDelete() {
        requisicao().pathParam("id", idSocio).when().delete(PATH_SOCIO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().when().get(PATH_SOCIO).then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
                CoreMatchers.is(0));

    }

}