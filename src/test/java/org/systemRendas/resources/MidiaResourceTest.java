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
public class MidiaResourceTest {

        private static final String PATH_LOCALIZACAO = "/localizacao";
        private static final String PATH_MUNICIPIO = "/municipio";
        private static final String PATH_PATRIMONIO = "/patrimonio";
        private static final String PATH_TIPOPATRIMONIO = "/tipopatrimonio";
        private static final String PATH_MIDIA = "/midia";

        private String idLocalizacao;
        private String idMunicipio;
        private String idPatrimonio;
        private String idTipoPatrimonio;
        private String idMidia;

        private HashMap<String, Object> bodyPatrimonio = new HashMap<String, Object>();
        private HashMap<String, Object> bodyLocalizacao = new HashMap<String, Object>();
        private HashMap<String, Object> bodyMunicipio = new HashMap<String, Object>();
        private HashMap<String, Object> bodyTipoPatrimonio = new HashMap<String, Object>();
        private HashMap<String, Object> bodyMidia = new HashMap<String, Object>();

        private String atibuteValue = "nome";

        private RequestSpecification requisicao() {
                return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        }

        @Test
        @Order(0)
        public void testSetUpLoad() {
                idMunicipio = insertMunicipio();
                idTipoPatrimonio = insertTipoPatrimonio();
                idLocalizacao = insertLocalizacao(idMunicipio);
                idPatrimonio = insertPatrimonio(idLocalizacao, idTipoPatrimonio);
        }

        private String insertMunicipio() {
                bodyMunicipio.put("ibge", Math.min(1, 5300108));
                bodyMunicipio.put("nome", "nome");
                bodyMunicipio.put("pais", "pais");
                bodyMunicipio.put("populacao", Math.min(1, 99999999));
                bodyMunicipio.put("uf", "uf");

                requisicao().body(bodyMunicipio).when().post(PATH_MUNICIPIO).then()
                                .statusCode(Response.Status.CREATED.getStatusCode());

                return requisicao().when().get(PATH_MUNICIPIO).then().statusCode(Response.Status.OK.getStatusCode())
                                .extract().jsonPath().get("[0].id");
        }

        private String insertTipoPatrimonio() {
                bodyTipoPatrimonio.put("descricao", "descricao");

                requisicao().body(bodyTipoPatrimonio).when().post(PATH_TIPOPATRIMONIO).then()
                                .statusCode(Response.Status.CREATED.getStatusCode());

                return requisicao().when().get(PATH_TIPOPATRIMONIO).then()
                                .statusCode(Response.Status.OK.getStatusCode()).extract().jsonPath().get("[0].id");
        }

        private String insertLocalizacao(String municipioId) {
                bodyLocalizacao.put("municipioId", municipioId);
                bodyLocalizacao.put("endereco", "endereco");
                bodyLocalizacao.put("bairro", "bairro");
                bodyLocalizacao.put("cep", "71010057");
                bodyLocalizacao.put("complemento", "complemento");
                bodyLocalizacao.put("latitude", -15.7801);
                bodyLocalizacao.put("longitude", -47.9292);

                requisicao().body(bodyLocalizacao).when().post(PATH_LOCALIZACAO).then()
                                .statusCode(Response.Status.CREATED.getStatusCode());

                return requisicao().when().get(PATH_LOCALIZACAO).then().statusCode(Response.Status.OK.getStatusCode())
                                .extract().jsonPath().get("[0].id");
        }

        private String insertPatrimonio(String localizacaoId, String tipoPatrimonioId) {
                bodyPatrimonio.put("dataFim", "18/06/2020");
                bodyPatrimonio.put("dataInicio", "18/06/2020");
                bodyPatrimonio.put("localizacaoId", localizacaoId);
                bodyPatrimonio.put("tipoPatrimonioId", tipoPatrimonioId);
                bodyPatrimonio.put("nome", "nome");
                bodyPatrimonio.put("valor", 0);

                requisicao().body(bodyPatrimonio).when().post(PATH_PATRIMONIO).then()
                                .statusCode(Response.Status.CREATED.getStatusCode())
                                .header("location", CoreMatchers.notNullValue());

                return requisicao().when().get(PATH_PATRIMONIO).then().statusCode(Response.Status.OK.getStatusCode())
                                .extract().jsonPath().get("[0].id");
        }

        @Test
        @Order(1)
        public void testInsert() {
                bodyMidia.put("caminho", "caminho");
                bodyMidia.put("nome", atibuteValue);
                bodyMidia.put("obs", "observavao");
                bodyMidia.put("patrimonioId", idPatrimonio);
                bodyMidia.put("tipo", "tipo");

                requisicao().body(bodyMidia).when().post(PATH_MIDIA).then()
                                .statusCode(Response.Status.CREATED.getStatusCode())
                                .header("location", CoreMatchers.notNullValue());
        }

        @Test
        @Order(2)
        public void testListAll() {
                idMidia = requisicao().when().get(PATH_MIDIA).then().statusCode(Response.Status.OK.getStatusCode())
                                .body("size()", CoreMatchers.is(1)).extract().jsonPath().get("[0].id");
        }

        @Test
        @Order(3)
        public void testListAllPegeable() {
                final int page = 0;
                final int size = 5;
                requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_MIDIA + "/page").then()
                                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
        }

        @Test
        @Order(4)
        public void testFindById() {
                requisicao().pathParam("id", idMidia).when().get(PATH_MIDIA + "/{id}").then()
                                .statusCode(Response.Status.OK.getStatusCode())
                                .body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
        }

        @Test
        @Order(5)
        public void testUpdate() {
                Map<String, Object> body = bodyMidia;
                body.put(atibuteValue, "nomeDiferente");
                requisicao().pathParam("id", idMidia).body(body).when().put(PATH_MIDIA + "/{id}").then()
                                .statusCode(Response.Status.OK.getStatusCode())
                                .body(atibuteValue, CoreMatchers.equalTo("nomeDiferente"));
        }

        @Test
        @Order(6)
        public void testDelete() {
                requisicao().pathParam("id", idMidia).when().delete(PATH_MIDIA + "/{id}").then()
                                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

                requisicao().when().get(PATH_MIDIA).then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
                                CoreMatchers.is(0));

        }

        @Test
        @Order(7)
        public void testCleanUp() {

                requisicao().pathParam("id", idPatrimonio).when().delete(PATH_PATRIMONIO + "/{id}").then()
                                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

                requisicao().pathParam("id", idLocalizacao).when().delete(PATH_LOCALIZACAO + "/{id}").then()
                                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

                requisicao().pathParam("id", idTipoPatrimonio).when().delete(PATH_TIPOPATRIMONIO + "/{id}").then()
                                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

                requisicao().pathParam("id", idMunicipio).when().delete(PATH_MUNICIPIO + "/{id}").then()
                                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        }

}