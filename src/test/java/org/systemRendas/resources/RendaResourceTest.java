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
public class RendaResourceTest {

    private static final String PATH_MUNICIPIO = "/municipio";
    private static final String PATH_LOCALIZACAO = "/localizacao";
    private static final String PATH_TIPOPATRIMONIO = "/tipopatrimonio";
    private static final String PATH_PATRIMONIO = "/patrimonio";
    private static final String PATH_PAGADOR = "/pagador";
    private static final String PATH_TIPORENDA = "/tiporenda";
    private static final String PATH_RENDA = "/renda";

    private String idLocalizacao;
    private String idMunicipio;
    private String idPatrimonio;
    private String idTipoPatrimonio;
    private String idPagador;
    private String idTipoRenda;
    private String idRenda;

    private HashMap<String, Object> bodyPatrimonio = new HashMap<String, Object>();
    private HashMap<String, Object> bodyLocalizacao = new HashMap<String, Object>();
    private HashMap<String, Object> bodyMunicipio = new HashMap<String, Object>();
    private HashMap<String, Object> bodyTipoDespesa = new HashMap<String, Object>();
    private HashMap<String, Object> bodyPagador = new HashMap<String, Object>();
    private HashMap<String, Object> bodyTipoRenda = new HashMap<String, Object>();
    private HashMap<String, Object> bodyRenda = new HashMap<String, Object>();

    private String atibuteValue = "descricao";

    private RequestSpecification requisicao() {
        return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(0)
    public void testSetUpLoad() {
        idMunicipio = insertMunicipio();
        idTipoPatrimonio = insertTipoPatrimonio();
        idPagador = insertPagador();
        idTipoRenda = insertTipoRenda();
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

        return requisicao().when().get(PATH_MUNICIPIO).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    private String insertTipoPatrimonio() {
        bodyTipoDespesa.put("descricao", "descricao");

        requisicao().body(bodyTipoDespesa).when().post(PATH_TIPOPATRIMONIO).then()
                .statusCode(Response.Status.CREATED.getStatusCode());

        return requisicao().when().get(PATH_TIPOPATRIMONIO).then().statusCode(Response.Status.OK.getStatusCode())
                .extract().jsonPath().get("[0].id");
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

        return requisicao().when().get(PATH_LOCALIZACAO).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    private String insertPatrimonio(String localizacaoId, String tipoPatrimonioId) {
        bodyPatrimonio.put("dataFim", "18/06/2020");
        bodyPatrimonio.put("dataInicio", "18/06/2020");
        bodyPatrimonio.put("localizacaoId", localizacaoId);
        bodyPatrimonio.put("tipoPatrimonioId", tipoPatrimonioId);
        bodyPatrimonio.put("nome", "nome");
        bodyPatrimonio.put("valor", 0);

        requisicao().body(bodyPatrimonio).when().post(PATH_PATRIMONIO).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());

        return requisicao().when().get(PATH_PATRIMONIO).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    private String insertPagador() {
        bodyPagador.put("cnpj", "string");
        bodyPagador.put("cpf", "string");
        bodyPagador.put("endereco", "string");
        bodyPagador.put("estadoCivil", "string");
        bodyPagador.put("nacionalidade", "nacionalidade");
        bodyPagador.put("nascimento", "06/06/1994");
        bodyPagador.put("nome", "string");
        bodyPagador.put("profissao", "string");
        bodyPagador.put("rg", "strin");

        requisicao().body(bodyPagador).when().post(PATH_PAGADOR).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());

        return requisicao().when().get(PATH_PAGADOR).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    private String insertTipoRenda() {
        bodyTipoRenda.put("descricao", atibuteValue);

        requisicao().body(bodyTipoRenda).when().post(PATH_TIPORENDA).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());

        return requisicao().when().get(PATH_TIPORENDA).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    @Test
    @Order(1)
    public void testInsert() {
        bodyRenda.put("dataFim", "06/04/1994");
        bodyRenda.put("dataInicio", "06/04/1994");
        bodyRenda.put("descricao", "descricao");
        bodyRenda.put("obs", "obs");
        bodyRenda.put("pagadorId", idPagador);
        bodyRenda.put("patrimonioId", idPatrimonio);
        bodyRenda.put("tipoRendaId", idTipoRenda);
        bodyRenda.put("valor", 0);
        bodyRenda.put("vencimento", "06/04/1994");

        requisicao().body(bodyRenda).when().post(PATH_RENDA).then().statusCode(Response.Status.CREATED.getStatusCode())
                .header("location", CoreMatchers.notNullValue());
    }

    @Test
    @Order(2)
    public void testListAll() {
        idRenda = requisicao().when().get(PATH_RENDA).then().statusCode(Response.Status.OK.getStatusCode())
                .body("size()", CoreMatchers.is(1)).extract().jsonPath().get("[0].id");
    }

    @Test
    @Order(3)
    public void testListAllPegeable() {
        final int page = 0;
        final int size = 5;
        requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_RENDA + "/page").then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
    }

    @Test
    @Order(4)
    public void testFindById() {
        requisicao().pathParam("id", idRenda).when().get(PATH_RENDA + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode()).body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
    }

    @Test
    @Order(5)
    public void testUpdate() {
        Map<String, Object> body = bodyRenda;
        body.put(atibuteValue, "nomeDiferente");
        requisicao().pathParam("id", idRenda).body(body).when().put(PATH_RENDA + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(atibuteValue, CoreMatchers.equalTo("nomeDiferente"));
    }

    @Test
    @Order(6)
    public void testDelete() {
        requisicao().pathParam("id", idRenda).when().delete(PATH_RENDA + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().when().get(PATH_RENDA).then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
                CoreMatchers.is(0));

    }

    @Test
    @Order(7)
    public void testCleanUp() {

        requisicao().pathParam("id", idPagador).when().delete(PATH_PAGADOR + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().pathParam("id", idPatrimonio).when().delete(PATH_PATRIMONIO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().pathParam("id", idLocalizacao).when().delete(PATH_LOCALIZACAO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().pathParam("id", idTipoPatrimonio).when().delete(PATH_TIPOPATRIMONIO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().pathParam("id", idMunicipio).when().delete(PATH_MUNICIPIO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().pathParam("id", idTipoRenda).when().delete(PATH_TIPORENDA + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

    }

}