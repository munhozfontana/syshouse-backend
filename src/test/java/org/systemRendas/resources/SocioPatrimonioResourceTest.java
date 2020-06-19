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

@QuarkusTestResource(H2DatabaseTestResource.class)
@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class SocioPatrimonioResourceTest {

        private static final String PATH_LOCALIZACAO = "/localizacao";
        private static final String PATH_MUNICIPIO = "/municipio";
        private static final String PATH_PATRIMONIO = "/patrimonio";
        private static final String PATH_TIPOPATRIMONIO = "/tipopatrimonio";
        private static final String PATH_SOCIO = "/socio";
        private static final String PATH_SOCIOPATRIMONIO = "/sociopatrimonio";

        private String idLocalizacao;
        private String idMunicipio;
        private String idPatrimonio;
        private String idTipoPatrimonio;
        private String idSocio;
        private String idSocioPatrimonio;

        private HashMap<String, Object> bodyPatrimonio = new HashMap<String, Object>();
        private HashMap<String, Object> bodyLocalizacao = new HashMap<String, Object>();
        private HashMap<String, Object> bodyMunicipio = new HashMap<String, Object>();
        private HashMap<String, Object> bodyTipoPatrimonio = new HashMap<String, Object>();
        private HashMap<String, Object> bodySocio = new HashMap<String, Object>();
        private HashMap<String, Object> bodySocioPatrimonio = new HashMap<String, Object>();

        private Boolean atibuteValue = true;

        private RequestSpecification requisicao() {
                return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        }

        @Test
        @Order(0)
        public void testSetUpLoad() {
                idMunicipio = insertMunicipio();
                idSocio = insertSocio();
                idTipoPatrimonio = insertTipoPatrimonio();
                idLocalizacao = insertLocalizacao(idMunicipio);
                idPatrimonio = insertPatrimonio(idLocalizacao, idTipoPatrimonio);
        }

        private String insertSocio() {

                bodySocio.put("cpf", "cpf");
                bodySocio.put("estadoCivil", "estadoCivil");
                bodySocio.put("nacionalidade", "nacionalidade");
                bodySocio.put("nome", "nome");
                bodySocio.put("profissao", "profissao");
                bodySocio.put("rg", "rg");

                requisicao().body(bodySocio).when().post(PATH_SOCIO).then()
                                .statusCode(Response.Status.CREATED.getStatusCode());

                return requisicao().when().get(PATH_SOCIO).then().statusCode(Response.Status.OK.getStatusCode())
                                .extract().jsonPath().get("[0].id");
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
                bodySocioPatrimonio.put("porcentagem", 4.6);
                bodySocioPatrimonio.put("proprietario", atibuteValue);
                bodySocioPatrimonio.put("socioId", idSocio);
                bodySocioPatrimonio.put("patrimonioId", idPatrimonio);

                requisicao().body(bodySocioPatrimonio).when().post(PATH_SOCIOPATRIMONIO).then()
                                .statusCode(Response.Status.CREATED.getStatusCode())
                                .header("location", CoreMatchers.notNullValue());
        }

        @Test
        @Order(2)
        public void testListAll() {
                idSocioPatrimonio = requisicao().when().get(PATH_SOCIOPATRIMONIO).then()
                                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1))
                                .extract().jsonPath().get("[0].id");
        }

        @Test
        @Order(3)
        public void testListAllPegeable() {
                final int page = 0;
                final int size = 5;
                requisicao().queryParam("page", page).queryParam("size", size).when()
                                .get(PATH_SOCIOPATRIMONIO + "/page").then()
                                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
        }

        @Test
        @Order(4)
        public void testFindById() {
                requisicao().pathParam("id", idSocioPatrimonio).when().get(PATH_SOCIOPATRIMONIO + "/{id}").then()
                                .statusCode(Response.Status.OK.getStatusCode())
                                .body("proprietario", CoreMatchers.equalTo(true));
        }

        @Test
        @Order(5)
        public void testUpdate() {
                Map<String, Object> body = bodySocioPatrimonio;
                body.put("proprietario", false);
                requisicao().pathParam("id", idSocioPatrimonio).body(body).when().put(PATH_SOCIOPATRIMONIO + "/{id}")
                                .then().statusCode(Response.Status.OK.getStatusCode())
                                .body("proprietario", CoreMatchers.equalTo(false));
        }

        @Test
        @Order(6)
        public void testDelete() {
                requisicao().pathParam("id", idSocioPatrimonio).when().delete(PATH_SOCIOPATRIMONIO + "/{id}").then()
                                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

                requisicao().when().get(PATH_SOCIOPATRIMONIO).then().statusCode(Response.Status.OK.getStatusCode())
                                .body("size()", CoreMatchers.is(0));

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

                requisicao().pathParam("id", idSocio).when().delete(PATH_SOCIO + "/{id}").then()
                                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        }

}