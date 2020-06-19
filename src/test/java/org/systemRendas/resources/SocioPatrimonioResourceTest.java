// package org.systemrendas.resources;

// import static io.restassured.RestAssured.given;

// import java.util.HashMap;
// import java.util.Map;

// import javax.ws.rs.core.MediaType;
// import javax.ws.rs.core.Response;

// import org.hamcrest.CoreMatchers;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;
// import org.junit.jupiter.api.TestInstance.Lifecycle;
// import org.junit.jupiter.api.TestMethodOrder;

// import io.quarkus.test.common.QuarkusTestResource;
// import io.quarkus.test.h2.H2DatabaseTestResource;
// import io.quarkus.test.junit.QuarkusTest;
// import io.restassured.specification.RequestSpecification;

// @QuarkusTestResource(H2DatabaseTestResource.class)
// @QuarkusTest
// @TestMethodOrder(OrderAnnotation.class)
// @TestInstance(Lifecycle.PER_CLASS)
// public class SocioPatrimonioResourceTest {

// private static final String PATH_SOCIOPATRIMONIO = "/sociopatrimonio";
// private static final String PATH_SOCIO = "/socio";
// private String atibuteValue = "endereco";
// private String idSocioPatrimonio;
// private String idSocio;

// private HashMap<String, Object> bodyReqSocioPatrimonio;

// @BeforeAll
// void setUp() {
// bodyReqSocioPatrimonio = new HashMap<String, Object>();
// bodyReqSocioPatrimonio.put("endereco", atibuteValue);
// bodyReqSocioPatrimonio.put("bairro", "bairro");
// bodyReqSocioPatrimonio.put("cep", "71010057");
// bodyReqSocioPatrimonio.put("complemento", "complemento");
// bodyReqSocioPatrimonio.put("latitude", -15.7801);
// bodyReqSocioPatrimonio.put("longitude", -47.9292);

// }

// private RequestSpecification requisicao() {
// return
// given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
// }

// @Test
// @Order(0)
// public void testSetUpLoad() {
// HashMap<String, Object> obj = new HashMap<String, Object>();
// obj.put("ibge", Math.min(1, 5300108));
// obj.put("nome", "nome");
// obj.put("pais", "pais");
// obj.put("populacao", Math.min(1, 99999999));
// obj.put("uf", "uf");

// requisicao().body(obj).when().post(PATH_SOCIO).then().statusCode(Response.Status.CREATED.getStatusCode());

// idSocio =
// requisicao().when().get(PATH_SOCIO).then().statusCode(Response.Status.OK.getStatusCode())
// .body("size()", CoreMatchers.is(1)).extract().jsonPath().get("[0].id");

// bodyReqSocioPatrimonio.put("socioId", idSocio);
// }

// @Test
// @Order(1)
// public void testInsert() {
// requisicao().body(bodyReqSocioPatrimonio).when().post(PATH_SOCIOPATRIMONIO).then()
// .statusCode(Response.Status.CREATED.getStatusCode()).header("location",
// CoreMatchers.notNullValue());
// }

// @Test
// @Order(2)
// public void testListAll() {
// idSocioPatrimonio = requisicao().when().get(PATH_SOCIOPATRIMONIO).then()
// .statusCode(Response.Status.OK.getStatusCode()).body("size()",
// CoreMatchers.is(1)).extract().jsonPath()
// .get("[0].id");
// }

// @Test
// @Order(3)
// public void testListAllPegeable() {
// final int page = 0;
// final int size = 5;
// requisicao().queryParam("page", page).queryParam("size",
// size).when().get(PATH_SOCIOPATRIMONIO + "/page").then()
// .statusCode(Response.Status.OK.getStatusCode()).body("size()",
// CoreMatchers.is(1));
// }

// @Test
// @Order(4)
// public void testFindById() {
// requisicao().pathParam("id",
// idSocioPatrimonio).when().get(PATH_SOCIOPATRIMONIO + "/{id}").then()
// .statusCode(Response.Status.OK.getStatusCode()).body(atibuteValue,
// CoreMatchers.equalTo(atibuteValue));
// }

// @Test
// @Order(5)
// public void testUpdate() {
// Map<String, Object> body = bodyReqSocioPatrimonio;
// body.put(atibuteValue, "nomeDiferente");
// requisicao().pathParam("id",
// idSocioPatrimonio).body(body).when().put(PATH_SOCIOPATRIMONIO +
// "/{id}").then()
// .statusCode(Response.Status.OK.getStatusCode())
// .body(atibuteValue, CoreMatchers.equalTo("nomeDiferente"));
// }

// @Test
// @Order(6)
// public void testDelete() {
// requisicao().pathParam("id",
// idSocioPatrimonio).when().delete(PATH_SOCIOPATRIMONIO + "/{id}").then()
// .statusCode(Response.Status.NO_CONTENT.getStatusCode());

// requisicao().when().get(PATH_SOCIOPATRIMONIO).then().statusCode(Response.Status.OK.getStatusCode())
// .body("size()", CoreMatchers.is(0));

// }

// @Test
// @Order(7)
// public void testCleanUp() {
// requisicao().pathParam("id", idSocio).when().delete(PATH_SOCIO +
// "/{id}").then()
// .statusCode(Response.Status.NO_CONTENT.getStatusCode());

// }

// }