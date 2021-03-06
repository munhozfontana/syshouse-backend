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
public class RecebimentoPatrimonioResourceTest {

    private static final String PATH_MUNICIPIO = "/municipio";
    private static final String PATH_LOCALIZACAO = "/localizacao";
    private static final String PATH_TIPOPATRIMONIO = "/tipopatrimonio";
    private static final String PATH_PATRIMONIO = "/patrimonio";
    private static final String PATH_PAGADOR = "/pagador";
    private static final String PATH_TIPORENDA = "/tiporenda";
    private static final String PATH_RENDA = "/renda";
    private static final String PATH_RECEBIMENTO = "/recebimento";
    private static final String PATH_RECEBIMENTOPATRIMONIO = "/recebimentopatrimonio";

    private String idLocalizacao;
    private String idMunicipio;
    private String idPatrimonio;
    private String idTipoPatrimonio;
    private String idPagador;
    private String idTipoRenda;
    private String idRenda;
    private String idRecebimento;
    private String idRecebimentoPatrimonio;

    private final HashMap<String, Object> bodyPatrimonio = new HashMap<String, Object>();
    private final HashMap<String, Object> bodyLocalizacao = new HashMap<String, Object>();
    private final HashMap<String, Object> bodyMunicipio = new HashMap<String, Object>();
    private final HashMap<String, Object> bodyTipoRecebimento = new HashMap<String, Object>();
    private final HashMap<String, Object> bodyPagador = new HashMap<String, Object>();
    private final HashMap<String, Object> bodyTipoRenda = new HashMap<String, Object>();
    private final HashMap<String, Object> bodyRenda = new HashMap<String, Object>();
    private final HashMap<String, Object> bodyRecebimento = new HashMap<String, Object>();
    private final HashMap<String, Object> bodyRecebimentoPatrimonio = new HashMap<String, Object>();

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
        idRenda = insertRenda(idPagador, idPatrimonio, idTipoRenda);
        idRecebimento = insertRecebimento(idRenda);
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
        bodyTipoRecebimento.put("descricao", "descricao");

        requisicao().body(bodyTipoRecebimento).when().post(PATH_TIPOPATRIMONIO).then()
                .statusCode(Response.Status.CREATED.getStatusCode());

        return requisicao().when().get(PATH_TIPOPATRIMONIO).then().statusCode(Response.Status.OK.getStatusCode())
                .extract().jsonPath().get("[0].id");
    }

    private String insertLocalizacao(final String municipioId) {
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

    private String insertPatrimonio(final String localizacaoId, final String tipoPatrimonioId) {
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
        bodyTipoRenda.put("descricao", "descricao");

        requisicao().body(bodyTipoRenda).when().post(PATH_TIPORENDA).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());

        return requisicao().when().get(PATH_TIPORENDA).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    private String insertRenda(final String pagadorId, final String patrimonioId, final String tiporendaId) {
        bodyRenda.put("dataFim", "06/04/1994");
        bodyRenda.put("dataInicio", "06/04/1994");
        bodyRenda.put("descricao", "descricao");
        bodyRenda.put("obs", "obs");
        bodyRenda.put("pagadorId", pagadorId);
        bodyRenda.put("patrimonioId", patrimonioId);
        bodyRenda.put("tipoRendaId", tiporendaId);
        bodyRenda.put("valor", 0);
        bodyRenda.put("vencimento", "06/04/1994");

        requisicao().body(bodyRenda).when().post(PATH_RENDA).then().statusCode(Response.Status.CREATED.getStatusCode())
                .header("location", CoreMatchers.notNullValue());

        return requisicao().when().get(PATH_RENDA).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    public String insertRecebimento(String rendaId) {
        bodyRecebimento.put("dataRecebimento", "06/04/2004");
        bodyRecebimento.put("obs", "obs");
        bodyRecebimento.put("rendaId", rendaId);
        bodyRecebimento.put("valor", 0);

        requisicao().body(bodyRecebimento).when().post(PATH_RECEBIMENTO).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());

        return requisicao().when().get(PATH_RECEBIMENTO).then().statusCode(Response.Status.OK.getStatusCode()).extract()
                .jsonPath().get("[0].id");
    }

    @Test
    @Order(1)
    public void testInsert() {

        bodyRecebimentoPatrimonio.put("recebimentoId", idRecebimento);
        bodyRecebimentoPatrimonio.put("patrimonioId", idPatrimonio);
        bodyRecebimentoPatrimonio.put("valorCalculado", 0);
        requisicao().body(bodyRecebimentoPatrimonio).when().post(PATH_RECEBIMENTOPATRIMONIO).then()
                .statusCode(Response.Status.CREATED.getStatusCode()).header("location", CoreMatchers.notNullValue());
    }

    @Test
    @Order(2)
    public void testListAll() {
        idRecebimentoPatrimonio = requisicao().when().get(PATH_RECEBIMENTOPATRIMONIO).then()
                .statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1)).extract().jsonPath()
                .get("[0].id");
    }

    @Test
    @Order(3)
    public void testListAllPegeable() {
        final int page = 0;
        final int size = 5;
        requisicao().queryParam("page", page).queryParam("size", size).when().get(PATH_RECEBIMENTOPATRIMONIO + "/page")
                .then().statusCode(Response.Status.OK.getStatusCode()).body("size()", CoreMatchers.is(1));
    }

    @Test
    @Order(4)
    public void testFindById() {
        requisicao().pathParam("id", idRecebimentoPatrimonio).when().get(PATH_RECEBIMENTOPATRIMONIO + "/{id}").then()
                .statusCode(Response.Status.OK.getStatusCode()).body("valorCalculado", CoreMatchers.notNullValue());
    }

    @Test
    @Order(5)
    public void testUpdate() {
        Map<String, Object> body = bodyRecebimentoPatrimonio;
        body.put("valorCalculado", 3);
        requisicao().pathParam("id", idRecebimentoPatrimonio).body(body).when()
                .put(PATH_RECEBIMENTOPATRIMONIO + "/{id}").then().statusCode(Response.Status.OK.getStatusCode())
                .body("valorCalculado", CoreMatchers.notNullValue());
    }

    @Test
    @Order(6)
    public void testDelete() {
        requisicao().pathParam("id", idRecebimentoPatrimonio).when().delete(PATH_RECEBIMENTOPATRIMONIO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().when().get(PATH_RECEBIMENTOPATRIMONIO).then().statusCode(Response.Status.OK.getStatusCode())
                .body("size()", CoreMatchers.is(0));

    }

    @Test
    @Order(7)
    public void testCleanUp() {

        requisicao().pathParam("id", idRecebimento).when().delete(PATH_RECEBIMENTO + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        requisicao().pathParam("id", idRenda).when().delete(PATH_RENDA + "/{id}").then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

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