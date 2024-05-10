package caeta.techalanger.core.application.ports;

import caeta.techalanger.core.domain.Pedido;

import java.util.List;

public interface PedidoRepositoryPort {

    Pedido salvar(Pedido pedido);

    List<Pedido> findAll();
}
