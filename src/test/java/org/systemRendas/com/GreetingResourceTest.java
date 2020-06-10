package org.systemRendas.com;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(OrderAnnotation.class)
public class GreetingResourceTest {

    private static final String PATH = "/tipodespesa";

    @Test
    @Order(1)
    public void insert() {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("descricao", "Minha descricao");

        given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).body(jsonBody).when()
                .post(PATH).then().statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Test
    @Order(2)
    public void listAll() {
        given().when().get(PATH).then().statusCode(200).body("descricao", CoreMatchers.hasItems("Minha descricao"));
    }

}