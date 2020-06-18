package org.systemrendas.resources;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.systemrendas.domain.Localizacao;
import org.systemrendas.domain.Municipio;
import org.systemrendas.repositories.MunicipioRepository;

import io.quarkus.launcher.shaded.com.google.inject.Inject;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.specification.RequestSpecification;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestInstance(Lifecycle.PER_CLASS)
@Tag("integration")
public class LocalizacaoResourceTest {

        private static final String PATH_LOCALIZACAO = "/localizacao";
        private static final String PATH_MUNICIPIO = "/municipio";
        private String atibuteValue = "endereco";
        private Municipio[] municipiosResponse;

        @Inject
        MunicipioRepository municipioRepository;

        private RequestSpecification requisicao() {
                return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        }

        private HashMap<String, Object> newElement() {
                HashMap<String, Object> entidade = new HashMap<String, Object>();
                entidade.put("municipioId", municipiosResponse[0].getId());
                entidade.put("endereco", atibuteValue);
                entidade.put("bairro", "bairro");
                entidade.put("cep", "71010057");
                entidade.put("complemento", "complemento");
                entidade.put("latitude", -15.7801);
                entidade.put("longitude", -47.9292);
                return entidade;
        }

        @Test
        @Order(0)
        public void testSetUpLoad() {
                HashMap<String, Object> obj = new HashMap<String, Object>();
                obj.put("ibge", Math.min(1, 5300108));
                obj.put("nome", "nome");
                obj.put("pais", "pais");
                obj.put("populacao", Math.min(1, 99999999));
                obj.put("uf", "uf");

                requisicao().body(obj).when().post(PATH_MUNICIPIO).then()
                                .statusCode(Response.Status.CREATED.getStatusCode());

                municipiosResponse = requisicao().when().get(PATH_MUNICIPIO).then()
                                .statusCode(Response.Status.OK.getStatusCode()).extract().as(Municipio[].class);
        }

        @Test
        @Order(1)
        public void testInsert() {

                requisicao().body(newElement()).when().post(PATH_LOCALIZACAO).then()
                                .statusCode(Response.Status.CREATED.getStatusCode())
                                .header("location", CoreMatchers.notNullValue());
        }

        @Test
        @Order(2)
        public void testListAll() {
                requisicao().when().get(PATH_LOCALIZACAO).then().statusCode(Response.Status.OK.getStatusCode())
                                .body("size()", CoreMatchers.is(1)).extract();
        }

        @Test
        @Order(3)
        public void testListAllPegeable() {
                final int page = 0;
                final int size = 5;
                requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_LOCALIZACAO + "/page")
                                .then().statusCode(Response.Status.OK.getStatusCode())
                                .body("size()", CoreMatchers.is(1));
        }

        @Test
        @Order(4)
        public void testFindById() {
                final Localizacao[] result = requisicao().when().get(PATH_LOCALIZACAO).then()
                                .statusCode(Response.Status.OK.getStatusCode()).extract().as(Localizacao[].class);
                requisicao().pathParam("id", result[0].getId()).when().get(PATH_LOCALIZACAO + "/{id}").then()
                                .statusCode(Response.Status.OK.getStatusCode())
                                .body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
        }

        @Test
        @Order(5)
        public void testUpdate() {
                final Localizacao[] result = requisicao().when().get(PATH_LOCALIZACAO).then()
                                .statusCode(Response.Status.OK.getStatusCode()).extract().as(Localizacao[].class);
                Map<String, Object> body = newElement();
                body.put("id", result[0].getId());
                body.put(atibuteValue, "nomeDiferente");
                requisicao().pathParam("id", result[0].getId()).body(body).when().put(PATH_LOCALIZACAO + "/{id}").then()
                                .statusCode(Response.Status.OK.getStatusCode())
                                .body(atibuteValue, CoreMatchers.equalTo("nomeDiferente"));
        }

        @Test
        @Order(6)
        public void testDelete() {
                final Localizacao[] result = requisicao().when().get(PATH_LOCALIZACAO).then()
                                .statusCode(Response.Status.OK.getStatusCode()).extract().as(Localizacao[].class);

                requisicao().pathParam("id", result[0].getId()).when().delete(PATH_LOCALIZACAO + "/{id}").then()
                                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

                requisicao().when().get(PATH_LOCALIZACAO).then().statusCode(Response.Status.OK.getStatusCode())
                                .body("size()", CoreMatchers.is(0));
        }

        @Test
        @Order(7)
        public void testCleanUp() {
                requisicao().pathParam("id", municipiosResponse[0].getId()).when().delete(PATH_MUNICIPIO + "/{id}")
                                .then().statusCode(Response.Status.NO_CONTENT.getStatusCode());

        }

}