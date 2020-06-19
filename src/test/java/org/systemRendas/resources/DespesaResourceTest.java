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
public class DespesaResourceTest {

    private static final String PATH_LOCALIZACAO = "/localizacao";
    private static final String PATH_MUNICIPIO = "/municipio";
    private static final String PATH_PATRIMONIO = "/patrimonio";
    private static final String PATH_TIPOPATRIMONIO = "/tipopatrimonio";
    private static final String PATH_TIPODESPESA = "/tipodespesa";
    private static final String PATH_DESPESA = "/despesa";

    private String idLocalizacao;
    private String idMunicipio;
    private String idPatrimonio;
    private String idTipoPatrimonio;
    private String idDespesa;
    private String idTipoDespesa;

    private HashMap<String, Object> bodyPatrimonio = new HashMap<String, Object>();
    private HashMap<String, Object> bodyLocalizacao = new HashMap<String, Object>();
    private HashMap<String, Object> bodyMunicipio = new HashMap<String, Object>();
    private HashMap<String, Object> bodyDespesa = new HashMap<String, Object>();
    private HashMap<String, Object> bodyTipoDespesa = new HashMap<String, Object>();

    private String atibuteValue = "descricao";

    private RequestSpecification requisicao() {
        return given().accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(0)
    public void testSetUpLoad() {
        idMunicipio = insertMunicipio();
        idTipoDespesa = insertTipoDespesa();
        idTipoPatrimonio = insertTipoPatrimonio();
        idLocalizacao = insertLocalizacao(idMunicipio);
        idPatrimonio = insertPatrimonio(idLocalizacao, idTipoPatrimonio);
    }

    private String insertTipoDespesa() {
        bodyTipoDespesa.put("descricao", "descricao");
        requisicao().body(bodyTipoDespesa).when().post(PATH_TIPODESPESA).then()
                .statusCode(Response.Status.CREATED.getStatusCode());

        return requisicao().when().get(PATH_TIPODESPESA).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
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

    @Test
    @Order(1)
    public void testInsert() {
        bodyDespesa.put("dataFim", "06/04/2004");
        bodyDespesa.put("dataInicio", "06/04/2004");
        bodyDespesa.put("descricao", atibuteValue);
        bodyDespesa.put("obs", "obs");
        bodyDespesa.put("patrimonioId", idPatrimonio);
        bodyDespesa.put("tipoDespesaId", idTipoDespesa);
        bodyDespesa.put("valor", 0);
        bodyDespesa.put("vencimento", "06/04/2004");

        requisicao().body(bodyDespesa).when().post(PATH_DESPESA).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());
    }

    @Test
    @Order(2)
    public void testListAll() {
        idDespesa = requisicao().when().get(PATH_DESPESA).then().statusCode(Response.Status.OK.getStatusCode())
                .body("size()", CoreMatchers.is(1)).extract().jsonPath().get("[0].id");
    }

    @Test
    @Order(3)
    public void testListAllPegeable() {
        final int page = 0;
        final int size = 5;
        requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_DESPESA + "/page").then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
    }

    @Test
    @Order(4)
    public void testFindById() {
        requisicao().pathParam("id", idDespesa).when().get(PATH_DESPESA + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode()).body(atibuteValue, CoreMatchers.equalTo(atibuteValue));
    }

    @Test
    @Order(5)
    public void testUpdate() {
        Map<String, Object> body = bodyDespesa;
        body.put(atibuteValue, "novoDadoDiferente");
        requisicao().pathParam("id", idDespesa).body(body).when().put(PATH_DESPESA + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(atibuteValue, CoreMatchers.equalTo("novoDadoDiferente"));
    }

    @Test
    @Order(6)
    public void testDelete() {
        requisicao().pathParam("id", idDespesa).when().delete(PATH_DESPESA + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().when().get(PATH_DESPESA).then().statusCode(Response.Status.OK.getStatusCode()).body("size()",
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

        requisicao().pathParam("id", idTipoDespesa).when().delete(PATH_TIPODESPESA + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

}