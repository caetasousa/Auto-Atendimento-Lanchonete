package caeta.techalanger.core.application.ports;

import caeta.techalanger.adapter.driver.controller.pedido.request.PedidoRequest;
import caeta.techalanger.core.domain.Pedido;

public interface PedidoServicePort {

    Pedido criarPedido(PedidoRequest request);
}
