package caeta.techalanger.adapter.driver.controller.pedido.response;

import caeta.techalanger.core.domain.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public record PedidoResponse(Long identificador, PedidoResponseCliente cliente, List<PedidoResponseItem> items) {

    public static List<PedidoResponse> paraDomainResponse(List<Pedido> pedido) {

        return pedido.stream()
                .map(novo -> new PedidoResponse(
                        novo.getId(),
                        new PedidoResponseCliente( novo.getCliente().getNome()),
                        novo.getItens().stream()
                                .map(item -> new PedidoResponseItem(item.getPrecoUnitario(), item.getQuantidade(),
                                        new PedidoResponseProduto(item.getProduto().getNome(), item.getProduto().getCategoria())))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
