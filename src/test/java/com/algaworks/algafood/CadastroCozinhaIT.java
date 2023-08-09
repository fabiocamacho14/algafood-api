package com.algaworks.algafood;


import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

    public static final int NAO_EXISTE = 100;

    @LocalServerPort
    private int port;

//    @Autowired
//    private Flyway flyway;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Integer quantidadeCozinhas;

    private Cozinha cozinhaAmericana;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath= "/cozinhas";

        databaseCleaner.clearTables();
        prepararDados();
        quantidadeCozinhas = cozinhaRepository.findAll().size();
//        flyway.migrate();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        RestAssured
                .given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConterXCozinhas_QuandoConsultarCozinhas() {
        RestAssured
                .given()
                    .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .body("nome", Matchers.hasSize(quantidadeCozinhas));
//                    .body("nome", Matchers.hasItems("Indiana", "Americana"));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        RestAssured
                .given()
                    .body(ResourceUtils.getContentFromResource("/jsons/cozinha-chinesa.json"))
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.CREATED.value());
    }

//    GET /cozinhas{cozinhaId}

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
        RestAssured
                .given()
                    .pathParam("cozinhaId", cozinhaAmericana.getId())
                    .accept(ContentType.JSON)
                .when()
                    .get("/{cozinhaId}")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("nome", Matchers.equalTo(cozinhaAmericana.getNome()));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
        RestAssured
                .given()
                    .pathParam("cozinhaId", NAO_EXISTE)
                    .accept(ContentType.JSON)
                .when()
                    .get("/{cozinhaId}")
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Indiana");
        cadastroCozinha.adicionar(cozinha1);

        cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cadastroCozinha.adicionar(cozinhaAmericana);
    }
}
