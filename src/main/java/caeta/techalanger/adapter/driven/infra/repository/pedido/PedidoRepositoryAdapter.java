package caeta.techalanger.adapter.driven.infra.repository.pedido;

import caeta.techalanger.adapter.driven.infra.repository.cliente.ClienteRepository;
import caeta.techalanger.adapter.driven.infra.repository.produto.ProdutoRepository;
import caeta.techalanger.core.application.ports.PedidoRepositoryPort;
import caeta.techalanger.core.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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

        PedidoEntity pedidoEntity = new PedidoEntity(pedido);
        var pedidoSalvo = repository.save(pedidoEntity);

        return pedidoSalvo.paraPedido();
    }

    @Override
    public List<Pedido> findAll() {
        List<PedidoEntity> pedidos = repository.findAll();

        return pedidos.stream()
                .map(PedidoEntity::paraPedido)
                .collect(Collectors.toList());
    }
}


