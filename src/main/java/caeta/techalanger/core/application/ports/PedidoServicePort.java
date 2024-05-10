package caeta.techalanger.core.application.ports;

import caeta.techalanger.adapter.driver.controller.pedido.request.PedidoRequest;
import caeta.techalanger.core.domain.Pedido;

import java.util.List;

public interface PedidoServicePort {

    Pedido criarPedido(PedidoRequest request);

    List<Pedido> listarPedidos();
}
