package caeta.techalanger.adapter.driver.controller.cliente;

import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteEntity;
import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteRepository;
import caeta.techalanger.adapter.driver.controller.cliente.request.ClienteRequest;
import caeta.techalanger.core.application.ports.ClienteServicePort;
import caeta.techalanger.core.domain.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository repository;

    @BeforeEach
    @Transactional
    void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cliente";

        repository.deleteAll();
    }

    @Test
    @DisplayName("Testa a criação de um novo cliente")
    void teste1() throws Exception {
        Cliente cliente = new Cliente("Eduardo", "04423258196", "caetasousa@gmail.com");

        String jsonRequest = objectMapper.writeValueAsString(cliente);

        var response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(jsonRequest)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response();

    }

    @Test
    @DisplayName("Nâo deve criar um cliente com CPF ja existente")
    void teste2() throws Exception {
        ClienteEntity clienteEntity = new ClienteEntity("Eduardo", "04423258196", "caetasousa@gmail.com");
        repository.save(clienteEntity);

        Cliente cliente = new Cliente("Eduardo", "04423258196", "caetasousa@gmail.com");

        String jsonRequest = objectMapper.writeValueAsString(cliente);

        var response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().response();

    }

    @Test
    @DisplayName("Testa se o cliente existe ao buscar por um cpf")
    @Transactional
    void teste3() {
        ClienteEntity clienteEntity = new ClienteEntity("Eduardo", "04423258196", "caetasousa@gmail.com");
        repository.save(clienteEntity);


        RestAssured
                .given()
                .pathParam("cpf", "04423258196")
                .accept(ContentType.JSON)
                .when()
                .get("/{cpf}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}