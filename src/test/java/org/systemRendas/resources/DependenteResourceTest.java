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
public class DependenteResourceTest {

    private static final String PATH_PAGADOR = "/pagador";
    private static final String PATH_DEPENDENTE = "/dependente";
    private String idPagador;
    private String idDependente;

    private HashMap<String, Object> bodyPagador = new HashMap<String, Object>();
    private HashMap<String, Object> bodyDependete = new HashMap<String, Object>();

    private String atibuteValue = "nome";

    private RequestSpecification requisicao() {
        return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(0)
    public void testSetUpLoad() {
        idPagador = insertPagador();
    }

    private String insertPagador() {
        bodyPagador.put("cpf", "cpf");
        bodyPagador.put("estadoCivil", "estadoCivil");
        bodyPagador.put("nacionalidade", "nacionalidade");
        bodyPagador.put("nome", "nome");
        bodyPagador.put("profissao", "profissao");
        bodyPagador.put("rg", "rg");

        requisicao().body(bodyPagador).when().post(PATH_PAGADOR).then()
                .statusCode(Response.Status.CREATED.getStatusCode());

        return requisicao().when().get(PATH_PAGADOR).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    @Test
    @Order(1)
    public void testInsert() {
        bodyDependete.put("nome", atibuteValue);
        bodyDependete.put("pagadorId", idPagador);

        requisicao().body(bodyDependete).when().post(PATH_DEPENDENTE).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());
    }

    @Test
    @Order(2)
    public void testListAll() {
        idDependente = requisicao().when().get(PATH_DEPENDENTE).then().statusCode(Response.Status.OK.getStatusCode())
                .body("size()", CoreMatchers.is(1)).extract().jsonPath().get("[0].id");
    }

    @Test
    @Order(3)
    public void testListAllPegeable() {
        final int page = 0;
        final int size = 5;
        requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_DEPENDENTE + "/page").then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
    }

    @Test
    @Order(4)
    public void testFindById() {
        requisicao().pathParam("id", idDependente).when().get(PATH_DEPENDENTE + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode()).body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
    }

    @Test
    @Order(5)
    public void testUpdate() {
        Map<String, Object> body = bodyDependete;
        body.put(atibuteValue, "novoDadoDiferente");
        requisicao().pathParam("id", idDependente).body(body).when().put(PATH_DEPENDENTE + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(atibuteValue, CoreMatchers.equalTo("novoDadoDiferente"));
    }

    @Test
    @Order(6)
    public void testDelete() {
        requisicao().pathParam("id", idDependente).when().delete(PATH_DEPENDENTE + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().when().get(PATH_DEPENDENTE).then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
                CoreMatchers.is(0));

    }

    @Test
    @Order(7)
    public void testCleanUp() {
        requisicao().pathParam("id", idPagador).when().delete(PATH_PAGADOR + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

    }

}