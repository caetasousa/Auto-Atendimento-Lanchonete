package caeta.techalanger.core.application.services;

import caeta.techalanger.adapter.driver.controller.pedido.request.PedidoItensRequest;
import caeta.techalanger.adapter.driver.controller.pedido.request.PedidoRequest;
import caeta.techalanger.core.application.ports.ClienteRepositoryPort;
import caeta.techalanger.core.application.ports.PedidoRepositoryPort;
import caeta.techalanger.core.application.ports.PedidoServicePort;
import caeta.techalanger.core.application.ports.ProdutoRepositoryPort;
import caeta.techalanger.core.domain.Cliente;
import caeta.techalanger.core.domain.Pedido;
import caeta.techalanger.core.domain.PedidoItem;

import java.math.BigDecimal;
import java.util.List;

public class PedidoService implements PedidoServicePort {
    private final ClienteRepositoryPort clienteRepositoryPort;
    private final ProdutoRepositoryPort produtoRepositoryPort;
    private final PedidoRepositoryPort pedidoRepositoryPort;


    public PedidoService(ClienteRepositoryPort clienteRepositoryPort, ProdutoRepositoryPort produtoRepositoryPort, PedidoRepositoryPort pedidoRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.produtoRepositoryPort = produtoRepositoryPort;
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }


    @Override
    public Pedido criarPedido(PedidoRequest request) {
        Pedido pedido = new Pedido();

        if (request.getCliente() != null) {
            Cliente cliente = clienteRepositoryPort.findByCPF(request.getCliente());
            pedido.setCliente(cliente);
        }

        List<PedidoItensRequest> itensPedido = request.getItens();

        for (PedidoItensRequest item : itensPedido) {
        var produto = produtoRepositoryPort.findById(item.getProdutoId());
            pedido.adicionarItem(new PedidoItem(item.getQuantidade(), produto, pedido));
        }
        pedido = pedidoRepositoryPort.salvar(pedido);

        return pedido;
    }
}
