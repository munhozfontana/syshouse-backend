package org.systemrendas.resources;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
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
public class TipoDespesaResourceTest {

    private static final String PATH_TIPOPATRIMONIO = "/tipopatrimonio";
    private String atibuteValue = "descricao";
    private String idTipoPatrimonio;
    private HashMap<String, Object> bodyReqTipoPatrimonio = new HashMap<String, Object>();

    @BeforeAll
    void setUp() {
        bodyReqTipoPatrimonio.put("descricao", atibuteValue);
    }

    private RequestSpecification requisicao() {
        return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    public void testInsert() {
        requisicao().body(bodyReqTipoPatrimonio).when().post(PATH_TIPOPATRIMONIO).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());
    }

    @Test
    @Order(2)
    public void testListAll() {
        idTipoPatrimonio = requisicao().when().get(PATH_TIPOPATRIMONIO).then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1)).extract().jsonPath()
                .get("[0].id");
    }

    @Test
    @Order(3)
    public void testListAllPegeable() {
        final int page = 0;
        final int size = 5;
        requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_TIPOPATRIMONIO + "/page").then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
    }

    @Test
    @Order(4)
    public void testFindById() {
        requisicao().pathParam("id", idTipoPatrimonio).when().get(PATH_TIPOPATRIMONIO + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode()).body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
    }

    @Test
    @Order(5)
    public void testUpdate() {
        Map<String, Object> body = bodyReqTipoPatrimonio;
        body.put(atibuteValue, "nomeDiferente");
        requisicao().pathParam("id", idTipoPatrimonio).body(body).when().put(PATH_TIPOPATRIMONIO + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(atibuteValue, CoreMatchers.equalTo("nomeDiferente"));
    }

    @Test
    @Order(6)
    public void testDelete() {
        requisicao().pathParam("id", idTipoPatrimonio).when().delete(PATH_TIPOPATRIMONIO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().when().get(PATH_TIPOPATRIMONIO).then().statusCode(Response.Status.OK.getStatusCode())
                .body("size()", CoreMatchers.is(0));

    }

}