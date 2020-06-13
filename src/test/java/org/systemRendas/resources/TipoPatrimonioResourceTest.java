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
import org.junit.jupiter.api.TestMethodOrder;
import org.systemrendas.domain.TipoPatrimonio;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.specification.RequestSpecification;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class TipoPatrimonioResourceTest {

    private static final String PATH = "/tipopatrimonio";
    private final int elementsToInsert = 5;
    private String atibuteValue = "descricao";

    private RequestSpecification requisicao() {
        return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    }

    private Map<String, Object> newElement() {
        return new HashMap<String, Object>() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            {
                put("descricao", atibuteValue);
            }
        };

    }

    @Test
    @Order(1)
    public void testInsert() {
        for (int i = 0; i < elementsToInsert; i++) {
            requisicao().body(newElement()).when().post(PATH).then().statusCode(Response.Status.CREATED.getStatusCode())
                    .header("location", CoreMatchers.notNullValue());
        }
    }

    @Test
    @Order(2)
    public void testListAll() {
        requisicao().when().get(PATH).then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
                CoreMatchers.is(elementsToInsert));
    }

    @Test
    @Order(3)
    public void testListAllPegeable() {
        final int page = 0;
        final int size = 5;
        requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH + "/page").then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(5));
    }

    @Test
    @Order(4)
    public void testFindById() {
        final TipoPatrimonio[] result = requisicao().when().get(PATH).then()
                .statusCode(Response.Status.OK.getStatusCode()).extract().as(TipoPatrimonio[].class);
        requisicao().pathParam("id", result[0].getId()).when().get(PATH + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode()).body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
    }

    @Test
    @Order(5)
    public void testUpdate() {
        final TipoPatrimonio[] result = requisicao().when().get(PATH).then()
                .statusCode(Response.Status.OK.getStatusCode()).extract().as(TipoPatrimonio[].class);
        Map<String, Object> body = newElement();
        body.put(atibuteValue, "nomeDiferente");
        requisicao().pathParam("id", result[0].getId()).body(body).when().put(PATH + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(atibuteValue, CoreMatchers.equalTo("nomeDiferente"));
    }

    @Test
    @Order(6)
    public void testDelete() {
        final TipoPatrimonio[] result = requisicao().when().get(PATH).then()
                .statusCode(Response.Status.OK.getStatusCode()).extract().as(TipoPatrimonio[].class);

        requisicao().pathParam("id", result[0].getId()).when().delete(PATH + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().when().get(PATH).then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
                CoreMatchers.is(elementsToInsert - 1));
    }

}