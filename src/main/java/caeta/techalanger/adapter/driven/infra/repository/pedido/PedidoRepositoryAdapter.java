package caeta.techalanger.adapter.driven.infra.repository.pedido;

import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteEntity;
import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteRepository;
import caeta.techalanger.adapter.driven.infra.repository.produto.ProdutoRepository;
import caeta.techalanger.adapter.driver.controller.pedido.request.PedidoItensRequest;
import caeta.techalanger.core.application.ports.PedidoRepositoryPort;
import caeta.techalanger.core.domain.Cliente;
import caeta.techalanger.core.domain.Pedido;
import caeta.techalanger.core.domain.PedidoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Pedido salvar(Pedido pedido) {

        PedidoEntity pedidoEntity = new PedidoEntity();

        if (pedido.getCliente() != null) {
            ClienteEntity clienteEntity = clienteRepository.findByCpf(pedido.getCliente().getCpf()).orElseThrow();
            pedidoEntity.setCliente(clienteEntity);
        }


        List<PedidoItem> itensPedido = pedido.getItens();

        for (PedidoItem item : itensPedido) {
            var produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow();
            pedidoEntity.adicionarItem(new PedidoEntityItem(item.getQuantidade(), pedidoEntity, produto));
        }
        var novoPedido = repository.save(pedidoEntity);

        return novoPedido.paraPedido();
    }
}


