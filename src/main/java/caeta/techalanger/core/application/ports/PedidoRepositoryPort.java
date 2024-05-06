package caeta.techalanger.core.application.ports;

import caeta.techalanger.core.domain.Pedido;

public interface PedidoRepositoryPort {

    Pedido salvar(Pedido pedido);
}
