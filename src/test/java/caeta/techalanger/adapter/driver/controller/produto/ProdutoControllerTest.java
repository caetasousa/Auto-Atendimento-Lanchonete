package caeta.techalanger.adapter.driver.controller.produto;

import caeta.techalanger.adapter.driven.infra.repository.produto.ProdutoEntity;
import caeta.techalanger.adapter.driven.infra.repository.produto.ProdutoRepository;
import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest()
@ActiveProfiles("test")
class ProdutoControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ProdutoRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Testa a criação de um novo produto")
    @Transactional
    void test1() throws Exception {
        Produto produto = new Produto("Coca-Cola",  Categoria.valueOf("BEBIDA"), BigDecimal.valueOf(5));

        String jsonRequest = objectMapper.writeValueAsString(produto);

        ResultActions resultActions = mockMvc.perform(post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        var responseStatusCode = resultActions.andReturn().getResponse().getStatus();

        assertEquals(201, responseStatusCode);
    }

    @Test
    @DisplayName("Nâo deve criar um produto com um Nome ja existente")
    @Transactional
    void teste2() throws Exception {
        ProdutoEntity produtoEntity = new ProdutoEntity(new Produto("Coca-Cola", Categoria.valueOf("BEBIDA"),BigDecimal.valueOf(5)));
        repository.save(produtoEntity);

        Produto produto = new Produto("Coca-Cola",  Categoria.valueOf("BEBIDA"), BigDecimal.valueOf(5));

        String jsonRequest = objectMapper.writeValueAsString(produto);

        ResultActions resultActions = mockMvc.perform(post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        var responseStatusCode = resultActions.andReturn().getResponse().getStatus();

        assertEquals(400, responseStatusCode);
    }

    @Test
    @DisplayName("Testa se o produto existe ao buscar por um id")
    @Transactional
    void teste3() throws Exception {
        ProdutoEntity produtoEntity = new ProdutoEntity(new Produto("Coca-Cola", Categoria.valueOf("BEBIDA"),BigDecimal.valueOf(5)));
        repository.save(produtoEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/produto/{id}",produtoEntity.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @DisplayName("Deve editar um produto")
    @Transactional
    void teste4() throws Exception {
        ProdutoEntity produtoEntity = new ProdutoEntity(new Produto("Coca-Cola", Categoria.valueOf("BEBIDA"),BigDecimal.valueOf(5)));
        repository.save(produtoEntity);

        Produto produto = new Produto("Suco",  Categoria.valueOf("BEBIDA"), BigDecimal.valueOf(5));

        String jsonRequest = objectMapper.writeValueAsString(produto);

        MvcResult result = mockMvc.perform(put("/produto/{id}", produtoEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertTrue(responseBody.contains("Suco"));
    }


    @Test
    @DisplayName("Deve deletar um produto a partir de um id")
    @Transactional
    void teste5() throws Exception {
        ProdutoEntity produtoEntity = new ProdutoEntity(new Produto("Coca-Cola", Categoria.valueOf("BEBIDA"),BigDecimal.valueOf(5)));
        repository.save(produtoEntity);

        mockMvc.perform(MockMvcRequestBuilders.delete("/produto/{id}",produtoEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @DisplayName("Deve buscar produtos pela categoria")
    @Transactional
    void teste6() throws Exception {
        ProdutoEntity produtoEntity1 = new ProdutoEntity(new Produto("Coca-Cola", Categoria.valueOf("BEBIDA"),BigDecimal.valueOf(5)));
        repository.save(produtoEntity1);

        ProdutoEntity produtoEntity2 = new ProdutoEntity(new Produto("Coca", Categoria.valueOf("BEBIDA"),BigDecimal.valueOf(5)));
        repository.save(produtoEntity2);

        mockMvc.perform(MockMvcRequestBuilders.get("/produto/categoria/{id}",produtoEntity1.getCategoria())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}

