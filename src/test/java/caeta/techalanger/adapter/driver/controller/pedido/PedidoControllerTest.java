package caeta.techalanger.adapter.driver.controller.pedido;

import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteEntity;
import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteRepository;
import caeta.techalanger.adapter.driven.infra.repository.pedido.PedidoEntity;
import caeta.techalanger.adapter.driven.infra.repository.pedido.PedidoEntityItem;
import caeta.techalanger.adapter.driven.infra.repository.pedido.PedidoRepository;
import caeta.techalanger.adapter.driven.infra.repository.produto.ProdutoEntity;
import caeta.techalanger.adapter.driven.infra.repository.produto.ProdutoRepository;
import caeta.techalanger.adapter.driver.controller.pedido.request.PedidoItensRequest;
import caeta.techalanger.adapter.driver.controller.pedido.request.PedidoRequest;
import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Cliente;
import caeta.techalanger.core.domain.PedidoItem;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest()
@ActiveProfiles("test")
class PedidoControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    @DisplayName("Testa a criação de um novo pedido sem cliente")
    @Transactional
    void teste1() throws Exception {
        ProdutoEntity produtoEntity = new ProdutoEntity("Coca-Cola", BigDecimal.valueOf(5), Categoria.valueOf("BEBIDA"));
        var produto = produtoRepository.save(produtoEntity);

        List<PedidoItensRequest> itens = new ArrayList<>();
        PedidoItensRequest a = new PedidoItensRequest(produto.getId(),1);
        itens.add(a);
        PedidoRequest novoPedido = new PedidoRequest(itens);



        String jsonRequest = objectMapper.writeValueAsString(novoPedido);

        ResultActions resultActions = mockMvc.perform(post("/pedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        var responseStatusCode = resultActions.andReturn().getResponse().getStatus();

        assertEquals(201, responseStatusCode);
    }
    @Test
    @DisplayName("Testa a criação de um novo pedido com cliente")
    @Transactional
    void teste2() throws Exception {
        ProdutoEntity produtoEntity = new ProdutoEntity("Coca-Cola", BigDecimal.valueOf(5), Categoria.valueOf("BEBIDA"));
        var produto = produtoRepository.save(produtoEntity);

        ClienteEntity clienteEntity = new ClienteEntity("Eduardo", "04423258196", "caetasousa@gmail.com");
        var cliente = clienteRepository.save(clienteEntity);

        List<PedidoItensRequest> itens = new ArrayList<>();
        PedidoItensRequest a = new PedidoItensRequest(produto.getId(),1);
        itens.add(a);
        PedidoRequest novoPedido = new PedidoRequest(itens);
        novoPedido.setCliente("04423258196");


        String jsonRequest = objectMapper.writeValueAsString(novoPedido);

        ResultActions resultActions = mockMvc.perform(post("/pedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        var responseStatusCode = resultActions.andReturn().getResponse().getStatus();
        assertEquals(201, responseStatusCode);
    }

    @Test
    @DisplayName("Deve buscar todos pedidos")
    @Transactional
    void teste6() throws Exception {
        ProdutoEntity produtoEntity = new ProdutoEntity("Coca-Cola", BigDecimal.valueOf(5), Categoria.valueOf("BEBIDA"));
        var produto = produtoRepository.save(produtoEntity);

        ClienteEntity clienteEntity = new ClienteEntity("Eduardo", "04423258196", "caetasousa@gmail.com");
        var cliente = clienteRepository.save(clienteEntity);


        PedidoEntity pedidoEntity1 = new PedidoEntity();
        PedidoEntity pedidoEntity2 = new PedidoEntity();


        pedidoEntity1.adicionarItem(new PedidoEntityItem(1, pedidoEntity1, produtoEntity));
        var response1 = pedidoRepository.save(pedidoEntity1);

        pedidoEntity2.adicionarItem(new PedidoEntityItem(2, pedidoEntity2, produtoEntity));
        var response2 =pedidoRepository.save(pedidoEntity2);



        String resultado = mockMvc.perform(MockMvcRequestBuilders.get("/pedido")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(resultado.contains(response1.getId().toString()));
        assertTrue(resultado.contains(response2.getId().toString()));
    }
}