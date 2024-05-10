package caeta.techalanger.adapter.driver.controller.pedido;

import caeta.techalanger.adapter.driver.controller.pedido.request.PedidoRequest;
import caeta.techalanger.core.application.services.PedidoService;
import caeta.techalanger.core.domain.Pedido;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {


    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criarPedido(@RequestBody @Valid PedidoRequest request) {
        return pedidoService.criarPedido(request);
    }

    @GetMapping()
    public ResponseEntity<List<Pedido>> buscarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}
