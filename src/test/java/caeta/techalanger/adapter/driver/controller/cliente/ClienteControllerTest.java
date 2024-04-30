package caeta.techalanger.adapter.driver.controller.cliente;

import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteEntity;
import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteRepository;
import caeta.techalanger.core.domain.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest()
@ActiveProfiles("test")
class ClienteControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Testa a criação de um novo cliente")
    @Transactional
    void teste1() throws Exception {
        Cliente cliente = new Cliente("Eduardo", "04423258196", "caetasousa@gmail.com");

        String jsonRequest = objectMapper.writeValueAsString(cliente);

        ResultActions resultActions = mockMvc.perform(post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        var responseStatusCode = resultActions.andReturn().getResponse().getStatus();

        assertEquals(201, responseStatusCode);
    }

    @Test
    @DisplayName("Nâo deve criar um cliente com CPF ja existente")
    @Transactional
    void teste2() throws Exception {
        ClienteEntity clienteEntity = new ClienteEntity("Eduardo", "04423258196", "caetasousa@gmail.com");
        repository.save(clienteEntity);

        Cliente cliente = new Cliente("Eduardo", "04423258196", "caetasousa@gmail.com");

        String jsonRequest = objectMapper.writeValueAsString(cliente);

        ResultActions resultActions = mockMvc.perform(post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        var responseStatusCode = resultActions.andReturn().getResponse().getStatus();

        assertEquals(400, responseStatusCode);
    }

    @Test
    @DisplayName("Testa se o cliente existe ao buscar por um cpf")
    @Transactional
    void teste3() throws Exception {
        ClienteEntity clienteEntity = new ClienteEntity("Eduardo", "04423258196", "caetasousa@gmail.com");
        repository.save(clienteEntity);

        String responseBody = mockMvc.perform(MockMvcRequestBuilders.get("/cliente/{cpf}",clienteEntity.getCpf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}