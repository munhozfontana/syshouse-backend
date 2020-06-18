package org.systemrendas.resources;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
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
@ApplicationScoped
public class MunicipioResourceTest {

    private static final String PATH_MUNICIPIO = "/municipio";
    private String atibuteValue = "nome";
    private String idMunicipio;
    private HashMap<String, Object> bodyReqMunicipio;

    @BeforeAll
    void setUp() {
        bodyReqMunicipio = new HashMap<String, Object>();
        bodyReqMunicipio.put("ibge", Math.min(1, 5300108));
        bodyReqMunicipio.put("nome", atibuteValue);
        bodyReqMunicipio.put("pais", "pais");
        bodyReqMunicipio.put("populacao", Math.min(1, 99999999));
        bodyReqMunicipio.put("uf", "uf");
    }

    private RequestSpecification requisicao() {
        return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    public void testInsert() {
        requisicao().body(bodyReqMunicipio).when().post(PATH_MUNICIPIO).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());
    }

    @Test
    @Order(2)
    public void testListAll() {
        idMunicipio = requisicao().when().get(PATH_MUNICIPIO).then().statusCode(Response.Status.OK.getStatusCode())
                .body("size()", CoreMatchers.is(1)).extract().jsonPath().get("[0].id");
    }

    @Test
    @Order(3)
    public void testListAllPegeable() {
        final int page = 0;
        final int size = 5;
        requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_MUNICIPIO + "/page").then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
    }

    @Test
    @Order(4)
    public void testFindById() {
        requisicao().pathParam("id", idMunicipio).when().get(PATH_MUNICIPIO + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode()).body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
    }

    @Test
    @Order(5)
    public void testUpdate() {
        Map<String, Object> body = bodyReqMunicipio;
        body.put(atibuteValue, "nomeDiferente");
        requisicao().pathParam("id", idMunicipio).body(body).when().put(PATH_MUNICIPIO + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(atibuteValue, CoreMatchers.equalTo("nomeDiferente"));
    }

    @Test
    @Order(6)
    public void testDelete() {
        requisicao().pathParam("id", idMunicipio).when().delete(PATH_MUNICIPIO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().when().get(PATH_MUNICIPIO).then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
                CoreMatchers.is(0));

    }

}