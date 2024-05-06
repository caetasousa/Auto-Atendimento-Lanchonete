package caeta.techalanger.adapter.driver.controller.pedido;

import caeta.techalanger.adapter.driven.infra.repository.pedido.PedidoEntity;
import caeta.techalanger.adapter.driven.infra.repository.pedido.PedidoRepository;
import caeta.techalanger.adapter.driver.controller.pedido.request.PedidoRequest;
import caeta.techalanger.core.application.services.PedidoService;
import caeta.techalanger.core.domain.Pedido;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {


    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criarPedido(@RequestBody @Valid PedidoRequest request) {
        return pedidoService.criarPedido(request);
    }

    @GetMapping()
    public ResponseEntity<List<Pedido>> buscarPedidos() {
        List<PedidoEntity> pedidos = repository.findAll();

        List<Pedido> pedidosDTO = pedidos.stream()
                .map(PedidoEntity::paraPedido)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidosDTO);
    }
}
